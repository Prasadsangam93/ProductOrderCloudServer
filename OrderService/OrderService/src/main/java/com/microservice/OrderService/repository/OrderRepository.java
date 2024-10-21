package com.microservice.OrderService.repository;

import com.microservice.OrderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {




    Order findByProductId(Long productId);
    //Order findByOrderId( Long OrderId);





}
