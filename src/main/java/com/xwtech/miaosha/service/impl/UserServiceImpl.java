package com.xwtech.miaosha.service.impl;

import com.xwtech.miaosha.base.CodeMsg;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.UserRepository;
import com.xwtech.miaosha.service.UserService;
import com.xwtech.miaosha.util.MD5Util;
import com.xwtech.miaosha.vo.LoginVo;
import org.apache.commons.codec.digest.Md5Crypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getById(Integer id) {
        return userRepository.findOne(id);
    }

    @Override
    public CodeMsg login(LoginVo loginVo) {
        if (loginVo ==null){
            return CodeMsg.SERVER_ERROR;
        }
        String mobile = loginVo.getMobile();
        String password =loginVo.getPassword();
        User user = getById(Integer.parseInt(mobile));
        if (user == null){
            return CodeMsg.MOBILE_NOT_EXIST;
        }
        String dbPassword = user.getPassword();
        String salt =user.getSalt();
        String calsPassWord = MD5Util.formPassToDbPass(loginVo.getPassword(),salt);
        if(calsPassWord.equals(dbPassword)){
            return CodeMsg.PASSWORD_ERROR;
         }
        return CodeMsg.SUCCESS;
    }
}
