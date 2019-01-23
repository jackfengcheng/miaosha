package com.xwtech.miaosha.controller;

import com.xwtech.miaosha.base.CodeMsg;
import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.MiaoshaOrder;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.service.GoodsService;
import com.xwtech.miaosha.service.MiaoshaService;
import com.xwtech.miaosha.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private MiaoshaService miaoshaService;

    @RequestMapping("/do_miaosha")
    public String list(Model model, User user,
                       @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if(user == null) {
            return "login";
        }
        //判断库存
        Goods goods = goodsService.goodsById(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getCode());
            return "miaosha_fail";
        }
        //判断是否已经秒杀到了
        OrderInfo order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_fail";
        }
        //减库存 下订单 写入秒杀订单
        OrderInfo orderInfo = miaoshaService.miaosha(user, goods);

        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }
}
