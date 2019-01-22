package com.xwtech.miaosha.service;


import com.xwtech.miaosha.damain.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> goodsList();

    Goods goodsById(Long goodsId);
}
