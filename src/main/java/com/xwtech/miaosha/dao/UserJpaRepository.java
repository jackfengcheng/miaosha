package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.User;
import org.springframework.data.jpa.repository.JpaRepository;



public interface UserJpaRepository extends JpaRepository<User,Long> {
}
