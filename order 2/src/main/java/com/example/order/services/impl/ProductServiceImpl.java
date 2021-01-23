package com.example.order.services.impl;

import com.example.order.entity.ProductList;
import com.example.order.repository.ProductRepository;
import com.example.order.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<ProductList> findByOrderId(int id)
    {
        Iterable<ProductList> productIterable = productRepository.findByOrderId(id);
        List<ProductList> productList = new ArrayList<>();
        productIterable.forEach(productList::add);
        return productList;
    }

    public int addProducts(List<ProductList> productLists)
    {
        int orderId = 0;
        for (ProductList list : productLists)
        {
            ProductList productList = productRepository.save(list);
            orderId = productList.getOrderId();
        }
        return orderId;
    }
}
