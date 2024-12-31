package com.sky.QuickRide.quickRide.App.repositories;

import com.sky.QuickRide.quickRide.App.entities.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletTransactionRepository extends JpaRepository<WalletTransaction,Long> {

}
