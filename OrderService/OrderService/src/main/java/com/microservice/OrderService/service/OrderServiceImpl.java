package com.microservice.OrderService.service;

import com.microservice.OrderService.client.ProductService;
import com.microservice.OrderService.entity.Order;
import com.microservice.OrderService.exceptions.OrderServiceCustomException;
import com.microservice.OrderService.model.OrderRequest;
import com.microservice.OrderService.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class OrderServiceImpl implements  OrderService{


    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductService productService;

    @Override

    public Map<String, Object> placeOrder(OrderRequest orderRequest) {
        log.info("before placing order");

        productService.reducedQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
        log.info("after checking product availability for product:{}", orderRequest.getProductId());





        Map<String, Object> response = new HashMap<>();
        Order order = new Order();

        // Set order details from the request
        order.setProductId(orderRequest.getProductId());
        order.setQuantity(orderRequest.getQuantity());
        order.setPrice(orderRequest.getPrice());
       order.setOrderDate(Instant.now());
       order.setOrderStatus("CREADIT");

       log.info("Order Placed");


        // Save the order
        Order savedOrder = orderRepository.save(order);
        log.info("product saved successfully after placing order");



        // Prepare the response
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Order Details Saved Successfully!");
        response.put("orderDetails", savedOrder); // Removed space in the key

        return response;
    }


}
