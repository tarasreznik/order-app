package com.example.orderapp.repos;

import com.example.orderapp.models.Order;
import com.example.orderapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order getOrderById(Long orderId);

    List<Order> getOrdersByUserId(Long id);
}
