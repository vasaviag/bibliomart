package com.example.demoelasticsearch.controller;


import com.example.demoelasticsearch.entity.MerchantDetails;
import com.example.demoelasticsearch.entity.UpdateProduct;
import com.example.demoelasticsearch.service.IElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demoelasticsearch.entity.Product;
import java.util.List;

@RestController
@RequestMapping(value = "searchproduct")
public class MainController {

    @Autowired
    IElasticSearchService iElasticSearchService;

    @CrossOrigin
    @PostMapping
    public Product save(@RequestBody Product product)
    {
        return iElasticSearchService.save(product);
    }

    @CrossOrigin
    @GetMapping(value = "/{pid}")
    Product getProduct(@PathVariable("pid") int id)
    {
        return iElasticSearchService.getProduct(id);
    }

    @CrossOrigin
    @GetMapping(value="/getProducts")
    List<Product> getAllProducts()
    {
        return iElasticSearchService.getAllProducts();
    }

    @CrossOrigin
    @GetMapping(value="/productMerchant/{pid}")
    List<MerchantDetails> merchantDetails(@PathVariable("pid") int index)
    {
        return iElasticSearchService.merchantDetails(index);
    }

    @CrossOrigin
    @GetMapping(value="/filter/{category_name}")
    List<Product> filterByCatName(@PathVariable("category_name")  String s)
    {
        return iElasticSearchService.filterByCatName(s);
    }


    @CrossOrigin
    @GetMapping(value="/findAll")
    List<Product> findAll()
    {
        return iElasticSearchService.findAll();
    }

    @CrossOrigin
    @GetMapping(value="/custom/{text}")
    List<Product> getAll(@PathVariable("text") String text)
    {
        return iElasticSearchService.getAll(text);
    }

    @CrossOrigin
    @GetMapping(value="/updateQuantityCost")
    void updateQuantityCost(UpdateProduct updateProduct)
    {
        iElasticSearchService.updateQuantityCostAfterOrder(updateProduct);
    }

}
