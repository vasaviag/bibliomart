package com.example.order.entity;

import java.io.Serializable;
import java.util.Objects;

public class ProductId implements Serializable {

    private int orderId;
    private int productId;
    private int merchantId;

    public ProductId() {
    }

    public ProductId(int orderId, int productId) {
        this.orderId = orderId;
        this.productId = productId;
        this.merchantId = merchantId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductId product = (ProductId) o;
        return (orderId==product.orderId) &&
                (productId==product.productId) &&
                (merchantId==product.merchantId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(orderId,productId,merchantId);
    }
}
