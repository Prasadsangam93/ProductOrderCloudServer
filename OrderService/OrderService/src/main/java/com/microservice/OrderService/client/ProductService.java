package com.microservice.OrderService.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name="PRODUCTSERVICE/product")

public interface ProductService {

    @PutMapping("/reducedQuantity/{productId}")
    public ResponseEntity<String> reducedQuantity(@PathVariable  Long productId, @RequestParam int quantity);
}
