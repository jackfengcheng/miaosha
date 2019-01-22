package com.xwtech.miaosha.service.impl;

import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.MiaoshaOrder;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.dao.GoodsRepository;
import com.xwtech.miaosha.dao.MiaoShaOrderRepository;
import com.xwtech.miaosha.dao.OrderRepository;
import com.xwtech.miaosha.service.GoodsService;
import com.xwtech.miaosha.service.MiaoshaService;
import com.xwtech.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MiaoshaServiceImpl implements MiaoshaService {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoShaOrderRepository miaoShaOrderRepository;

    @Transactional
    @Override
    public OrderInfo miaosha(User user, Goods goods) {
        //减库存
        this.goodsService.reduceStock(goods);
        //存订单
        OrderInfo orderInfo = this.orderService.createOrder(user, goods);
        //存秒杀订单
        createMiaoShaOrder(user,goods,orderInfo);
        return orderInfo;
    }


    /**
     * 添加秒杀订单
     * @param user
     * @param goods
     * @param orderInfo
     * @return
     */
    @Override
    public MiaoshaOrder createMiaoShaOrder(User user, Goods goods, OrderInfo orderInfo) {
        MiaoshaOrder mo = new MiaoshaOrder();
        mo.setGoodsId(goods.getId());
        mo.setOrderId(orderInfo.getId());
        mo.setUserId(new Long((long)user.getId()));
        MiaoshaOrder miaoshaOrder = this.miaoShaOrderRepository.save(mo);
        return miaoshaOrder;
    }
}
