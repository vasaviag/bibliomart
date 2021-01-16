package com.example.democart.services;

import com.example.democart.entity.Cart;

import java.util.List;

public interface CartService {

     List<Cart> findAll();
     void deleteEntireCart(int id);
     Cart save(Cart cart);
     List<Cart>getCurrentCart();
     Cart updateQuantityOfProduct(int userId,int productId);
     List<Cart> findByUserId(int id);
     void deleteByUserIdAndProductId(int userId, int productId);
     Cart addItemsToCart(Cart cart);
     Cart findByUserIdAndProductId(int userId, int productId);

}
