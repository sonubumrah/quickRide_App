package com.sky.QuickRide.quickRide.App.services;

import org.locationtech.jts.geom.Point;

public interface DistanceService {

    double calculateDistance(Point src,Point dest);

}
