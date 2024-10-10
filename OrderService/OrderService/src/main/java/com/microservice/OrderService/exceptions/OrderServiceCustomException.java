package com.microservice.OrderService.exceptions;




import lombok.Data;

@Data
public class OrderServiceCustomException extends Exception{

    private static final long serialVersionUID = 1L;

    public OrderServiceCustomException() {
        super();

    }

    public OrderServiceCustomException(String message) {
        super(message);

    }

}
