package com.sky.QuickRide.quickRide.App.strategies;

import com.sky.QuickRide.quickRide.App.entities.enums.PaymentMethod;
import com.sky.QuickRide.quickRide.App.strategies.Impl.CashPaymentStrategy;
import com.sky.QuickRide.quickRide.App.strategies.Impl.WalletPaymentStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentStrategyManager {
    private final CashPaymentStrategy cashPaymentStrategy;
    private final WalletPaymentStrategy walletPaymentStrategy;

    public PaymentStrategy paymentStrategy(PaymentMethod paymentMethod){
        return switch (paymentMethod){
            case WALLET -> walletPaymentStrategy;
            case CASH -> cashPaymentStrategy;
        };
    }
}
