package com.example.order.services;

import com.example.order.entity.Order;
import com.example.order.entity.ProductList;
import com.example.order.repository.OrderRepository;

import java.util.List;


public interface OrderService {

    List<Order>findAll();
    Order save(Order order);
    List<Order> findByUserId(int id);
    Order addItemsToOrder(Order order);
    List<Order> getCurrentOrder();
}
