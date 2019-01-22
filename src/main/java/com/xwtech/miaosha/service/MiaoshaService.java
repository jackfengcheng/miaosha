package com.xwtech.miaosha.service;

import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.MiaoshaOrder;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.damain.User;

public interface MiaoshaService {

    OrderInfo miaosha(User user, Goods goods);

    MiaoshaOrder createMiaoShaOrder(User user,Goods goods,OrderInfo orderInfo);

}
