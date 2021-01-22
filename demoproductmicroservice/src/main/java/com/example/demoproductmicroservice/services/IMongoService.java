package com.example.demoproductmicroservice.services;

import com.example.demoproductmicroservice.entity.MerchantDetails;
import com.example.demoproductmicroservice.entity.Product;
import com.example.demoproductmicroservice.entity.UpdateProduct;
import org.apache.kafka.common.protocol.types.Field;

import java.util.List;

public interface IMongoService {

    Product save(Product product);
    Product getProduct(int id);
    List<Product> topProducts();
    //List<Product> merchantDetails(int id);
    List<MerchantDetails> merchantDetails(int index);
    List<Product> findAll();
    void updateQuantityCostAfterOrder(UpdateProduct updateProduct);
    void updateQuantityCostByMerchant(UpdateProduct updateProduct);
    List<Product> findByProductName(String pname);
}
