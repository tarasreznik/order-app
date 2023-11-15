package com.example.orderapp.services;

import com.example.orderapp.exceptions.NotEnoughGoodsToBuy;
import com.example.orderapp.exceptions.ResourceNotFoundException;
import com.example.orderapp.models.Goods;
import com.example.orderapp.models.Order;
import com.example.orderapp.repos.GoodsRepository;
import com.example.orderapp.repos.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final UserService userService;
    private final OrderRepository orderRepo;

    private final GoodsRepository goodsRepo;


    public OrderServiceImpl(UserService userService, OrderRepository orderRepo, GoodsRepository goodsRepo) {
        this.userService = userService;
        this.orderRepo = orderRepo;
        this.goodsRepo = goodsRepo;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @Override
    public Order getOrderById(Long orderId) {
        return orderRepo.findById(orderId).orElseThrow(() ->
                new ResourceNotFoundException("There is no order with id: " + orderId));
    }

    @Override
    public Order createOrder() {
        Order order = new Order();
        order.setUser(userService.getLoggedInUser());
        return orderRepo.save(order);
    }

    @Override
    public Order addGoodsToOrder(Long orderId, String goodsName, Integer quantityToBuy) {
        Order order = getOrderById(orderId);

        Goods goods = goodsRepo.findByName(goodsName).orElseThrow(() ->
                new ResourceNotFoundException("There is no goods with name: " + goodsName));

        Integer availableQuantity = goods.getAvailableQuantity();

        if (availableQuantity >= quantityToBuy) {
            order.getItems().add(goods);
            goods.setAvailableQuantity(goods.getAvailableQuantity() - quantityToBuy);
            order.setAmount(order.getAmount() + goods.getPrice() * quantityToBuy);

            return orderRepo.save(order);
        } else throw new NotEnoughGoodsToBuy("Goods " + goods.getName() + " is available " + availableQuantity);
    }

    @Override
    public void payForOrder(Long orderId) {
        Order order = getOrderById(orderId);
        order.setPaid(true);
        orderRepo.save(order);
    }

    @Override
    public List<Order> getLoggedInUserOrders() {
        return orderRepo.getOrdersByUserId(userService.getLoggedInUser().getId());
    }
}
