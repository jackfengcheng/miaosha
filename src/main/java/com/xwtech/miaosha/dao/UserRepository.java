package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaSpecificationExecutor<User>, JpaRepository<User,Long> {


}
