package com.bibliomart.BiblioMartMerchant.Controller;

import com.bibliomart.BiblioMartMerchant.model.*;
import com.bibliomart.BiblioMartMerchant.services.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "merchant")
public class MyController {

    @Autowired
    MerchantService merchantService;

    @PostMapping(value = "/register")
    public Id register(@RequestBody Merchant mer){
        return merchantService.register(mer);
    }

    @PostMapping(value = "/login")
    public Id login(@RequestBody Merchant mer){
        return merchantService.login(mer);
    }

    @PostMapping(value = "/addProduct")
    public Merchant AddProduct(@RequestBody Merchant mer) {
        return merchantService.addProduct(mer);
    }

    @GetMapping(value = "/view/{merchantId}")
    public List<ProductDetails> view(@PathVariable int merchantId){
        return merchantService.viewProduct(merchantId);
    }


    @PostMapping(value="/updateAfterOrder")
    public void updateAfterOrder(@RequestBody UpdateOrder updateOrderdetails){
        System.out.println(updateOrderdetails);
        merchantService.updateAfterOrder(updateOrderdetails);
    }

    @PostMapping(value = "/updateByMerchant")
    public void updateByMerchant(@RequestBody UpdateByMerchant updateMerchantProducts){
        merchantService.updateByMerchant(updateMerchantProducts);
    }


}
