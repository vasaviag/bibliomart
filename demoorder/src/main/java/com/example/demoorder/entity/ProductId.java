package com.example.demoorder.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductId implements Serializable {
    private int order_id;
    private int product_id;

    public ProductId() {
    }

    public ProductId(int order_id, int product_id) {
        this.order_id = order_id;
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId productId = (ProductId) o;
        return (order_id==productId.order_id) &&
                (product_id==productId.product_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_id,product_id);
    }
}
