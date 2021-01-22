package com.example.demoproductmicroservice.services.impl;


import com.example.demoproductmicroservice.entity.MerchantDetails;
import com.example.demoproductmicroservice.entity.Product;
import com.example.demoproductmicroservice.entity.UpdateProduct;
import com.example.demoproductmicroservice.repository.IMongoRepository;
import com.example.demoproductmicroservice.services.IMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;



@Service
public class ProductMongoService implements IMongoService {

    @Autowired
    IMongoRepository iMongoRepository;

    @Override
    public Product save(Product product)
    {
        return iMongoRepository.save(product);
    }


    @Override
    public Product getProduct(int id)
    {
        return iMongoRepository.findByProductId(id);
    }

    @Override
    public List<Product> topProducts()
    {
        final Pageable pageable=PageRequest.of(0,10);
        List<Product> list = new ArrayList<>();
        Iterable<Product> productIterable=iMongoRepository.findAll(pageable);
        for (Product product :
                productIterable) {
            list.add(product);
        }
        return list;
    }

    @Override
    public List<MerchantDetails> merchantDetails(int index)
    {
        List<MerchantDetails> merchantDetailsList=new ArrayList<>();
        Iterable<MerchantDetails> merchantDetailsIterable=iMongoRepository.findByProductId(index).getMerchantDetailsList();
        merchantDetailsIterable.forEach(merchantDetailsList::add);
        return merchantDetailsList;
    }


    @Override
    public List<Product> findAll()
    {
        List<Product> productList=new ArrayList<>();
        Iterable<Product> productIterable=iMongoRepository.findAll();
        productIterable.forEach(productList::add);
        return productList;
    }

    @Override
    public List<Product> findByProductName(String pname)
    {
        List<Product> productList=new ArrayList<>();
        Iterable<Product> productIterable=iMongoRepository.findByProductName(pname);
        productIterable.forEach(productList::add);
        return productList;
    }

    @Override
    public void updateQuantityCostByMerchant(UpdateProduct updateProduct)
    {
        Product product = getProduct(updateProduct.getProductId());
//        System.out.println(product.getMerchantDetailsList().get(0).getMerchantName());
        List<MerchantDetails> merchantDetailsList = product.getMerchantDetailsList();
        for (MerchantDetails merch : merchantDetailsList)
        {
            if(merch.getMerchantId() == updateProduct.getMerchantId())
            {
                merch.setQuantity(updateProduct.getQuantity());
                merch.setCost(updateProduct.getCost());
                break;
            }
        }
        product.setMerchantDetailsList(merchantDetailsList);
        save(product);
    }

    public void updateQuantityCostAfterOrder(UpdateProduct updateProduct)
    {
        Product product = getProduct(updateProduct.getProductId());
//        System.out.println(product.getMerchantDetailsList().get(0).getMerchantName());
        List<MerchantDetails> merchantDetailsList = product.getMerchantDetailsList();
        for (MerchantDetails merch : merchantDetailsList)
        {
            if(merch.getMerchantId() == updateProduct.getMerchantId())
            {
                merch.setQuantity(merch.getQuantity() - updateProduct.getQuantity());
                break;
            }
        }
        product.setMerchantDetailsList(merchantDetailsList);
        save(product);
    }
}
