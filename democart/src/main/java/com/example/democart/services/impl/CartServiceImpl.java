package com.example.democart.services.impl;

import com.example.democart.entity.Cart;
import com.example.democart.repository.CartRepository;
import com.example.democart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart>getCurrentCart(int userId)
    {
        Iterable<Cart> cartIterable= cartRepository.findAll();
        List<Cart> cartList=new ArrayList<>();
        cartIterable.forEach(cartList::add);
        return cartList;
    }

    @Override
    public Cart save(Cart cart) {

        return cartRepository.save(cart);

    }

    @Override
    public void deleteEntireCart(int id) {
        cartRepository.deleteById(id);
        System.out.println("Deleted");

    }

    @Override
    public List<Cart> findAll() {
        Iterable<Cart> cartIterable= cartRepository.findAll();
        List<Cart> cartList=new ArrayList<>();
        cartIterable.forEach(cartList::add);
        return cartList;
    }

    public void updateQuantityOfProduct()
    {
        // int val;
        // get the data from database
        // quantity + val;
        // save
        Cart cartData=new Cart();

        int val=1;  //dummy variable for testing... value will come from front end ( + and - button)
        int quantity=cartData.getQuantity();
        quantity=quantity+val;
        cartData.setQuantity(quantity);
    }

    @Override
    public void deleteProductFromCart() {

    }

    @Override
    public Cart findById(int id) {
        return cartRepository.findById(id).get();
    }

    @Override
    public Cart addItemsToCart(Cart cart)
    {
        return cartRepository.save(cart);
    }


}
