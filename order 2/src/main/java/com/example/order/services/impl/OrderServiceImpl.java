package com.example.order.services.impl;

import com.example.order.entity.Order;
import com.example.order.repository.OrderRepository;
import com.example.order.repository.ProductRepository;
import com.example.order.services.OrderService;
import com.example.order.services.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProducerService producerService;

    @Autowired
    private SendEmailService sendEmailService;


    @Override
    public List<Order> findAll() {
        Iterable<Order> orderIterable= orderRepository.findAll();
        List<Order> orderList=new ArrayList<>();
        orderIterable.forEach(orderList::add);
        return orderList;
    }

    @Override
    public Order save(Order order) {
        producerService.sendMessageToElasticSearchAfterOrder(productRepository.findByOrderId(order.getOrderId()));
        producerService.sendMessageToMerchantSearchAfterOrder(productRepository.findByOrderId(order.getOrderId()));
        producerService.sendMessageToProductAfterOrder(productRepository.findByOrderId(order.getOrderId()));

//        sendEmailService.sendEmail("kumaramit.94713@gmail.com", "Order Confirmation: Hurray!! Your Order is placed",
//                " Orders from BiblioMart");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findByUserId(int userId) {
        Iterable<Order> orderIterable = orderRepository.findByUserId(userId);
        List<Order> orderList=new ArrayList<>();
        orderIterable.forEach(orderList::add);
        return orderList;
    }

    @Override
    public Order addItemsToOrder(Order order) {
//

        return orderRepository.save(order);
    }

    @Override
    public List<Order> getCurrentOrder() {
        Iterable<Order> orderIterable= orderRepository.findAll();
        List<Order> orderList=new ArrayList<>();
        orderIterable.forEach(orderList::add);
        return orderList;
    }
}
