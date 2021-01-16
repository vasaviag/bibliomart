package com.example.democart.services.impl;

import com.example.democart.entity.Cart;
import com.example.democart.entity.CartId;
import com.example.democart.repository.CartRepository;
import com.example.democart.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public List<Cart>getCurrentCart()
    {
        Iterable<Cart> cartIterable= cartRepository.findAll();
        List<Cart> cartList=new ArrayList<>();
        cartIterable.forEach(cartList::add);
        return cartList;
    }


    Cart cartData=new Cart();

    @Override
    public Cart save(Cart cart) {

        return cartRepository.save(cart);

    }

    @Override
    public void deleteEntireCart(int userId) {
        List<Cart> carts = findByUserId(userId);
        for (Cart cart : carts)
        {
            cartRepository.deleteById(new CartId(cart.getuserId(), cart.getproductId()));
        }
        //System.out.println("Deleted");
    }

    @Override
    public List<Cart> findAll() {
        Iterable<Cart> cartIterable= cartRepository.findAll();
        List<Cart> cartList=new ArrayList<>();
        cartIterable.forEach(cartList::add);
        return cartList;
    }


    @Override
    public List<Cart> findByUserId(int userId) {
        Iterable<Cart> cartIterable = cartRepository.findByUserId(userId);
        List<Cart> cartList=new ArrayList<>();
        cartIterable.forEach(cartList::add);
        return cartList;
    }

    @Transactional(readOnly = false)
    @Override
    public void deleteByUserIdAndProductId(int userId, int productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);

    }

    @Override
    public Cart addItemsToCart(Cart cart)
    {
        return cartRepository.save(cart);
    }

    @Override
    public Cart findByUserIdAndProductId(int userId, int productId) {
        List<Cart> cart = cartRepository.findByUserIdAndProductId(userId,productId);
        return cart.get(0);
    }
    @Override
    public Cart updateQuantityOfProduct(int userId, int productId)
    {
        // int val;
        // get the data from database
        // quantity + val;
        // save

        int val=1;  //dummy variable for testing... value will come from front end ( + and - button)
        List<Cart>list=cartRepository.findByUserIdAndProductId(userId,productId);
        int q=list.get(0).getQuantity();
        list.get(0).setQuantity(q+val);
        cartRepository.save(list.get(0));
        return list.get(0);

    }


}
