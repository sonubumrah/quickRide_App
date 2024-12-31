package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.entities.Payment;
import com.sky.QuickRide.quickRide.App.entities.Ride;
import com.sky.QuickRide.quickRide.App.entities.enums.PaymentStatus;
import org.springframework.stereotype.Service;

@Service
public interface PaymentService {
    void processPayment(Ride ride);

    Payment createNewPayment(Ride ride);

    void updatePaymentStatus(Payment payment, PaymentStatus status);
}
