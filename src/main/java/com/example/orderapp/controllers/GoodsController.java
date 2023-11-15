package com.example.orderapp.controllers;

import com.example.orderapp.models.Goods;
import com.example.orderapp.services.GoodsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GoodsController {
    private final GoodsService goodsService;

    @Autowired
    public GoodsController(GoodsService goodsService) {
        this.goodsService = goodsService;
    }

    //manager and client
    @GetMapping("/goods")
    public List<Goods> getAllGoods() {
        return goodsService.getAllGoods();
    }

    //manager
    @PostMapping ("/goods")
    public ResponseEntity<Goods> saveGoods(@Valid @RequestBody Goods createdGoods) {
        Goods good = goodsService.saveGoods(createdGoods);
        return new ResponseEntity<>(good, HttpStatus.CREATED);
    }
}
