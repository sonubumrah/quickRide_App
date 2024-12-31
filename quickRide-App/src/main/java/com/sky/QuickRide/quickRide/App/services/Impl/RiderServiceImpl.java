package com.sky.QuickRide.quickRide.App.services.Impl;

import com.nimbusds.jose.proc.SecurityContext;
import com.sky.QuickRide.quickRide.App.dto.DriverDto;
import com.sky.QuickRide.quickRide.App.dto.RideDto;
import com.sky.QuickRide.quickRide.App.dto.RideRequestDto;
import com.sky.QuickRide.quickRide.App.dto.RiderDto;
import com.sky.QuickRide.quickRide.App.entities.*;
import com.sky.QuickRide.quickRide.App.entities.enums.RideRequestStatus;
import com.sky.QuickRide.quickRide.App.entities.enums.RideStatus;
import com.sky.QuickRide.quickRide.App.exceptions.ResourceNotFoundException;
import com.sky.QuickRide.quickRide.App.repositories.RideRequestRepository;
import com.sky.QuickRide.quickRide.App.repositories.RiderRepository;
import com.sky.QuickRide.quickRide.App.services.DriverService;
import com.sky.QuickRide.quickRide.App.services.RatingService;
import com.sky.QuickRide.quickRide.App.services.RideService;
import com.sky.QuickRide.quickRide.App.services.RiderService;
import com.sky.QuickRide.quickRide.App.strategies.RideStrategyManager;
import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Builder
public class RiderServiceImpl implements RiderService {
    private final ModelMapper modelMapper;
    //private final RideFareCalculationStrategy rideFareCalculationStrategy;
   // private final DriverMatchingStrategy driverMatchingStrategy;
    private final RideRequestRepository rideRequestRepository;
    private final RiderRepository riderRepository;
    private final RideStrategyManager rideStrategyManager;

    private final RideService rideService;
    private final DriverService driverService;
    private final RatingService ratingService;
    @Override
    @Transactional //for atomacity in multiple transaction
    public RideRequestDto requestRide(RideRequestDto rideRequestDto) {
        Rider rider=getCurrentRider();
       RideRequest rideRequest = modelMapper.map(rideRequestDto,RideRequest.class);

       rideRequest.setRideRequestStatus(RideRequestStatus.PENDING);
       rideRequest.setRider(rider);

       Double fare=rideStrategyManager.rideFareCalculationStrategy().calculateFare(rideRequest);
       rideRequest.setFare(fare);
       List<Driver> drivers=rideStrategyManager.driverMatchingStrategy(rider.getRating()).findMatchingDriver(rideRequest);

       RideRequest saveedRideRequest=rideRequestRepository.save(rideRequest);
      // log.info(rideRequest.toString());
 //   TODO : Add accept method to send notification to driver.
       return modelMapper.map(saveedRideRequest,RideRequestDto.class);
    }

    @Override
    public RideDto cancelRide(Long rideId) {
        Rider rider=getCurrentRider();
        Ride ride=rideService.getRideById(rideId);

        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider does not own this ride with rideId"+rideId);
        }
        if(!ride.getRideStatus().equals(RideStatus.CONFIRMED)){
            throw new RuntimeException("Ride Cannot bee canceled , invalid status "+ride.getRideStatus());
        }
        Ride savedRide=rideService.updateRideStatus(ride,RideStatus.CANCELLED);
        driverService.updateDriverAvailability(ride.getDriver(),true);
        return modelMapper.map(savedRide,RideDto.class);


    }

    @Override
    public DriverDto rateDriver(Long rideId, Integer rating) {
        Ride ride =rideService.getRideById(rideId);
        Rider rider=getCurrentRider();
        if(!rider.equals(ride.getRider())){
            throw new RuntimeException("Rider is not owner of this  ride");
        }
        if(!ride.getRideStatus().equals(RideStatus.ENDED)){
            throw new RuntimeException("Ride Status cannot be Ended hence cannot start rating , status :"+ride.getRideStatus());
        }

        return ratingService.rateDriver(ride,rating);

    }

    @Override
    public RiderDto getMyProfile() {
        Rider currentRider=getCurrentRider();
        return modelMapper.map(currentRider,RiderDto.class);


    }

    @Override
    public Page<RideDto> getAllMyRides(PageRequest pageRequest) {
        Rider currentRider=getCurrentRider();
        return rideService.getAllRidesOfRider(currentRider,pageRequest).map(
                ride -> modelMapper.map(ride,RideDto.class)
        );


    }

    @Override
    public Rider createNewRider(User user) {
        Rider rider = Rider.builder().user(user).rating(0.0).build();
        return riderRepository.save(rider);
    }

    @Override
    public Rider getCurrentRider() {
        User user = (User ) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return riderRepository.findByUser(user).orElseThrow(()->new ResourceNotFoundException(
                "Rider Not associated with  user with id:"+user.getId()));
    }
}
