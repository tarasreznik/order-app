package com.example.orderapp.controllers;

import com.example.orderapp.models.OrderItemModel;
import com.example.orderapp.models.Order;
import com.example.orderapp.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    // manager
    @GetMapping("/orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/myorders")
    public List<Order> getLoggedInUserOrders() {
        return orderService.getLoggedInUserOrders();
    }
    //client
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder() {
        return new ResponseEntity<>(orderService.createOrder(), HttpStatus.CREATED);

    }

    //client
    @PutMapping("/orders/{orderId}")
    public ResponseEntity<Order> addToOrder(@PathVariable Long orderId, @RequestBody OrderItemModel orderItemModel) {
        return new ResponseEntity<>(orderService.addGoodsToOrder(orderId, orderItemModel.getGoodsName(),
                orderItemModel.getQuantity()), HttpStatus.OK);
    }

    //client
    @PutMapping("/orders/{orderId}/pay")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void payForOrder(@PathVariable Long orderId){
        orderService.payForOrder(orderId);
    }
}
