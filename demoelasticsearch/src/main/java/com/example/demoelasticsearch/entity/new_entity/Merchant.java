package com.example.demoelasticsearch.entity.new_entity;

import java.util.List;

public class Merchant {

    private int merchantId;
    private String merchantName;
    private double merchantRating;
    private String email;
    private String password;
    private List<ProductDetailsMerch> productDetails;

    public Merchant() {
    }

    public Merchant(int merchantId, String merchantName, double merchantRating, String email, String password, List<ProductDetailsMerch> productDetails) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.merchantRating = merchantRating;
        this.email = email;
        this.password = password;
        this.productDetails = productDetails;
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

    public double getMerchantRating() {
        return merchantRating;
    }

    public void setMerchantRating(double merchantRating) {
        this.merchantRating = merchantRating;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<ProductDetailsMerch> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetailsMerch> productDetails) {
        this.productDetails = productDetails;
    }
}
