package com.sky.QuickRide.quickRide.App.repositories;

import com.sky.QuickRide.quickRide.App.entities.User;
import com.sky.QuickRide.quickRide.App.entities.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet,Long> {


    Optional<Wallet> findByUser(User user);
}
