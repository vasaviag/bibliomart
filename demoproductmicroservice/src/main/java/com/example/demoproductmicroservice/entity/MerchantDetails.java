package com.example.demoproductmicroservice.entity;



//@Document
public class MerchantDetails {

    String merchantName;
    int merchantId;
    double cost;
    int quantity;
    double merchantRating;

    public MerchantDetails() {
    }

    public MerchantDetails(String merchantName, int merchantId, double cost, int quantity, double merchantRating) {
        this.merchantName = merchantName;
        this.merchantId = merchantId;
        this.cost = cost;
        this.quantity = quantity;
        this.merchantRating = merchantRating;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(double merchantRating) {
        this.merchantRating = merchantRating;
    }
}
