package com.example.democart.repository;

import com.example.democart.entity.Cart;

import com.example.democart.entity.CartId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart,CartId> {
    List<Cart> findByUserId(int userId);
    void deleteByUserIdAndProductId(int userId, int productId);
    List<Cart> findByUserIdAndProductId(int userId,int productId);
}
