package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.dto.DriverDto;
import com.sky.QuickRide.quickRide.App.dto.RiderDto;
import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.Ride;
import com.sky.QuickRide.quickRide.App.entities.Rider;
import org.springframework.stereotype.Service;

@Service
public interface RatingService {
    DriverDto rateDriver(Ride ride, Integer rating);
    RiderDto rateRider(Ride ride, Integer rating);
    void createNewRating(Ride ride);

}
