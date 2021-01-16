package com.bibliomart.BiblioMartMerchant.repository;

import com.bibliomart.BiblioMartMerchant.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products,Integer> {
    long count();
    Products findByproductName(String str);
}
