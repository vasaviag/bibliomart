package com.bibliomart.BiblioMartMerchant.services.impl;

import com.bibliomart.BiblioMartMerchant.model.*;
import com.bibliomart.BiblioMartMerchant.repository.MerchantRepository;
import com.bibliomart.BiblioMartMerchant.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MerchantServiceImpl implements MerchantService {

    private static final int SUCCESS_CODE = 200;
    private static final int ERROR_CODE = 400;

    @Autowired
    MerchantRepository merchantRepository;

    @Override
    public Id register(Merchant mer){
        mer.setMerchantId((int)merchantRepository.count()+1);
        Id id = new Id();
        try {
            Merchant merchantdata = merchantRepository.insert(mer);
            id.setMerchantId(merchantdata.getMerchantId());
            id.setStatus(SUCCESS_CODE);
        }catch (Exception exp){
            id.setMerchantId(-1);
            id.setStatus(ERROR_CODE);
        }
        return id;
    }

    @Override
    public Id login(Merchant mer){
        Id id = new Id();
        try {
            Merchant merchantdata = merchantRepository.findByEmail(mer.getEmail());
            if (merchantdata.getPassword().equals(mer.getPassword())) {
                id.setMerchantId(merchantdata.getMerchantId());
                id.setStatus(SUCCESS_CODE);
            } else {
                id.setMerchantId(-1);
                id.setStatus(ERROR_CODE);
            }
        }catch(Exception exp){
            id.setMerchantId(-1);
            id.setStatus(ERROR_CODE);
        }
        return id;
    }

    @Override
    public Merchant addProduct(Merchant mer){
            int merId = mer.getMerchantId();
            List<Merchant> merdata = merchantRepository.findByMerchantId(merId);
            Merchant merchant = merdata.get(0);
            try {
                int flag = 0;
                List<ProductDetails> productDetailsList = merchant.getProductDetails();
                for(int i=0;i<productDetailsList.size();i++){
                    if(productDetailsList.get(i).getProductId() == mer.getProductDetails().get(0).getProductId()){
                        flag = 1;
                    }
                }
                if(flag == 0) {
                    productDetailsList.add(mer.getProductDetails().get(0));
                    merchant.setProductDetails(productDetailsList);
                }
            }catch (Exception exp) {
                merchant.setProductDetails(mer.getProductDetails());
            }
            merchantRepository.save(merchant);
            return merchant;

    }

    @Override
    public List<ProductDetails> viewProduct(int merId){
        List<Merchant> merdata = merchantRepository.findByMerchantId(merId);
        return merdata.get(0).getProductDetails();
    }

    @Override
    public void updateAfterOrder(UpdateOrder updateOrderdetails){
        for(int i=0;i<updateOrderdetails.getMerchantId().size();i++){
            List<Merchant> merdata =  merchantRepository.findByMerchantId(updateOrderdetails.getMerchantId().get(i));
            Merchant merchant = merdata.get(0);
            double rating = merchant.getGetMerchantRating();
            List<ProductDetails> productDetails = merchant.getProductDetails();
            for(ProductDetails productDetail:productDetails){
                if(productDetail.getProductId() == updateOrderdetails.getProductId().get(i)){
                    productDetail.setQuantity(productDetail.getQuantity()-updateOrderdetails.getQuantity().get(i));
                    if(rating < 3) rating = rating + (0.01*updateOrderdetails.getQuantity().get(i));
                    else if(rating < 4) rating = rating + (0.005*updateOrderdetails.getQuantity().get(i));
                    else if(rating < 5) rating = rating + (0.001*updateOrderdetails.getQuantity().get(i));
                    merchant.setGetMerchantRating(rating);
                    break;
                }
            }
            merchant.setProductDetails(productDetails);
            merchantRepository.save(merchant);
        }
    }

    @Override
    public void updateByMerchant(UpdateByMerchant updateMerchantProducts){
        List<Merchant> merdata =  merchantRepository.findByMerchantId(updateMerchantProducts.getMerchantId());
        Merchant merchant = merdata.get(0);
        List<ProductDetails> productDetails = merchant.getProductDetails();
        for(ProductDetails productDetail:productDetails){
            if(productDetail.getProductId() == merdata.get(0).getProductDetails().get(0).getProductId()){
                productDetail.setQuantity(updateMerchantProducts.getQuantity());
                productDetail.setCost(updateMerchantProducts.getCost());
                break;
            }
        }
        merchant.setProductDetails(productDetails);
        merchantRepository.save(merchant);
    }
}