package com.xwtech.miaosha.dao;

import com.xwtech.miaosha.damain.Goods;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
public interface GoodsRepository extends JpaRepository<Goods,Long>,JpaSpecificationExecutor<Goods> {
}
