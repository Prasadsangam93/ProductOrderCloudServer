package com.microservice.OrderService.service;

import com.microservice.OrderService.model.OrderRequest;

import java.util.Map;

public interface OrderService {

    Map<String, Object> placeOrder(OrderRequest orderRequest);


}
