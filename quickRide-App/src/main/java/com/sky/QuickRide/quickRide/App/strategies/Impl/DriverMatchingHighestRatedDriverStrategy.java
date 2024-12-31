package com.sky.QuickRide.quickRide.App.strategies.Impl;

import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.RideRequest;
import com.sky.QuickRide.quickRide.App.repositories.DriverRepository;
import com.sky.QuickRide.quickRide.App.strategies.DriverMatchingStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

@RequiredArgsConstructor
public class DriverMatchingHighestRatedDriverStrategy implements DriverMatchingStrategy {
    private final DriverRepository driverRepository;
    @Override
    public List<Driver> findMatchingDriver(RideRequest rideRequest) {

        return driverRepository.findTenNearestDrivers(rideRequest.getPickupLocation());
    }
}
