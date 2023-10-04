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
        MakePaymentResponseDto makePaymentResponseDto = new MakePaymentResponseDto();
        try{
            Payment payment = paymentService.makePayment(makePaymentRequestDto.getBillId());
            makePaymentResponseDto.setResponseStatus(ResponseStatus.SUCCESS);
            makePaymentResponseDto.setPaymentStatus(payment.getPaymentStatus());
            makePaymentResponseDto.setTxnId(payment.getTxnId());
            return makePaymentResponseDto;
        } catch (Exception e) {
            makePaymentResponseDto.setResponseStatus(ResponseStatus.FAILURE);
            return makePaymentResponseDto;
        }
    }
}
