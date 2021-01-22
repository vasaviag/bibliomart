package com.example.demoelasticsearch.entity.new_entity;

public class ProductListFromOrder {

    private int orderId;
    private int productId;
    private String productName;
    private int quantity;
    private double cost;
    private int merchantId;
    private String merchantName;

    public ProductListFromOrder() {
    }

    public ProductListFromOrder(int orderId, int productId, String productName, int quantity, double cost, int merchantId, String merchantName) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.cost = cost;
        this.merchantId = merchantId;
        this.merchantName = merchantName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(long cost) {
        this.cost = cost;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }
}
