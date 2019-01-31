package com.xwtech;

import com.xwtech.miaosha.App;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.UserRepository;
import com.xwtech.miaosha.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void UserTest(){

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
                Predicate predicate = criteriaBuilder.equal(root.get("mobile"), 13830038970L);
                return predicate;
            }
        };
        List<User> users = this.userRepository.findAll(spec);
        for (User user:users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void queryUserByName(){
        List<User> users = this.userRepository.queryUserByName("jack");
        for (User user:users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void queryUserByNameAndMobile(){
        List<User> users = this.userRepository.queryUserByNameAndMobile("rose","13830038970");
        for (User user:users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void queryUserByNameLike(){
        List<User> users = this.userRepository.queryUserByNameLike("%ro%");
        for (User user:users) {
            System.out.println(user.toString());
        }
    }
}
