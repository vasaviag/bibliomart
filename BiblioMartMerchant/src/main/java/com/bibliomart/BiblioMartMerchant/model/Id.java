package com.bibliomart.BiblioMartMerchant.model;

public class Id {
    private int MerchantId;
    private int status;

    public int getMerchantId() {
        return MerchantId;
    }

    public void setMerchantId(int merchantId) {
        MerchantId = merchantId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
