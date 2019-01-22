package com.xwtech.miaosha.service.impl;



import com.xwtech.miaosha.base.CodeMsg;
import com.xwtech.miaosha.base.CookieKey;
import com.xwtech.miaosha.base.RedisKey;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.UserRepository;
import com.xwtech.miaosha.exception.GlobalException;
import com.xwtech.miaosha.service.UserService;
import com.xwtech.miaosha.util.CookieUtil;
import com.xwtech.miaosha.util.MD5Util;
import com.xwtech.miaosha.util.UUIDUtil;
import com.xwtech.miaosha.vo.LoginVo;
import groovy.util.logging.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(final Long id) {
        Specification<User> spec = new Specification<User>() {
            /**
             * Predicate 封装单个的查询条件
             * Root<Users> root ：查询的属性的封装
             * CriteriaQuery<?> query ：封装了  我们要执行的查询中的各个部分的信息。select   from    order by 等
             * CriteriaBuilder cb ：查询条件的构造器
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                /**
                 * 参数一：查询的条件属性
                 * 参数二：条件的值
                 */
                Predicate predicate = criteriaBuilder.equal(root.get("mobile"), id);
                return predicate;
            }
        };
        List<User> users = this.userRepository.findAll(spec);
        return users.get(0);
    }

    @Override
    public boolean login(HttpServletResponse response ,  LoginVo loginVo) {
        if (loginVo ==null){
            throw new GlobalException( CodeMsg.SERVER_ERROR);
        }
        String mobile = loginVo.getMobile();
        String password =loginVo.getPassword();
        User user = getById(Long.parseLong(mobile));
        if (user == null){
            throw new GlobalException( CodeMsg.MOBILE_NOT_EXIST);
        }
        String dbPassword = user.getPassword();
        String salt =user.getSalt();
        String calsPassWord = MD5Util.formPassToDbPass(password,salt);
        if(!calsPassWord.equals(dbPassword)){
            throw new GlobalException( CodeMsg.PASSWORD_ERROR);
         }
         //设置token
        addCookie(response,user);
        return true;
    }

    @Override
    public User getByToken(HttpServletResponse response,String token) {
        if(StringUtils.isEmpty(token)){
            return null;
        }
        User user = (User)this.redisTemplate.boundHashOps(RedisKey.TOKEN).get(token);
        //刷新ciikie
        if(user != null){
            addCookie(response,user);
        }
        return user;
    }


    private void addCookie(HttpServletResponse response,User user){
        String token = UUIDUtil.uuid();
        redisTemplate.boundHashOps(RedisKey.TOKEN).put(token,user);
        redisTemplate.expire(token,30,TimeUnit.MINUTES);
        Cookie cookie = new Cookie(CookieKey.COOKIE_NAME_TOKEN,token);
        cookie.setMaxAge(108000);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
