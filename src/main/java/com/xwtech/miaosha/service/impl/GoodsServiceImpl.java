package com.xwtech.miaosha.service.impl;

import com.xwtech.miaosha.damain.Goods;
import com.xwtech.miaosha.dao.GoodsRepository;
import com.xwtech.miaosha.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService{

    @Autowired
    private GoodsRepository goodsRepository;

    @Override
    public List<Goods> goodsList() {
        List<Goods> goodsList = this.goodsRepository.findAll();
        return goodsList;
    }

    @Override
    public Goods goodsById(Long goodsId) {
        Goods good = this.goodsRepository.findOne(goodsId);
        return good;
    }
}