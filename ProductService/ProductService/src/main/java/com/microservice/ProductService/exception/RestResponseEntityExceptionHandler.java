package com.microservice.ProductService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(ProductServiceException.class)
    public ResponseEntity<Map<String,Object>> handlerProductServiceException(ProductServiceException ex) {
   Map map = new HashMap();
   map.put("Message", ex.getMessage());
   map.put("success",false);
   map.put("status",HttpStatus.NOT_FOUND.value());
   map.put(" errorCode",ex.getMessage());
   return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(map);
    }
}
