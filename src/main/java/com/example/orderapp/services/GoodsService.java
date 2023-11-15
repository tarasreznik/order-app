package com.example.orderapp.services;

import com.example.orderapp.models.Goods;

import java.util.List;

public interface GoodsService {

    List<Goods> getAllGoods();

    Goods saveGoods(Goods goods);

    //Integer getGoodsQuantity(Goods goods);



}
