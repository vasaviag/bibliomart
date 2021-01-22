package com.example.demoproductmicroservice.entity.new_entity;

public class UpdateProductsMerch {
    private int MerchantId;
    private String productName;
    private int quantity;
    private double cost;
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
    public int getMerchantId() {
        return MerchantId;
    }
    public void setMerchantId(int merchantId) {
        MerchantId = merchantId;
    }
}
