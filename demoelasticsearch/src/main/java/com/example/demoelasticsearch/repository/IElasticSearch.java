package com.example.demoelasticsearch.repository;

import com.example.demoelasticsearch.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IElasticSearch extends ElasticsearchRepository<Product,String> {

    Product findByProductId(int id);
    List<Product> findByCatName(String s);
    List<Product> findByProductName(String pname);
}
