package com.xwtech.miaosha.controller;

import com.xwtech.miaosha.service.OrderService;
import com.xwtech.miaosha.vo.Pages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admini on 2019/1/31.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/get/all")
    public Pages getAll(){
        Pages orderList = this.orderService.getOrderList(5, 0);
        return orderList;
    }
}
