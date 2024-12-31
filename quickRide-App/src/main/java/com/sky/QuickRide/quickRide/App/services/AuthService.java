package com.sky.QuickRide.quickRide.App.services;

import com.sky.QuickRide.quickRide.App.dto.DriverDto;
import com.sky.QuickRide.quickRide.App.dto.SignupDto;
import com.sky.QuickRide.quickRide.App.dto.UserDto;

public interface AuthService {
    String[] login(String email,String password);


    UserDto signup(SignupDto signupDto);

    DriverDto onboardNewDriver(Long userId,String vehicleId);


    String refreshToken(String refreshToken);
}
