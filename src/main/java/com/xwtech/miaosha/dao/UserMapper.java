package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserMapper {

    @Select("select * from user where userid=#{id}")
    public User getUserById(@Param("id") Integer id) ;
}
