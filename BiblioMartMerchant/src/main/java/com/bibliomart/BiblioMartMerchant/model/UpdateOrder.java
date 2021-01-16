package com.bibliomart.BiblioMartMerchant.model;

import java.util.List;

public class UpdateOrder {
    List<Integer> merchantId;
    List<Integer> productId;
    List<Integer> quantity;

    public List<Integer> getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(List<Integer> merchantId) {
        this.merchantId = merchantId;
    }

    public List<Integer> getProductId() {
        return productId;
    }

    public void setProductId(List<Integer> productId) {
        this.productId = productId;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
