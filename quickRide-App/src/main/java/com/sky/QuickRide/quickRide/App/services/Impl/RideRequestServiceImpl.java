package com.sky.QuickRide.quickRide.App.services.Impl;

import com.sky.QuickRide.quickRide.App.entities.RideRequest;
import com.sky.QuickRide.quickRide.App.exceptions.ResourceNotFoundException;
import com.sky.QuickRide.quickRide.App.repositories.RideRequestRepository;
import com.sky.QuickRide.quickRide.App.services.RideRequestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RideRequestServiceImpl implements RideRequestService {
    private final RideRequestRepository rideRequestRepository;
    @Override
    public RideRequest findRideRequestById(Long rideRequestId) {
        return rideRequestRepository
                .findById(rideRequestId)
                .orElseThrow(()-> new ResourceNotFoundException("RideRequest not found with id: "+rideRequestId));
    }

    @Override
    public void update(RideRequest rideRequest) {
        RideRequest toSave = rideRequestRepository.findById(rideRequest.getId()).
                orElseThrow(()-> new ResourceNotFoundException("RideRequest Not found with this id"+rideRequest.getId()));
        rideRequestRepository.save(toSave);
    }
}
