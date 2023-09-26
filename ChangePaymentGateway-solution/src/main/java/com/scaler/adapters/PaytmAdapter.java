package com.scaler.adapters;

import com.scaler.libraries.paytm.PaytmApi;
import com.scaler.libraries.paytm.PaytmPaymentResponse;
import com.scaler.models.Payment;
import com.scaler.models.PaymentStatus;

public class PaytmAdapter implements PaymentGatewayAdapter{

    private PaytmApi paytmApi;

    public PaytmAdapter() {
        this.paytmApi = new PaytmApi();
    }

    @Override
    public Payment makePayment(long billId, double amount){
        PaytmPaymentResponse paytmPaymentResponse = paytmApi.makePayment(billId, amount);
        Payment payment = new Payment();
        payment.setTxnId(paytmPaymentResponse.getTxnId());
        payment.setPaymentStatus(PaymentStatus.valueOf(paytmPaymentResponse.getPaymentStatus()));
        payment.setBillId(billId);
        return payment;
    }
}
