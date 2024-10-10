package com.microservice.ProductService.exception;

public class ProductServiceException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public ProductServiceException() {
        super();

    }

    public ProductServiceException(String message) {
        super(message);

    }

}
