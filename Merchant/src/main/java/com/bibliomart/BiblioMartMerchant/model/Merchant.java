package com.bibliomart.BiblioMartMerchant.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "merchant")
public class Merchant {
    private int merchantId;
    private String merchantName;
    private int getMerchantRating;
    @Id
    private String email;
    private String password;
    private List<ProductDetails> productDetails;

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

    public int getGetMerchantRating() {
        return getMerchantRating;
    }

    public void setGetMerchantRating(int getMerchantRating) {
        this.getMerchantRating = getMerchantRating;
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

    public List<ProductDetails> getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(List<ProductDetails> productDetails) {
        this.productDetails = productDetails;
    }
}
