package com.example.demoorder.services;

import com.example.demoorder.entity.Order;

import java.util.List;

public interface OrderService {
    List<Order> findAll();
    Order save(Order order);
    List<Order> historyOfOrdersByOrderId(int id);
    Order findById(int Id);
    Order saveOrder(Order order);
}
