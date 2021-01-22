package com.example.demoproductmicroservice.repository;
import com.example.demoproductmicroservice.entity.MerchantDetails;
import com.example.demoproductmicroservice.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface IMongoRepository extends MongoRepository<Product,Integer> {

    Product findByProductId(int id);
    List<Product> findByProductName(String pname);

}
