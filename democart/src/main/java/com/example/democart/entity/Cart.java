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
    private int user_id;
    private int product_id;
    private int quantity;
    private double cost;

    public Cart() {
    }

    public Cart(int user_id, int product_id, int quantity, double cost) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity = quantity;
        this.cost = cost;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
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
}
