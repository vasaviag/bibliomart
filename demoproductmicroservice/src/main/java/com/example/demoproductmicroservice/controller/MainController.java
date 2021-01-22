package com.example.demoproductmicroservice.controller;


import com.example.demoproductmicroservice.entity.MerchantDetails;
import com.example.demoproductmicroservice.entity.Product;
import com.example.demoproductmicroservice.entity.UpdateProduct;
import com.example.demoproductmicroservice.services.impl.ProductMongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value="myproduct")
public class MainController {

    @Autowired
    ProductMongoService productMongoService; //needs to go through the interface

    @CrossOrigin
    @PostMapping
    public Product save(@RequestBody Product product)
    {
        return productMongoService.save(product);
    }

    @CrossOrigin
    @GetMapping(value="/{pid}")
    Product getProduct(@PathVariable("pid") int id)
    {
        System.out.println(id);
        System.out.println(productMongoService.getProduct(id).toString());
        return productMongoService.getProduct(id);
    }

    @CrossOrigin
    @GetMapping(value="/getProducts")
    List<Product> getAllProducts()
    {
        return productMongoService.topProducts();
    }

    @CrossOrigin
    @GetMapping(value="/productMerchant/{pid}")
    List<MerchantDetails> merchantDetails(@PathVariable("pid") int index)
    {
        return productMongoService.merchantDetails(index);
    }

    @CrossOrigin
    @GetMapping(value="/findAll")
    List<Product> findAll()
    {
        return productMongoService.findAll();
    }

    @CrossOrigin
    @PutMapping(value="/updateQuantityCost")
    void updateQuantityCost(@RequestBody UpdateProduct updateProduct)
    {
        productMongoService.updateQuantityCostAfterOrder(updateProduct);
    }

}
