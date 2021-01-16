package com.example.democart.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "cart")
@IdClass(CartId.class)
public class Cart {
    @Id
    private int userId;
    @Id
    private int productId;
    private int quantity;
    private double cost;

    public Cart() {
    }

    public Cart(int userId, int productId, int quantity, double cost) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
        this.cost = cost;
    }


    public int getuserId() {
        return userId;
    }

    public void setuserId(int userId) {
        this.userId = userId;
    }

    public int getproductId() {
        return productId;
    }

    public void setproductId(int productId) {
        this.productId = productId;
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
