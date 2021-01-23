package com.example.order.repository;

import com.example.order.entity.Order;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order,Integer> {

    List<Order>findByUserId(int userId);

}
