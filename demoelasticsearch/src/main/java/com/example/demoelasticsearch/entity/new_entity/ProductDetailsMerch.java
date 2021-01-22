package com.example.demoelasticsearch.entity.new_entity;

public class ProductDetailsMerch {

    private String productName;
    private String categoryName;
    private double cost;
    private int quantity;
    private int productId;
    private String productDescription;
    private String edition;
    private String author;
    private String publisher;
    private String language;
    private String imageUrl;

    public ProductDetailsMerch() {
    }

    public ProductDetailsMerch(String productName, String categoryName, double cost, int quantity, int productId, String productDescription, String edition, String author, String publisher, String language, String imageUrl) {
        this.productName = productName;
        this.categoryName = categoryName;
        this.cost = cost;
        this.quantity = quantity;
        this.productId = productId;
        this.productDescription = productDescription;
        this.edition = edition;
        this.author = author;
        this.publisher = publisher;
        this.language = language;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
