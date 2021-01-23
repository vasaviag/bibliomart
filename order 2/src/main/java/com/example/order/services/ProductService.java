package com.example.order.services;

import com.example.order.entity.Order;
import com.example.order.entity.ProductList;
import com.example.order.repository.ProductRepository;

import java.util.List;

public interface ProductService {
    List<ProductList> findByOrderId(int orderId);
    int addProducts(List<ProductList> productLists);
}
