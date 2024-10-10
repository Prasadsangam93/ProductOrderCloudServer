package com.microservice.OrderService.decoder;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.OrderService.exceptions.ErrorResponse;
import com.microservice.OrderService.exceptions.OrderServiceCustomException;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;

public class CustomDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            ErrorResponse errorResponse = objectMapper.readValue(response.body().asInputStream(), ErrorResponse.class);
            return new OrderServiceCustomException(errorResponse.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new OrderServiceCustomException("ServiceInternalException...");
    }
    }

