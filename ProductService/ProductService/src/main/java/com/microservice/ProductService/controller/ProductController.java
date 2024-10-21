package com.microservice.ProductService.controller;


import com.microservice.ProductService.model.ErrorResponse;
import com.microservice.ProductService.model.ProductRequest;
import com.microservice.ProductService.model.ProductResponse;
import com.microservice.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;


    @PostMapping("saveProduct")
    public ResponseEntity<Map<String, Object>> saveProducts(@RequestBody ProductRequest productRequest) {
        Map<String, Object> result = productService.addProducts(productRequest);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductResponse> getProductDetails(@PathVariable Long productId) {
            ProductResponse response = productService.getProductById(productId);
            return ResponseEntity.ok().body(response);
    }

    @PutMapping("/reducedQuantity/{productId}")
    public  ResponseEntity<String> reducedQuantity(@PathVariable  Long productId, @RequestParam int quantity){
        String result = productService.reducedQuantity(productId, quantity);
        return ResponseEntity.ok().body(result);

    }

    }
