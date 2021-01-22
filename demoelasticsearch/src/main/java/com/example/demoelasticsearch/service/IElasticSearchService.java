package com.example.demoelasticsearch.service;

import com.example.demoelasticsearch.entity.Product;
import com.example.demoelasticsearch.entity.MerchantDetails;
import com.example.demoelasticsearch.entity.UpdateProduct;
import java.util.List;


public interface IElasticSearchService {

    Product save(Product product);

    Product getProduct(int id);

    List<Product> getAllProducts();

    List<MerchantDetails> merchantDetails(int index);

    List<Product> filterByCatName(String s);

    List<Product> findAll();

    List<Product> getAll(String text);

    List<Product> findByProductName(String pname);

    void updateQuantityCostAfterOrder(UpdateProduct updateProduct);

    void updateQuantityCostByMerchant(UpdateProduct updateProduct);



}
