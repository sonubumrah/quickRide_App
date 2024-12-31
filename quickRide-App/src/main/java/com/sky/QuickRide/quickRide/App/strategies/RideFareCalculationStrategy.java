package com.sky.QuickRide.quickRide.App.strategies;

import com.sky.QuickRide.quickRide.App.entities.RideRequest;

public interface RideFareCalculationStrategy {
     double RIDE_FARE_MULTIPLIER=10;
    double calculateFare(RideRequest rideRequest);
}
