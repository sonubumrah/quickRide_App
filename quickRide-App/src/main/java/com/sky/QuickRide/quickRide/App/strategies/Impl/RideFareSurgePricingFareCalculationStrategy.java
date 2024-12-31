package com.sky.QuickRide.quickRide.App.strategies.Impl;

import com.sky.QuickRide.quickRide.App.entities.RideRequest;
import com.sky.QuickRide.quickRide.App.services.DistanceService;
import com.sky.QuickRide.quickRide.App.strategies.RideFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideFareSurgePricingFareCalculationStrategy implements RideFareCalculationStrategy {
    private final DistanceService distanceService;
    private static final double SURGE_FACTOR=2;
    @Override
    public double calculateFare(RideRequest rideRequest) {
        double distance=distanceService.calculateDistance(rideRequest.getPickupLocation(),rideRequest.getDropOffLocation());
        return distance*RIDE_FARE_MULTIPLIER*SURGE_FACTOR;
    }
}
