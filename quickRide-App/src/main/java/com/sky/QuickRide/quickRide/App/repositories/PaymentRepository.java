package com.sky.QuickRide.quickRide.App.repositories;

import com.sky.QuickRide.quickRide.App.entities.Payment;
import com.sky.QuickRide.quickRide.App.entities.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment,Long> {

    Optional<Payment> findByRide(Ride ride);
}
