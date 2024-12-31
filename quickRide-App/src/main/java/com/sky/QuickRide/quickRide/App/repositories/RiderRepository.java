package com.sky.QuickRide.quickRide.App.repositories;

import com.sky.QuickRide.quickRide.App.entities.Rider;
import com.sky.QuickRide.quickRide.App.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RiderRepository extends JpaRepository<Rider,Long> {

    Optional<Rider>  findByUser(User user);
}
