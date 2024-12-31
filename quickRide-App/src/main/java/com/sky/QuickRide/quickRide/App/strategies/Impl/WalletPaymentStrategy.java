package com.sky.QuickRide.quickRide.App.strategies.Impl;

import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.Payment;
import com.sky.QuickRide.quickRide.App.entities.Rider;
import com.sky.QuickRide.quickRide.App.entities.enums.PaymentStatus;
import com.sky.QuickRide.quickRide.App.entities.enums.TransactionMethod;
import com.sky.QuickRide.quickRide.App.repositories.PaymentRepository;
import com.sky.QuickRide.quickRide.App.services.PaymentService;
import com.sky.QuickRide.quickRide.App.services.WalletService;
import com.sky.QuickRide.quickRide.App.strategies.PaymentStrategy;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletPaymentStrategy implements PaymentStrategy {
    private final WalletService walletService;
    private final PaymentRepository paymentRepository;
    @Override
    @Transactional
    public void processPayment(Payment payment) {
        Driver driver=payment.getRide().getDriver();

        Rider rider=payment.getRide().getRider();

        walletService.deductMoneyFromWallet(rider.getUser(),payment.getAmount(),
                null,payment.getRide(), TransactionMethod.RIDE);

        double driver_cut=payment.getAmount()*(1-PLATFORM_COMMISSION);

        walletService.addMoneyToWallet(driver.getUser(),driver_cut,
                null,payment.getRide(),TransactionMethod.RIDE);

        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);

    }
}
