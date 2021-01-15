package com.example.demoorder.entity;

import org.springframework.data.annotation.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="order")
public class Order {
    @Id
    private int order_id;
    private int user_id;
    private String timestamp;

    public Order() {
    }

    public Order(int order_id, int user_id, String timestamp) {
        this.order_id = order_id;
        this.user_id = user_id;
        this.timestamp = timestamp;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
