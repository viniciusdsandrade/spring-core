package com.restful.transactiondemo.Controller;

import com.restful.transactiondemo.Service.OrderService;
import com.restful.transactiondemo.dto.OrderRequest;
import com.restful.transactiondemo.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<OrderResponse> placeOrder(@RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.placeOrder(request));
    }
}
