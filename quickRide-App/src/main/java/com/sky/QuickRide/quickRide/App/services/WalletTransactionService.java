package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.dto.WalletTransactionDto;
import com.sky.QuickRide.quickRide.App.entities.WalletTransaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service

public interface WalletTransactionService {
    void createNewWalletTransaction(WalletTransaction walletTransaction);
}
