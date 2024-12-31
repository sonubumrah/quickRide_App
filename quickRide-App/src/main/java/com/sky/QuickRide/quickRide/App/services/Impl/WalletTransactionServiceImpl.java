package com.sky.QuickRide.quickRide.App.services.Impl;

import com.sky.QuickRide.quickRide.App.dto.WalletTransactionDto;
import com.sky.QuickRide.quickRide.App.entities.WalletTransaction;
import com.sky.QuickRide.quickRide.App.repositories.WalletTransactionRepository;
import com.sky.QuickRide.quickRide.App.services.WalletTransactionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.function.array.JsonArrayViaElementArgumentReturnTypeResolver;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WalletTransactionServiceImpl implements WalletTransactionService {
    private final WalletTransactionRepository walletTransactionRepository;
    private final ModelMapper modelMapper;
    @Override
    public void createNewWalletTransaction(WalletTransaction walletTransaction) {

       walletTransactionRepository.save(walletTransaction);

    }
}
