package com.microservice.OrderService.Controller;



import com.microservice.OrderService.exceptions.OrderServiceCustomException;
import com.microservice.OrderService.model.OrderRequest;
import com.microservice.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/placeOrder")
    public ResponseEntity<Map<String, Object>> placeOrder(@RequestBody OrderRequest orderRequest) {
        Map<String, Object> response = orderService.placeOrder(orderRequest);
        return ResponseEntity.ok().body(response);

    }
    @GetMapping("/{orderId}")
    public ResponseEntity<Map<String, Object>> getOrderDetails(@PathVariable Long orderId)  throws  OrderServiceCustomException {
       Map<String, Object> result = orderService.getOrderDetails(orderId);
       return ResponseEntity.ok().body(result);
   }

}