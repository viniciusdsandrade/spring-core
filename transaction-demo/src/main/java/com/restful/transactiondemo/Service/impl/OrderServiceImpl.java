package com.restful.transactiondemo.Service.impl;

import com.restful.transactiondemo.Entity.Order;
import com.restful.transactiondemo.Entity.Payment;
import com.restful.transactiondemo.Repository.OrderRepository;
import com.restful.transactiondemo.Repository.PaymentRepository;
import com.restful.transactiondemo.Service.OrderService;
import com.restful.transactiondemo.dto.OrderRequest;
import com.restful.transactiondemo.dto.OrderResponse;
import com.restful.transactiondemo.exception.PaymentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final PaymentRepository paymentService;

    public OrderServiceImpl(OrderRepository orderRepository,
                            PaymentRepository paymentService) {
        this.orderRepository = orderRepository;
        this.paymentService = paymentService;
    }

    /**
     * Método para realizar um pedido, salvar o pedido e processar o pagamento.
     *
     * @param request Objeto que contém as informações do pedido e do pagamento.
     * @return Uma resposta que inclui o número de rastreamento do pedido, o status e uma mensagem.
     * @throws PaymentException Se o tipo de pagamento não for "DEBIT".
     */
    @Override
    @Transactional()
    public OrderResponse placeOrder(OrderRequest request) {

        Order order = request.getOrder();
        order.setStatus("PROCESSING");
        order.setOrderTracking(UUID.randomUUID().toString());
        orderRepository.save(order);

        Payment payment = request.getPayment();

        if (!payment.getType().equals("DEBIT"))
            throw new PaymentException("Invalid payment type");

        payment.setOrderId(order.getId());
        paymentService.save(payment);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setOrderTrackingNumber(order.getOrderTracking());
        orderResponse.setStatus(order.getStatus());
        orderResponse.setMessage("Order placed successfully");
        return orderResponse;
    }
}
