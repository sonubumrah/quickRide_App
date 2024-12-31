package com.sky.QuickRide.quickRide.App.dto;

import com.sky.QuickRide.quickRide.App.entities.enums.TransactionMethod;
import com.sky.QuickRide.quickRide.App.entities.enums.TransactionType;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WalletTransactionDto {

    private Long id;


    private  Double amount;

    private TransactionType transactionType;

    private TransactionMethod transactionMethod;


    private RideDto rideDto;

    private String transactionId;


    private WalletDto walletDto;


    private LocalDateTime timeStamp;


}
