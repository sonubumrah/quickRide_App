package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.entities.Ride;
import com.sky.QuickRide.quickRide.App.entities.User;
import com.sky.QuickRide.quickRide.App.entities.Wallet;
import com.sky.QuickRide.quickRide.App.entities.enums.TransactionMethod;
import org.springframework.stereotype.Service;

@Service
public interface WalletService {
    Wallet addMoneyToWallet(User user, Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);

    void withdrawAllMyMoneyFromWallet();

    Wallet findWalletById(Long walletId);

    Wallet createNewWallet(User user);

    Wallet findByUser(User user);

    Wallet deductMoneyFromWallet(User user,Double amount, String transactionId, Ride ride, TransactionMethod transactionMethod);
}
