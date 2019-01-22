package com.xwtech;

import com.xwtech.miaosha.App;
import com.xwtech.miaosha.dao.GoodsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class GoodsTest {

    @Autowired
    private GoodsRepository goodsRepository;

    @Test
    public void goodList(){
        this.goodsRepository.findAll();
    }
}
