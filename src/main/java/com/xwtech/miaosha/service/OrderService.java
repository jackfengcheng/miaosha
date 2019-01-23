package com.xwtech.miaosha.service;

import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.MiaoshaOrder;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.damain.User;

public interface OrderService {

    OrderInfo getMiaoshaOrderByUserIdGoodsId(Integer id, long goodsId);

    OrderInfo createOrder(User user, Goods goods);
}
