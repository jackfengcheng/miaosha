package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User,Long>,Repository<User,Long> {

    @Query(value = "select * from user where nickname =?",nativeQuery = true)
    List<User> queryUserByName(String name);

    @Query(value = "select * from user where nickname =? and mobile =?",nativeQuery = true)
    List<User> queryUserByNameAndMobile(String name,String mobile);

    @Query(value = "select * from user where nickname like ? ORDER by id DESC ",nativeQuery = true)
    List<User> queryUserByNameLike(String name);
}
