package com.sky.QuickRide.quickRide.App.services.Impl;

import com.sky.QuickRide.quickRide.App.dto.RideRequestDto;
import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.Ride;
import com.sky.QuickRide.quickRide.App.entities.RideRequest;
import com.sky.QuickRide.quickRide.App.entities.Rider;
import com.sky.QuickRide.quickRide.App.entities.enums.RideRequestStatus;
import com.sky.QuickRide.quickRide.App.entities.enums.RideStatus;
import com.sky.QuickRide.quickRide.App.exceptions.ResourceNotFoundException;
import com.sky.QuickRide.quickRide.App.repositories.RideRepository;
import com.sky.QuickRide.quickRide.App.services.RideRequestService;
import com.sky.QuickRide.quickRide.App.services.RideService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class RideServiceImpl implements RideService {
    private final RideRepository rideRepository;
    private final RideRequestService rideRequestService;
    private final ModelMapper modelMapper;
    //private final RideRequest rideRequest;
    @Override
    public Ride getRideById(Long rideId) {
        return rideRepository.findById(rideId)
                .orElseThrow(()-> new ResourceNotFoundException("Ride not found with id"+rideId));
    }


    @Override
    public Ride CreateNewRide(RideRequest rideRequest, Driver driver) {
        rideRequest.setRideRequestStatus(RideRequestStatus.CONFIRMED);
        Ride ride=modelMapper.map(rideRequest,Ride.class);
        ride.setRideStatus(RideStatus.CONFIRMED);
        ride.setDriver(driver);
        ride.setOtp(generateRandomOTP());
        ride.setId(null);

        rideRequestService.update(rideRequest);
        return  rideRepository.save(ride);


       // return null;
    }

    @Override
    public Ride updateRideStatus(Ride ride, RideStatus rideStatus) {
        ride.setRideStatus(rideStatus);
       return rideRepository.save(ride);

    }



    @Override
    public Page<Ride> getAllRidesOfRider(Rider rider, PageRequest pageRequest) {
        return rideRepository.findByRider(rider,pageRequest);
    }

    @Override
    public Page<Ride> getAllRidesOfDriver(Driver driver, PageRequest pageRequest) {
        return rideRepository.findByDriver(driver,pageRequest);
    }
    public String generateRandomOTP(){
        Random random=new Random();
        int otpInt=random.nextInt(10000);
        return String.format("%04d",otpInt);
    }
}
