package com.bibliomart.BiblioMartMerchant.model;

import java.util.List;

public class UpdateOrder {
    private List<Integer> merchantId;
    private List<String> productName;
    private List<Integer> quantity;

    public List<Integer> getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(List<Integer> merchantId) {
        this.merchantId = merchantId;
    }

    public List<String> getProductName() {
        return productName;
    }

    public void setProductName(List<String> productName) {
        this.productName = productName;
    }

    public List<Integer> getQuantity() {
        return quantity;
    }

    public void setQuantity(List<Integer> quantity) {
        this.quantity = quantity;
    }
}
