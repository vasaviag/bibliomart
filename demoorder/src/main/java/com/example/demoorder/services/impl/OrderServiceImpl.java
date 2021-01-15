package com.example.demoorder.services.impl;

import com.example.demoorder.entity.Order;
import com.example.demoorder.repository.OrderRepository;
import com.example.demoorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<Order> findAll() {
        Iterable<Order> employeeIterable = orderRepository.findAll();
        List<Order> orderList = new ArrayList<>();
        employeeIterable.forEach(orderList::add);
        return orderList;
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> historyOfOrdersByOrderId(int id) {
        List<String> arr1=new ArrayList<String>();
        Order order=new Order();
        order=orderRepository.findById(Id);
        String s=order.getTimestamp();
        String arr[]=s.split(",");
        Date date2=new SimpleDateFormat("yyyy-MM-dd").parse(Timestamp);

    }

    @Override
    public Order findById(int Id) {
        return orderRepository.findById(Id).get();
    }

    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
}
