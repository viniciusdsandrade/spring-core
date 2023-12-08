package com.restful.transactiondemo.dto;

import com.restful.transactiondemo.Entity.Order;
import com.restful.transactiondemo.Entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequest {
    private Order order;
    private Payment payment;
}
