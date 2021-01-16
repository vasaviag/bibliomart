package com.bibliomart.BiblioMartMerchant.repository;

import com.bibliomart.BiblioMartMerchant.model.Merchant;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MerchantRepository extends MongoRepository<Merchant,Integer> {
    long count();
    Merchant findByEmail(String str);
    List<Merchant> findByMerchantId(int id);
}
