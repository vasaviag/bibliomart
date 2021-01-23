package com.example.order.controller;


import com.example.order.entity.Order;
import com.example.order.entity.ProductList;
import com.example.order.services.OrderService;
import com.example.order.services.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    ProductService productService;

    // returns all orders
    @GetMapping("/orderget")
    public List<Order> getCurrentOrder()
    {
        return orderService.getCurrentOrder();
    }

    //get order by order id from Product List entity

    @GetMapping(value="/getorder/{orderId}")
    List<ProductList> findByUserId(@PathVariable("orderId") int id)
    {
        return productService.findByOrderId(id);
    }

    //done
    @PostMapping("/orderadd/{userId}")
    public Order addItemsToOrder(@RequestBody List<ProductList> productLists, @PathVariable int userId)
    {
        System.out.println("hhhhh");
        Order order = generateTimeStampsAndOrderId(userId);
        int orderID = order.getOrderId();
        for (ProductList prod: productLists)
        {
            prod.setOrderId(orderID);
        }
        productService.addProducts(productLists);

        return order;
    }

    //History of Orders By timeStamp

    @GetMapping("/timeStamps/{userId}")
    public List<Order> getTimeStamps(@PathVariable("userId") int id)
    {
        List<Order> timeStamps = new ArrayList<>();
        List<Order> orders = orderService.findByUserId(id);
        for (Order order : orders)
        {
            timeStamps.add(new Order(order.getOrderId(), order.getUserId(), order.getTimestamp()));
        }
        return timeStamps;
    }

    public Order generateTimeStampsAndOrderId(int userId)
    {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Order order = new Order();
        order.setUserId(userId);
        order.setTimestamp(timestamp + "");
        return orderService.save(order);
    }
}
