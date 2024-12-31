package com.sky.QuickRide.quickRide.App.services.Impl;

import com.sky.QuickRide.quickRide.App.dto.DriverDto;
import com.sky.QuickRide.quickRide.App.dto.RiderDto;
import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.Rating;
import com.sky.QuickRide.quickRide.App.entities.Ride;
import com.sky.QuickRide.quickRide.App.entities.Rider;
import com.sky.QuickRide.quickRide.App.exceptions.ResourceNotFoundException;
import com.sky.QuickRide.quickRide.App.exceptions.RuntimeConflictException;
import com.sky.QuickRide.quickRide.App.repositories.DriverRepository;
import com.sky.QuickRide.quickRide.App.repositories.RatingRepository;
import com.sky.QuickRide.quickRide.App.repositories.RiderRepository;
import com.sky.QuickRide.quickRide.App.services.RatingService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;
    private final DriverRepository driverRepository;
    private final RiderRepository riderRepository;
    private final ModelMapper modelMapper;
    @Override
    public DriverDto rateDriver(Ride ride, Integer rating) {
        Driver driver=ride.getDriver();
        Rating ratingObj=ratingRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("rating not found for ride with id:"+ride.getId()));
        if(ratingObj.getDriverRating()!=null){
            throw  new RuntimeConflictException("Driver is already rated ,cannot rate again");
        }
        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating =ratingRepository.findByDriver(driver)
                .stream()
                .mapToDouble(Rating::getDriverRating)
                .average().orElse(0.0);
        driver.setRating(newRating);
        Driver savedDriver =driverRepository.save(driver);
        return modelMapper.map(savedDriver,DriverDto.class);
    }

    @Override
    public RiderDto rateRider(Ride ride, Integer rating) {
        Rider rider=ride.getRider();
        Rating ratingObj=ratingRepository.findByRide(ride)
                .orElseThrow(()->new ResourceNotFoundException("rating not found for ride with id:"+ride.getId()));
        if(ratingObj.getRiderRating()!=null){
            throw  new RuntimeConflictException("Rider is already rated ,cannot rate again");
        }
        ratingObj.setDriverRating(rating);
        ratingRepository.save(ratingObj);

        Double newRating =ratingRepository.findByRider(rider)
                .stream()
                .mapToDouble(Rating::getRiderRating)
                .average().orElse(0.0);
        rider.setRating(newRating);
        Rider savedRider=riderRepository.save(rider);
        return modelMapper.map(savedRider,RiderDto.class);

    }

    @Override
    public void createNewRating(Ride ride) {
        Rating rating=Rating.builder()
                .rider(ride.getRider())
                .driver(ride.getDriver())
                .ride(ride)
                .build();
        ratingRepository.save(rating);
    }
}
