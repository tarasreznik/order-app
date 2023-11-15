package com.example.orderapp.services;

import com.example.orderapp.models.Order;

import java.util.List;

public interface OrderService {

    Order getOrderById(Long orderId);

    Order createOrder();

    Order addGoodsToOrder(Long orderId, String goodsName, Integer quantityToBuy);
    void payForOrder(Long orderId);

    List<Order> getLoggedInUserOrders();

    List<Order> getAllOrders();
}
