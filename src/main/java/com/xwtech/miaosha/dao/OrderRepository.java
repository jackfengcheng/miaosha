package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface OrderRepository extends JpaRepository<OrderInfo,Long>,JpaSpecificationExecutor<OrderInfo> {
}
