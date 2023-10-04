package com.scaler.services;

import com.scaler.adapters.PaymentGatewayAdapter;
import com.scaler.exceptions.InvalidBillException;
import com.scaler.models.Bill;
import com.scaler.models.Payment;
import com.scaler.repositories.BillRepository;

public class PaymentServiceImpl implements PaymentService{

    private BillRepository billRepository;
    private PaymentGatewayAdapter paymentGatewayAdapter;

    public PaymentServiceImpl(BillRepository billRepository, PaymentGatewayAdapter paymentGatewayAdapter) {
        this.billRepository = billRepository;
        this.paymentGatewayAdapter = paymentGatewayAdapter;
    }

    @Override
    public Payment makePayment(long billId) throws InvalidBillException {
        Bill bill = billRepository.findById(billId).orElseThrow(() -> new InvalidBillException("Bill not found"));
        return paymentGatewayAdapter.makePayment(billId, bill.getTotalAmount());
    }
}
