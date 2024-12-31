package com.sky.QuickRide.quickRide.App.strategies.Impl;

import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.Payment;
import com.sky.QuickRide.quickRide.App.entities.Wallet;
import com.sky.QuickRide.quickRide.App.entities.enums.PaymentStatus;
import com.sky.QuickRide.quickRide.App.entities.enums.TransactionMethod;
import com.sky.QuickRide.quickRide.App.repositories.PaymentRepository;
import com.sky.QuickRide.quickRide.App.services.PaymentService;
import com.sky.QuickRide.quickRide.App.services.WalletService;
import com.sky.QuickRide.quickRide.App.strategies.PaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CashPaymentStrategy implements PaymentStrategy {
   private final WalletService walletService;
   private final PaymentRepository paymentRepository;
    @Override
    public void processPayment(Payment payment) {
        Driver driver=payment.getRide().getDriver();
        Wallet driverWallet = walletService.findByUser(driver.getUser());
        double platformCommission = payment.getAmount()*PLATFORM_COMMISSION;

        walletService.deductMoneyFromWallet(driver.getUser(),platformCommission,
                null,payment.getRide(), TransactionMethod.RIDE);


        payment.setPaymentStatus(PaymentStatus.CONFIRMED);
        paymentRepository.save(payment);
    }
}
