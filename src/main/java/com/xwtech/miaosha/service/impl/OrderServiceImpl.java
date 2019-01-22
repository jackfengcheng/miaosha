package com.xwtech.miaosha.service.impl;

import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.MiaoshaOrder;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.GoodsRepository;
import com.xwtech.miaosha.dao.OrderRepository;
import com.xwtech.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public MiaoshaOrder getMiaoshaOrderByUserIdGoodsId(Integer id, long goodsId) {

        this.orderRepository.findAll();
        return null;
    }

    @Override
    public OrderInfo createOrder(User user, Goods goods) {
        OrderInfo ol = new OrderInfo();
        ol.setGoodsId(goods.getId());
        ol.setCreateDate(new Date(System.currentTimeMillis()));
        ol.setGoodsName(goods.getGoodsName());
        ol.setGoodsPrice(goods.getGoodsPrice());
        ol.setDeliveryAddrId(0l);
        ol.setStatus(0);
        ol.setOrderChannel(1);
        ol.setUserId(new Long((long)user.getId()));
        ol.setGoodsCount(1);
        OrderInfo orderInfo = this.orderRepository.save(ol);
        return orderInfo;
    }
}
