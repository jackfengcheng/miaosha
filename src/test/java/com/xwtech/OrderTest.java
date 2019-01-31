package com.xwtech;

import com.xwtech.miaosha.App;
import com.xwtech.miaosha.damain.MiaoshaOrder;
import com.xwtech.miaosha.damain.OrderInfo;
import com.xwtech.miaosha.dao.GoodsRepository;
import com.xwtech.miaosha.dao.OrderRepository;
import com.xwtech.miaosha.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class OrderTest {




}
