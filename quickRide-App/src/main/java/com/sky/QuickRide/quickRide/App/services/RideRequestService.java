package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.entities.RideRequest;

public interface RideRequestService {

    RideRequest findRideRequestById(Long rideRequestId);

    void update(RideRequest rideRequest);
}
