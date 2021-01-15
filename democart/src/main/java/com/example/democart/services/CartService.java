package com.example.democart.services;

import com.example.democart.entity.Cart;

import java.util.List;

public interface CartService {

     List<Cart> findAll();
     void deleteEntireCart(int id);
     Cart save(Cart cart);
     List<Cart>getCurrentCart(int userId);
     void updateQuantityOfProduct();
     void deleteProductFromCart();
     Cart findById(int id);

    Cart addItemsToCart(Cart cart);

}
