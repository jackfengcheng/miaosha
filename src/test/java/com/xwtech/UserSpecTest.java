package com.xwtech;

import com.xwtech.miaosha.App;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class UserSpecTest {
    @Autowired
    private UserRepository repository;

    /**
     * 单条件的查询
     */
    @Test
    public void test(){
        Specification<User> specification =new Specification<User>() {

            /**
             * Predicate 封装单个的查询条件
             * Root<Users> root ：查询的属性的封装
             * CriteriaQuery<?> query ：封装了  我们要执行的查询中的各个部分的信息。select   from    order by 等
             * CriteriaBuilder cb ：查询条件的构造器
             */
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.equal(root.get("nickname"),"rose");
                return predicate;
            }
        };
        List<User> userList = this.repository.findAll(specification);
        for (User user:userList) {
            System.out.println(user);
        }
    }

    /**
     * 通过数组进行多条件的查询
     */
    @Test
    public void test1(){
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> list =new ArrayList<Predicate>();
                list.add(cb.equal(root.get("nickname"),"rose"));
                list.add(cb.equal(root.get("mobile"),15200000000L));
                Predicate [] arr = new Predicate[list.size()];
                return cb.and(list.toArray(arr));
            }
        };
        List<User> userList = this.repository.findAll(specification);
        for (User user:userList) {
            System.out.println(user);
        }
    }

    /**
     * 直接传递多条件
     */
    @Test
    public void test2(){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate nickname = cb.equal(root.get("nickname"), "rose");
                Predicate mobile = cb.equal(root.get("mobile"), 15200000000L);
                return cb.or(nickname,mobile);
            }
        };
        List<User> userList = this.repository.findAll(spec);
        for (User user:userList) {
            System.out.println(user);
        }
    }

    /**
     * 分页+条件查询
     */
    @Test
    public void test3(){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate predicate = cb.like(root.get("nickname"), "ro%");
                return predicate;
            }
        };
        Pageable pageable = new PageRequest(1,5);
        Page page = this.repository.findAll(spec, pageable);
        long totalElements = page.getTotalElements();
        System.out.println("---------总条数"+totalElements);
        int totalPages = page.getTotalPages();
        System.out.println("---------总页数"+totalPages);
        List<User> list = page.getContent();
        for (User user: list) {
            System.out.println(user);
        }
    }

    /**
     * 排序+条件查询
     */
    @Test
    public void test4(){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate predicate = cb.like(root.get("nickname"), "ro%");
                return predicate;
            }
        };
        Sort sort = new Sort(Sort.Direction.ASC,"id");
        List<User> list = this.repository.findAll(spec, sort);
        for (User user :list) {
            System.out.println(user);
        }
    }


    /**
     * 分页+排序+条件查询
     * 分页在Sort中传
     */
    @Test
    public void test5(){
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                Predicate predicate = cb.like(root.get("nickname"), "ro%");
                return predicate;
            }
        };
        Sort sort = new Sort(Sort.Direction.DESC,"id");
        Pageable pageable = new PageRequest(0,5,sort);
        Page page = this.repository.findAll(spec, pageable);
        long totalElements = page.getTotalElements();
        System.out.println("---------总条数"+totalElements);
        int totalPages = page.getTotalPages();
        System.out.println("---------总页数"+totalPages);
        List<User> list = page.getContent();
        for (User user :list) {
            System.out.println(user);
        }
    }

}
