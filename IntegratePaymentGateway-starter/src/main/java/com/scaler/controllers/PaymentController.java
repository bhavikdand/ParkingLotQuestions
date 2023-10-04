package com.scaler.controllers;

import com.scaler.dtos.MakePaymentRequestDto;
import com.scaler.dtos.MakePaymentResponseDto;
import com.scaler.dtos.ResponseStatus;
import com.scaler.models.Payment;
import com.scaler.services.PaymentService;

public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) {
        return null;
    }
}
