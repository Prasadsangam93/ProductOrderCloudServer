package com.microservice.OrderService.client;

import com.microservice.OrderService.model.ProductDetails;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@FeignClient(name="PRODUCTSERVICE/product")

public interface ProductService {

    @PutMapping("/reducedQuantity/{productId}")
    public ResponseEntity<String> reducedQuantity(@PathVariable Long productId, @RequestParam int quantity);


    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getPaymentDetails(@PathVariable Long orderId) ;
}

