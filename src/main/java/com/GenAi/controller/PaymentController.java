package com.GenAi.controller;

import com.GenAi.model.dto.PaymentDTO;
import com.GenAi.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/{orderId}")
    public PaymentDTO pay(@PathVariable Long orderId) {
        return paymentService.pay(orderId);
    }
}