package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserCrudRepository extends CrudRepository<User,Long> {

}
