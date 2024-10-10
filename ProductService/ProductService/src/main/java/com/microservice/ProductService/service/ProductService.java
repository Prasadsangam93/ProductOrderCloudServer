package com.microservice.ProductService.service;


import com.microservice.ProductService.exception.ProductServiceException;
import com.microservice.ProductService.model.ErrorResponse;
import com.microservice.ProductService.model.ProductRequest;
import com.microservice.ProductService.model.ProductResponse;


import java.util.Map;

public interface ProductService {

    Map<String, Object> addProducts(ProductRequest productRequest);

    ErrorResponse getProductById(Long productId) throws ProductServiceException;


    String reducedQuantity(Long productId, int quantity);
}