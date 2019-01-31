package com.xwtech.miaosha.service;

import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.vo.Pages;

public interface OrderService {

    OrderInfo getMiaoshaOrderByUserIdGoodsId(Integer id, long goodsId);

    OrderInfo createOrder(User user, Goods goods);

    Pages getOrderList(int size, int page);
}
