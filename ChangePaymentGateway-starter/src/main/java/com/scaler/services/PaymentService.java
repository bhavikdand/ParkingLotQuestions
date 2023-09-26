package com.scaler.services;

import com.scaler.exceptions.InvalidBillException;
import com.scaler.models.Payment;

public interface PaymentService {

    Payment makePayment(long billId) throws InvalidBillException;
}
