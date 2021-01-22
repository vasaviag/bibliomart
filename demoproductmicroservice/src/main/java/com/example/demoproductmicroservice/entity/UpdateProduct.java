package com.example.demoproductmicroservice.entity;

public class UpdateProduct {

    private int productId;
    private int merchantId;
    private int quantity;
    private double cost;

    public UpdateProduct() {
    }

    public UpdateProduct(int productId, int merchantId, int quantity, double cost) {
        this.productId = productId;
        this.merchantId = merchantId;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
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

    public void setCost(double cost) {
        this.cost = cost;
    }
}
