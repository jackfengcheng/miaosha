package com.xwtech.miaosha.controller;


import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.damain.User;
import com.xwtech.miaosha.service.GoodsService;
import com.xwtech.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private UserService userService;

    @Autowired
    private GoodsService goodsService;

    /**
     * 商品列表
     * @param model
     * @param response
     * @param user
     * @return
     */
    @RequestMapping("/to_list")
    public String list(Model model,HttpServletResponse response,User user) {
        List<Goods> goods = this.goodsService.goodsList();
        model.addAttribute("user",user);
        model.addAttribute("goods",goods);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{id}")
    public String detail(Model model, User user,
                         @PathVariable("id")long goodsId) {

        Goods goods = this.goodsService.goodsById(goodsId);
        long start =goods.getStartDate().getTime();
        long end =goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSecond =0;
        if(now <start){//秒杀没开始
            miaoshaStatus=0;
            remainSecond = (int)((start -now)/1000);
        }else if(now > end){//秒杀已经结束
            miaoshaStatus=2;
            remainSecond=-1;
        }else {//秒杀正在进行
            miaoshaStatus=1;
            remainSecond=0;
        }
        model.addAttribute("goods",goods);
        model.addAttribute("miaoshaStatus",miaoshaStatus);
        model.addAttribute("remainSecond",remainSecond);
        model.addAttribute("user",user);
        return "goods_detail";
    }
}
