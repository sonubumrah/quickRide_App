package com.sky.QuickRide.quickRide.App.dto;

import com.sky.QuickRide.quickRide.App.entities.User;
import lombok.Data;


import java.util.List;
@Data
public class WalletDto {

    private Long id;

    private User user;

    private  Double balance;


    private List<WalletTransactionDto> transaction;

}
