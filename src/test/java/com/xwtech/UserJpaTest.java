package com.xwtech;

import com.xwtech.miaosha.App;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.UserJpaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserJpaTest {
    @Autowired
    private UserJpaRepository repository;

    /**
     * 直接返回一个LIST,方便使用
     */
    @Test
    public void test(){
        List<User> users = this.repository.findAll(new Sort(Sort.Direction.ASC, "id"));
        for (User user:users) {
            System.out.println("------>"+user);
        }
    }


}
