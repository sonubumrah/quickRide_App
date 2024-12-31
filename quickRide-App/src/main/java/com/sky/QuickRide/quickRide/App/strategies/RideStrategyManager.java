package com.sky.QuickRide.quickRide.App.strategies;

import com.sky.QuickRide.quickRide.App.strategies.Impl.DriverMatchingHighestRatedDriverStrategy;
import com.sky.QuickRide.quickRide.App.strategies.Impl.DriverMatchingNearestDriverStrategy;
import com.sky.QuickRide.quickRide.App.strategies.Impl.RideFareDefaultCalculationStrategy;
import com.sky.QuickRide.quickRide.App.strategies.Impl.RideFareSurgePricingFareCalculationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
@RequiredArgsConstructor
public class RideStrategyManager {
    private final DriverMatchingHighestRatedDriverStrategy highestRatedDriverStrategy;
    private final DriverMatchingNearestDriverStrategy nearestDriverStrategy;
    private final RideFareDefaultCalculationStrategy defaultCalculationStrategy;
    private final RideFareSurgePricingFareCalculationStrategy surgePricingFareCalculationStrategy;
    public DriverMatchingStrategy driverMatchingStrategy(double riderRating ){
        if(riderRating>4.8){
            return highestRatedDriverStrategy;
        }
        else{
            return nearestDriverStrategy;
        }


    }
    public RideFareCalculationStrategy rideFareCalculationStrategy(){
        LocalTime surgeStartTime=LocalTime.of(18,0);
        LocalTime surgeEndTime=LocalTime.of(21,0);
        LocalTime currentTime = LocalTime.now();

        boolean isSurgeTime = currentTime.isAfter(surgeStartTime) && currentTime.isBefore(surgeEndTime);
        if(isSurgeTime){
            return surgePricingFareCalculationStrategy;
        }
        else{
            return defaultCalculationStrategy;
        }

    }

}
