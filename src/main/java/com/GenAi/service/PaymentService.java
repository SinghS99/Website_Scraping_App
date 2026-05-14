package com.GenAi.service;

import com.GenAi.model.Order;
import com.GenAi.model.Payment;
import com.GenAi.model.dto.PaymentDTO;
import com.GenAi.repo.OrderRepo;
import com.GenAi.repo.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    @Autowired private PaymentRepo paymentRepo;
    @Autowired private OrderRepo orderRepo;

    public PaymentDTO pay(Long orderId) {
        Order order = orderRepo.findById(orderId).orElseThrow();
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(order.getTotalAmount());
        payment.setMethod("FAKE");
        payment.setStatus("SUCCESS");

        order.setStatus("PAID");
        orderRepo.save(order);
        Payment saved = paymentRepo.save(payment);

        return toPaymentDTO(saved);
    }

    private PaymentDTO toPaymentDTO(Payment payment) {
        PaymentDTO dto = new PaymentDTO();
        dto.setId(payment.getId());
        dto.setOrderId(payment.getOrder().getId());
        dto.setAmount(payment.getAmount());
        dto.setMethod(payment.getMethod());
        dto.setStatus(payment.getStatus());
        return dto;
    }
}
