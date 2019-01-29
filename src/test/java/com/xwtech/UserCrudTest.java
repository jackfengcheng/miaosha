package com.xwtech;

import com.xwtech.miaosha.App;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.UserCrudRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserCrudTest {
    @Autowired
    private UserCrudRepository crudRepository;

    /**
     * 单个添加
     */
    @Test
    public void save(){
       User user = new User();
       user.setNickname("刘整");
       user.setMobile(15293062854l);
       User user1 = this.crudRepository.save(user);
       System.out.println(user);
   }

    /**
     * 批量添加
     */
    @Test
    public void saveAll(){
        User user = new User();
        user.setNickname("刘整2");
        user.setMobile(15293062854l);

        User user1 = new User();
        user1.setNickname("刘整1");
        user1.setMobile(15293062858l);
        List list = new ArrayList();
        list.add(user);
        list.add(user1);
        this.crudRepository.save(list);
        System.out.println(user);
    }
}
