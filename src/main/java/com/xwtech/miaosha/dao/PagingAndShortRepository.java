package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingAndShortRepository extends PagingAndSortingRepository<User,Long> {

}
