package com.xwtech;

import com.xwtech.miaosha.App;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.PagingAndShortRepository;
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
public class UserPagingAndShortTest {

    @Autowired
    private PagingAndShortRepository repository;


    /**
     * spring data jpa分页处理
     */
    @Test
    public void test(){
        int page=1;//当前页的索引，索引是从0开始的
        int size=2;//当前页的数量
        Pageable Pageable = new PageRequest(page,size);
        Page<User> userPage = this.repository.findAll(Pageable);
        int totalPages = userPage.getTotalPages();//总页数
        List<User> users = userPage.getContent();//返回的数据
        for (User user: users) {
            System.out.println("用户----->"+user);
        }
        long totalElements = userPage.getTotalElements();//总条数
        System.out.println("总页数----->"+totalPages);
        System.out.println("总条数----->"+totalElements);

    }

    /**
     * spring data jpa单列排序
     */
    @Test
    public void test1(){
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        List<User> users = (List<User>)this.repository.findAll(sort);
        for (User user:users) {
            System.out.println("用户-----------》"+user);
        }

    }
}
