package com.sky.QuickRide.quickRide.App.strategies;

import com.sky.QuickRide.quickRide.App.entities.Payment;

public interface PaymentStrategy {

    Double PLATFORM_COMMISSION=0.3;
    void processPayment(Payment payment);
}
