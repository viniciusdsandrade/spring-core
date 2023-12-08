package com.restful.transactiondemo.Service;


import com.restful.transactiondemo.Repository.OrderRepository;
import com.restful.transactiondemo.dto.OrderRequest;
import com.restful.transactiondemo.dto.OrderResponse;

public interface OrderService {
    

    
    OrderResponse placeOrder(OrderRequest request);
}
