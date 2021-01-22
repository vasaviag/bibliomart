package com.example.demoelasticsearch.service;

import com.example.demoelasticsearch.entity.new_entity.Merchant;
import com.example.demoelasticsearch.entity.new_entity.ProductListFromOrder;
import com.example.demoelasticsearch.entity.new_entity.UpdateProductsMerch;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface IConsumerService
{
    void listener(ArrayList<ProductListFromOrder> productListFromOrders) throws IOException;
    void listener1(String updateProductsMerch) throws IOException;
    void listener2(String merchant) throws IOException;
}
