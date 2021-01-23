package com.example.order.services;

import com.example.order.entity.ProductList;
import com.example.order.services.impl.Producer;

import java.util.List;

public interface ProducerService {
    void sendMessageToProductAfterOrder(List<ProductList> productList);
    void sendMessageToElasticSearchAfterOrder(List<ProductList> productList);
    void sendMessageToMerchantSearchAfterOrder(List<ProductList> productList);
}
