package com.sky.QuickRide.quickRide.App.dto;

import com.sky.QuickRide.quickRide.App.entities.enums.PaymentMethod;
import com.sky.QuickRide.quickRide.App.entities.enums.RideStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class RideDto {

    private Long id;

    private PointDto pickupLocation;

    private PointDto dropLocation;

    private LocalDateTime createdTime;

    private RiderDto rider;


    private PaymentMethod paymentMethod;

    private RideStatus rideStatus;

    private DriverDto driver;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private String otp;

}
