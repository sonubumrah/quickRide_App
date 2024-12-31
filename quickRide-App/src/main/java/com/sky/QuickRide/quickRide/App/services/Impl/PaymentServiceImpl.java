package com.sky.QuickRide.quickRide.App.services.Impl;

import com.sky.QuickRide.quickRide.App.dto.WalletTransactionDto;
import com.sky.QuickRide.quickRide.App.entities.Payment;
import com.sky.QuickRide.quickRide.App.entities.Ride;
import com.sky.QuickRide.quickRide.App.entities.enums.PaymentStatus;
import com.sky.QuickRide.quickRide.App.exceptions.ResourceNotFoundException;
import com.sky.QuickRide.quickRide.App.repositories.PaymentRepository;
import com.sky.QuickRide.quickRide.App.services.PaymentService;
import com.sky.QuickRide.quickRide.App.services.WalletTransactionService;
import com.sky.QuickRide.quickRide.App.strategies.PaymentStrategyManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentStrategyManager paymentStrategyManager;
    @Override
    public void processPayment(Ride ride) {
        Payment payment=paymentRepository.findByRide(ride)
                .orElseThrow(()
                        ->new ResourceNotFoundException("Payment Not found for ride with id"+ride.getId()));
        paymentStrategyManager.paymentStrategy(payment.getPaymentMethod()).processPayment(payment);

    }

    @Override
    public Payment createNewPayment(Ride ride) {
        Payment payment=Payment.builder()
                .ride(ride)
                .paymentMethod(ride.getPaymentMethod())
                .amount(ride.getFare())
                .paymentStatus(PaymentStatus.PENDING)
                .build();

        return paymentRepository.save(payment);
    }

    @Override
    public void updatePaymentStatus(Payment payment, PaymentStatus status) {
        payment.setPaymentStatus(status);
        paymentRepository.save(payment);
    }
}
