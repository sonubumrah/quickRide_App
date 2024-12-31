package com.sky.QuickRide.quickRide.App.repositories;

import com.sky.QuickRide.quickRide.App.entities.Driver;
import com.sky.QuickRide.quickRide.App.entities.User;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DriverRepository extends JpaRepository<Driver,Long> {
    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)
    List<Driver> findTenNearestDrivers(Point pickupLocation);

    @Query(value = "SELECT d.*  "
    +"FROM drivers d "+
    "where d.available = true AND ST_DWithin( d.current_location, :pickupLocation, 15000 ) "+
    "order by d.rating DESC "+
            "limit 10", nativeQuery = true)
    List<Driver> findTenNearestByTopRatedDriver(Point pickupLocation);

    Optional<Driver> findByUser(User user);
}
