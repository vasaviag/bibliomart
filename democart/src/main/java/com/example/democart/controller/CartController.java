package com.example.democart.controller;

import com.example.democart.entity.Cart;
import com.example.democart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/cart")
public class CartController {

    @Autowired
    CartService cartService;

    //get current cart
    @GetMapping("/cartget/{userId}")
    public List<Cart> getCurrentCart(@PathVariable("userId") int userId)
    {
       return cartService.getCurrentCart(userId);
    }

    @GetMapping(value="/getcart/{userId}")
    Cart findById(@PathVariable("userId") int userId)
    {
        return cartService.findById(userId);
    }
    //add item to cart
    @PostMapping("/cartadd")
    public Cart addItemsToCart(@RequestBody Cart cart)
    {
        return this.cartService.addItemsToCart(cart);
    }

  @DeleteMapping("/cartdel/{product_id}")
    public void deleteEntireCart(@PathVariable(value = "product_id") int product_id)
  {
       cartService.deleteEntireCart(product_id);
  }
  @PutMapping("/cartupd/{user_id}/{product_id}")
    public void updateQuantityOfProduct()
  {
      cartService.updateQuantityOfProduct();
  }

  @PostMapping("cartdel/{userId}/{productId}")
  public void deleteProductFromCart()
  {
        cartService.deleteProductFromCart();
  }



}
