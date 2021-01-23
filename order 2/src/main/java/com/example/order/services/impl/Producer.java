package com.example.order.services.impl;

import com.example.order.entity.ProductList;
import com.example.order.services.ProducerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Producer implements ProducerService {

    @Autowired
    private KafkaTemplate<String, List<ProductList>> kafkaTemplate1;

    private static final Logger logger = LoggerFactory.getLogger(Producer.class);

    protected static final String UPDATE_BY_ORDER_TO_PRODUCT_AFTER_ORDER = "updateByOrderToProductAfterOrder";
    protected static final String UPDATE_BY_ORDER_TO_ELASTIC_AFTER_ORDER = "updateByOrderToElasticAfterOrder";
    protected static final String UPDATE_BY_ORDER_TO_MERCHANT_AFTER_ORDER = "updateByOrderToMerchantAfterOrder";


    public void sendMessageToProductAfterOrder(List<ProductList> productList)
    {
        logger.info(String.format("#### -> Producing Message -> %s",productList.toString()));
        kafkaTemplate1.send(UPDATE_BY_ORDER_TO_PRODUCT_AFTER_ORDER,productList);
        logger.info(String.format("Sent to Product Microservice"));
    }

    public void sendMessageToMerchantSearchAfterOrder(List<ProductList> productList) {
        logger.info(String.format("#### -> Producing message -> %s", productList.toString()));
        kafkaTemplate1.send(UPDATE_BY_ORDER_TO_MERCHANT_AFTER_ORDER, productList);
        logger.info(String.format("Sent to Merchant MicroService"));
    }

    public void sendMessageToElasticSearchAfterOrder(List<ProductList> productList) {
        logger.info(String.format("#### -> Producing message -> %s", productList.toString()));
        kafkaTemplate1.send(UPDATE_BY_ORDER_TO_ELASTIC_AFTER_ORDER, productList);
        logger.info(String.format("Sent to Elastic Search MicroService"));
    }
}
