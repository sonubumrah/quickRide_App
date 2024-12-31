package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.dto.DriverDto;
import com.sky.QuickRide.quickRide.App.dto.RideDto;
import com.sky.QuickRide.quickRide.App.dto.RiderDto;
import com.sky.QuickRide.quickRide.App.entities.Driver;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface DriverService {
    RideDto acceptRide(Long rideRequestId);

    RideDto cancelRide(Long rideId);

    RideDto startRide(Long rideId,String otp);

    RideDto endRide(Long rideId);

    RiderDto rateRider(Long rideId,Integer rating);

    DriverDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);


    Driver getCurrentDriver();

    Driver updateDriverAvailability(Driver driver, boolean available);

    Driver createNewDriver(Driver driver);
}
