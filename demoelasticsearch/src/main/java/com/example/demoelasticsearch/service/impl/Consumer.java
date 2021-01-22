package com.example.demoelasticsearch.service.impl;

import com.example.demoelasticsearch.entity.MerchantDetails;
import com.example.demoelasticsearch.entity.Product;
import com.example.demoelasticsearch.entity.ProductDetails;
import com.example.demoelasticsearch.entity.UpdateProduct;
import com.example.demoelasticsearch.entity.new_entity.Merchant;
import com.example.demoelasticsearch.entity.new_entity.ProductListFromOrder;
import com.example.demoelasticsearch.entity.new_entity.UpdateProductsMerch;
import com.example.demoelasticsearch.service.IConsumerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class Consumer implements IConsumerService {

    @Autowired
    ElasticSearchServiceImpl elasticSearchService;

    private final Logger logger = LoggerFactory.getLogger(Consumer.class);
    @KafkaListener(topics = "updateByOrderAfterOrder", groupId = "group_search")
    public void listener(ArrayList<ProductListFromOrder> productListFromOrders) throws IOException {
        System.out.println(productListFromOrders);

        ObjectMapper mapper = new ObjectMapper();
        List<ProductListFromOrder> prodList = mapper.convertValue(
                productListFromOrders,
                new TypeReference<List<ProductListFromOrder>>(){}
        );
        UpdateProduct updateProduct = new UpdateProduct();
        for (ProductListFromOrder productListFromOrder : prodList) {
            updateProduct.setProductId(productListFromOrder.getProductId());
            updateProduct.setQuantity(productListFromOrder.getQuantity());
            updateProduct.setMerchantId(productListFromOrder.getMerchantId());
            updateProduct.setCost(productListFromOrder.getCost());
            elasticSearchService.updateQuantityCostAfterOrder(updateProduct);
        }

        logger.info("Consumed!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @KafkaListener(topics = "updateByMerchantAfterUpdate", groupId = "group_search")
    public void listener1(String updateProductsMerch) throws IOException {
        UpdateProduct updateProduct = new UpdateProduct();
        ObjectMapper mapper = new ObjectMapper();
        UpdateProductsMerch updateProductsMerch1 = mapper.readValue(updateProductsMerch.getBytes(), UpdateProductsMerch.class);

        updateProduct.setMerchantId(updateProductsMerch1.getMerchantId());
        updateProduct.setCost(updateProductsMerch1.getCost());
        updateProduct.setQuantity(updateProductsMerch1.getQuantity());
        updateProduct.setProductId(elasticSearchService.findByProductName(updateProductsMerch1.getProductName()).get(0).getProductId());
        elasticSearchService.updateQuantityCostByMerchant(updateProduct);
        System.out.println("Consumed!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @KafkaListener(topics = "AddProduct1", groupId = "group_search")
    public void listener2(String merchant) throws IOException {
        Product product = new Product();

        ObjectMapper mapper = new ObjectMapper();
        Merchant merchant1 = mapper.readValue(merchant.getBytes(), Merchant.class);

        product.setProductId(merchant1.getProductDetails().get(0).getProductId());

        product.setCatName(merchant1.getProductDetails().get(0).getCategoryName());

        product.setProductName(merchant1.getProductDetails().get(0).getProductName());

        product.setProductDescription(merchant1.getProductDetails().get(0).getProductDescription());

        product.setImageUrl(merchant1.getProductDetails().get(0).getImageUrl());

        product.setProductDetails(new ProductDetails(merchant1.getProductDetails().get(0).getLanguage(), merchant1.getProductDetails().get(0).getAuthor(),
                merchant1.getProductDetails().get(0).getPublisher(), merchant1.getProductDetails().get(0).getEdition()));

        List<MerchantDetails> merchantDetailsList = new ArrayList<>();

        merchantDetailsList.add(new MerchantDetails(merchant1.getMerchantName(), merchant1.getMerchantId(),
                merchant1.getProductDetails().get(0).getCost(), merchant1.getProductDetails().get(0).getQuantity(), merchant1.getMerchantRating()));

        if(elasticSearchService.getProduct(merchant1.getProductDetails().get(0).getProductId()) != null) {
            Product product1 = elasticSearchService.getProduct(merchant1.getProductDetails().get(0).getProductId());
            List<MerchantDetails> newList = Stream.concat(product1.getMerchantDetailsList().stream(), merchantDetailsList.stream())
                    .collect(Collectors.toList());
            product.setMerchantDetailsList(newList);
        }
        else
        {
            product.setMerchantDetailsList(merchantDetailsList);
        }

        logger.info("Consumed!!!!!!!!!!!!!!!!!!");
        elasticSearchService.save(product);
    }

}
