package com.example.democart.entity;

import java.io.Serializable;
import java.util.Objects;

public class CartId implements Serializable {
    private int user_id;
    private int product_id;

    public CartId() {
    }

    public CartId(int user_id, int product_id) {
        this.user_id = user_id;
        this.product_id = product_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CartId cartId = (CartId) o;
        return (user_id==cartId.user_id) &&
                (product_id==cartId.product_id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(user_id,product_id);
    }
}
