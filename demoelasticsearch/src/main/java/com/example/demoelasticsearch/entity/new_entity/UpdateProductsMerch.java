package com.example.demoelasticsearch.entity.new_entity;

public class UpdateProductsMerch {

    private int merchantId;
    private String productName;
    private int quantity;
    private double cost;

    public UpdateProductsMerch() {
    }

    public UpdateProductsMerch(int merchantId, String productName, int quantity, int cost) {
        this.merchantId = merchantId;
        this.productName = productName;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
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

    public void setCost(int cost) {
        this.cost = cost;
    }
}
