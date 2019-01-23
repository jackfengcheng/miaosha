package com.xwtech;

import com.xwtech.miaosha.App;
import com.xwtech.miaosha.damain.MiaoshaOrder;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.dao.GoodsRepository;
import com.xwtech.miaosha.dao.OrderRepository;
import com.xwtech.miaosha.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class OrderTest {

    @Autowired
    private OrderService orderService;

    @Test
    public void goodList(){
        OrderInfo orderInfo = this.orderService.getMiaoshaOrderByUserIdGoodsId(1, 2);
        System.out.println(orderInfo.getGoodsId()+"---------->"+orderInfo.getUserId());
    }
}
