package com.example.demoproductmicroservice.entity;


//@Document
public class ProductDetails {

    String language;
    String author;
    String publisher;
    String edition;

    public ProductDetails() {
    }

    public ProductDetails(String language, String author, String publisher, String edition) {
        this.language = language;
        this.author = author;
        this.publisher = publisher;
        this.edition = edition;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
