package com.example.demoorder.controller;


import com.example.demoorder.entity.Order;
import com.example.demoorder.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping("/saveOrder")
    public Order saveOrder(Order order)
    {
        return orderService.saveOrder(order);
    }

    @GetMapping("/historyOfOrders")
    public List<Order> historyOfOrdersByOrderId(int id)
    {
        return orderService.historyOfOrdersByOrderId(id);
    }



}
