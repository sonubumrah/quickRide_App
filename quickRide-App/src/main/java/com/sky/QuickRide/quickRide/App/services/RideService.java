package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.dto.RideRequestDto;
import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.Ride;
import com.sky.QuickRide.quickRide.App.entities.RideRequest;
import com.sky.QuickRide.quickRide.App.entities.Rider;
import com.sky.QuickRide.quickRide.App.entities.enums.RideStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface RideService {

    Ride getRideById(Long rideId);



    Ride CreateNewRide(RideRequest rideRequest, Driver driver);

    Ride updateRideStatus(Ride ride, RideStatus rideStatus);

    Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest);

    Page<Ride> getAllRidesOfDriver(Driver driver,PageRequest pageRequest);
}
