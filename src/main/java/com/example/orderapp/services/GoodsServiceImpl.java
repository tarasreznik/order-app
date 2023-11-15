package com.example.orderapp.services;

import com.example.orderapp.exceptions.ItemAlreadyExistsException;
import com.example.orderapp.models.Goods;
import com.example.orderapp.repos.GoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    private final GoodsRepository goodsRepo;

    @Autowired
    public GoodsServiceImpl(GoodsRepository goodsRepo) {
        this.goodsRepo = goodsRepo;
    }


    @Override
    public List<Goods> getAllGoods() {
        return goodsRepo.findAll();
    }

    @Override
    public Goods saveGoods(Goods goods) {
        if (Boolean.TRUE.equals(goodsRepo.existsByName(goods.getName()))) {
            throw new ItemAlreadyExistsException("Goods with name " + goods.getName() + " is already exists");
        }

        return goodsRepo.save(goods);
    }
}
