package com.example.demoelasticsearch.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;

@Document(indexName = "searchbooks")
public class Product {

    @Id
    private int productId;
    private String productName;
    private String catName;
    private String productDescription;
    private String imageUrl;
    private ProductDetails productDetails;
    private List<MerchantDetails> merchantDetailsList;

    public Product() {
    }

    public Product(int productId, String productName, String catName, String productDescription, String imageUrl, ProductDetails productDetails, List<MerchantDetails> merchantDetailsList) {
        this.productId = productId;
        this.productName = productName;
        this.catName = catName;
        this.productDescription = productDescription;
        this.imageUrl = imageUrl;
        this.productDetails = productDetails;
        this.merchantDetailsList = merchantDetailsList;
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

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public ProductDetails getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(ProductDetails productDetails) {
        this.productDetails = productDetails;
    }

    public List<MerchantDetails> getMerchantDetailsList() {
        return merchantDetailsList;
    }

    public void setMerchantDetailsList(List<MerchantDetails> merchantDetailsList) {
        this.merchantDetailsList = merchantDetailsList;
    }
}
