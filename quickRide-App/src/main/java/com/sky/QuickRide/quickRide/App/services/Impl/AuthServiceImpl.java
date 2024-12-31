package com.sky.QuickRide.quickRide.App.services.Impl;

import com.sky.QuickRide.quickRide.App.Security.JWTService;
import com.sky.QuickRide.quickRide.App.dto.DriverDto;
import com.sky.QuickRide.quickRide.App.dto.SignupDto;
import com.sky.QuickRide.quickRide.App.dto.UserDto;
import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.User;
import com.sky.QuickRide.quickRide.App.entities.enums.Role;
import com.sky.QuickRide.quickRide.App.exceptions.ResourceNotFoundException;
import com.sky.QuickRide.quickRide.App.exceptions.RuntimeConflictException;
import com.sky.QuickRide.quickRide.App.repositories.UserRepository;
import com.sky.QuickRide.quickRide.App.services.AuthService;
import com.sky.QuickRide.quickRide.App.services.DriverService;
import com.sky.QuickRide.quickRide.App.services.RiderService;
import com.sky.QuickRide.quickRide.App.services.WalletService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;

    private final ModelMapper modelmapper;
    private final RiderService riderService;
    private final WalletService walletService;
    private final DriverService driverService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;
    @Override
    public String[] login(String email, String password) {
        String []tokens=new String[2];
       Authentication authentication= authenticationManager.authenticate(
               new UsernamePasswordAuthenticationToken(email,password));
       User user=(User) authentication.getPrincipal();
       String accessToken=jwtService.generateAccessToken(user);
       String refreshToken=jwtService.generateRefreshToken(user);

       return new String[]{accessToken,refreshToken};



    }

    @Override
    @Transactional
    public UserDto signup(SignupDto signupDto) {
       User user= userRepository.findByEmail(signupDto.getEmail()).orElse(null);
       if(user!=null){
           throw new RuntimeException("Cannot SignUp , User Already Exist with this email"+signupDto.getEmail());
       }




        User mappedUser=modelmapper.map(signupDto,User.class);
        mappedUser.setRoles(Set.of(Role.RIDER));
        mappedUser.setPassword(passwordEncoder.encode(mappedUser.getPassword()));
        User savedUser = userRepository.save(mappedUser);
        riderService.createNewRider(savedUser);
        walletService.createNewWallet(savedUser);
        return modelmapper.map(savedUser,UserDto.class);
    }

    @Override
    public DriverDto onboardNewDriver(Long userId,String vehicleId) {
        User user=userRepository.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("User Not find with id"+userId));
        if(user.getRoles().contains(Role.DRIVER)){
            throw new RuntimeConflictException("User with id "+userId+" is already driver");
        }
        Driver createDriver=Driver.builder()
                .id(userId)
                .rating(0.0)
                .vehicleId(vehicleId)
                .available(true)
                .build();
        user.getRoles().add(Role.DRIVER);
        userRepository.save(user);
        Driver savedDriver=driverService.createNewDriver(createDriver);

        return modelmapper.map(savedDriver,DriverDto.class);
    }

    @Override
    public String refreshToken(String refreshToken) {
        Long userId = jwtService.getUserIdFromToken(refreshToken);
        User user=userRepository.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("User Not found with id: "+userId));
        return jwtService.generateAccessToken(user);
    }
}
