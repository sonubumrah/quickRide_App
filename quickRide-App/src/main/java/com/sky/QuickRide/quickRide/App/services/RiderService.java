package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.dto.DriverDto;
import com.sky.QuickRide.quickRide.App.dto.RideDto;
import com.sky.QuickRide.quickRide.App.dto.RideRequestDto;
import com.sky.QuickRide.quickRide.App.dto.RiderDto;
import com.sky.QuickRide.quickRide.App.entities.Rider;
import com.sky.QuickRide.quickRide.App.entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface RiderService {
    RideRequestDto requestRide(RideRequestDto rideRequestDto);

    RideDto cancelRide(Long rideId);



    DriverDto rateDriver(Long rideId,Integer rating);

    RiderDto getMyProfile();

    Page<RideDto> getAllMyRides(PageRequest pageRequest);

    Rider createNewRider(User user);

    Rider getCurrentRider();
}
