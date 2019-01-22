package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.MiaoshaOrder;
import com.xwtech.miaosha.damain.OrderInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface MiaoShaOrderRepository extends JpaRepository<MiaoshaOrder,Long>,JpaSpecificationExecutor<MiaoshaOrder> {
}
