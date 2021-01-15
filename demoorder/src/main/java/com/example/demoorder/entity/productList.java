package com.example.demoorder.entity;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "productList")
@IdClass(ProductId.class)
public class productList {
    @Id
    private int order_id;
    @Id
    private int product_id;
    private int quantity;
    private double cost;
    private int merchant_id;

    public productList() {
    }

    public productList(int order_id, int product_id, int quantity, double cost, int merchant_id) {
        this.order_id = order_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.cost = cost;
        this.merchant_id = merchant_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
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

    public int getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(int merchant_id) {
        this.merchant_id = merchant_id;
    }
}
