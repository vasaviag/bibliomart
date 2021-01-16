package com.bibliomart.BiblioMartMerchant.services;

import com.bibliomart.BiblioMartMerchant.model.*;

import java.util.List;

public interface MerchantService {
    Id register(Merchant mer);
    Id login(Merchant mer);
    Merchant addProduct(Merchant mer);
    List<ProductDetails> viewProduct(int merId);
    void updateAfterOrder(UpdateOrder updateOrderdetails);
    void updateByMerchant(UpdateByMerchant updateMerchantProducts);

}
