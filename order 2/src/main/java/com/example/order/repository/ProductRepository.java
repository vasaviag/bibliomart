package com.example.order.repository;

import com.example.order.entity.Order;
import com.example.order.entity.ProductId;
import com.example.order.entity.ProductList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<ProductList, ProductId> {

    List<ProductList> findByOrderId(int orderId);

}
