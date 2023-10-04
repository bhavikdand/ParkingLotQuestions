package com.scaler.adapters;

import com.scaler.libraries.razorpay.RazorpayApi;
import com.scaler.libraries.razorpay.RazorpayPaymentResponse;
import com.scaler.models.Payment;
import com.scaler.models.PaymentStatus;

public class RazorpayAdapter implements PaymentGatewayAdapter{

    private RazorpayApi razorpayApi = new RazorpayApi();
    @Override
    public Payment makePayment(long billId, double amount) {
        RazorpayPaymentResponse razorpayPaymentResponse = razorpayApi.processPayment(billId, amount);
        Payment payment = new Payment();
        payment.setTxnId(razorpayPaymentResponse.getTransactionId());
        payment.setPaymentStatus(PaymentStatus.valueOf(razorpayPaymentResponse.getPaymentStatus()));
        payment.setBillId(billId);
        return payment;
    }
}
