package com.example.order.entity;


import javax.persistence.*;

@Entity
@Table(name="order1")
public class Order {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int orderId;
    private int userId;
    private String timestamp;

    public Order() {
    }

    public Order(int orderId, int userId, String timestamp) {
        this.orderId = orderId;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
