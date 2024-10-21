package com.microservice.ProductService.service;


import com.microservice.ProductService.entity.Product;
import com.microservice.ProductService.exception.ProductServiceException;
import com.microservice.ProductService.model.ErrorResponse;
import com.microservice.ProductService.model.ProductRequest;
import com.microservice.ProductService.model.ProductResponse;
import com.microservice.ProductService.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Map<String, Object> addProducts(ProductRequest productRequest) {
        Map<String, Object> response = new HashMap<>();
        Product product = new Product();
        product.setProductId((long) 0);
        product.setProductName(productRequest.getProductName());
        product.setPrice(productRequest.getPrice());
        product.setQuantity(productRequest.getQuantity());
        Product savedProduct = productRepository.save(product);
        response.put("status", HttpStatus.OK.value());
        response.put("message", "Product Details Saved Successfully...!");
        response.put("Product Details", savedProduct);
        return response;
    }

    @Override
    public ProductResponse getProductById(Long productId) throws ProductServiceException {
        Product product= productRepository.findByProductId(productId);
        if (product!=null){
            ProductResponse response = new ProductResponse();
            response.setProductName(product.getProductName());
            response.setQuantity(product.getQuantity());
            response.setPrice(product.getPrice());
            return response;
        }else{
            throw new ProductServiceException("Product not found with productId: "+productId);
        }
    }

    @Override
    public String reducedQuantity(Long productId, int quantity) {
        Product product = productRepository.findByProductId(productId);
        log.info("ProductDetails : {}",product);
        if(product!=null){
            log.info("ProductQuantity :{}",product.getQuantity());
            log.info("quantity :{}",quantity);
            if(product.getQuantity() < quantity){
                log.info("AQuantity :{}",product.getQuantity());
                log.info("Aquantity :{}",quantity);
                throw new ProductServiceException("not having enough quantity");
            }else{
                product.setQuantity(product.getQuantity()-quantity);
                productRepository.save(product);
                log.info("Having enough quantity");
                log.info("Product saved in db");
                return "Order placed successfully...!";

            }
        }else {
            throw  new ProductServiceException("Product not found with productId :"+productId);
        }

    }
}















