package com.sky.QuickRide.quickRide.App.entities;

import com.sky.QuickRide.quickRide.App.entities.enums.PaymentMethod;
import com.sky.QuickRide.quickRide.App.entities.enums.RideStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.locationtech.jts.geom.Point;

import java.time.LocalDateTime;
@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(indexes = {
        @Index(name = "idx_ride_rider",columnList = "rider_id"),
        @Index(name = "idx_ride_driver",columnList = "driver_id")
})


public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point pickupLocation;
    @Column(columnDefinition = "Geometry(Point, 4326)")
    private Point dropLocation;
    @CreationTimestamp
    private LocalDateTime createdTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Rider rider;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Enumerated(EnumType.STRING)
    private RideStatus rideStatus;
    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;
    private Double fare;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;

    public String otp;


}
