package com.example.demoelasticsearch.service.impl;

import com.example.demoelasticsearch.entity.MerchantDetails;
import com.example.demoelasticsearch.entity.Product;
import com.example.demoelasticsearch.entity.UpdateProduct;
import com.example.demoelasticsearch.repository.IElasticSearch;
import com.example.demoelasticsearch.service.IElasticSearchService;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ElasticSearchServiceImpl implements IElasticSearchService {

    @Autowired
    ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    IElasticSearch iElasticSearch;

    @Override
    public Product save(Product product)
    {
        return iElasticSearch.save(product);
    }

    @Override
    public Product getProduct(int id)
    {
        return iElasticSearch.findByProductId(id);
    }

    @Override
    public List<MerchantDetails> merchantDetails(int index)
    {
        List<MerchantDetails> merchantDetailsList=new ArrayList<>();
        Iterable<MerchantDetails> merchantDetailsIterable=iElasticSearch.findByProductId(index).getMerchantDetailsList();
        merchantDetailsIterable.forEach(merchantDetailsList::add);
        return merchantDetailsList;
    }

    @Override
    public List<Product> getAllProducts()
    {
        final Pageable pageable=PageRequest.of(0,2);
        List<Product> list=new ArrayList<>();
        Iterable<Product> productIterable=iElasticSearch.findAll(pageable);
        for(Product product : productIterable){
            list.add(product);
        }
        return list;
    }

    @Override
    public List<Product> filterByCatName(String s) {
        List<Product> list=new ArrayList<>();
        Iterable<Product> productIterable=iElasticSearch.findByCatName(s);
        productIterable.forEach(list::add);
        return list;
    }

    @Override
    public List<Product> findByProductName(String pname)
    {
        List<Product> list=new ArrayList<>();
        Iterable<Product> productIterable=iElasticSearch.findByProductName(pname);
        productIterable.forEach(list::add);
        return list;
    }

    @Override
    public List<Product> findAll()
    {
        List<Product> list=new ArrayList<>();
        Iterable<Product> productIterable=iElasticSearch.findAll();
        productIterable.forEach(list::add);
        return list;
    }

    @Override
    public List<Product> getAll(String text) {

        text=text.replace(" ","+");
        QueryBuilder queryBuilder = QueryBuilders.boolQuery().should(QueryBuilders.queryStringQuery(text)
                .lenient(true).field("productName").field("catName").field("productDescription"))
                .should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true).field("productName").field("catName").field("productDescription"));

          NativeSearchQuery nativeSearchQuery= new NativeSearchQueryBuilder().withQuery(queryBuilder).build();
          List<Product> productList = elasticsearchRestTemplate.queryForList(nativeSearchQuery, Product.class, IndexCoordinates.of("searchbooks"));
          return productList;
    }

    @Override
    public void updateQuantityCostByMerchant(UpdateProduct updateProduct)
    {
        Product product = getProduct(updateProduct.getProductId());
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
