package com.sky.QuickRide.quickRide.App.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(indexes = {
        @Index(name = "idx_rating_driver",columnList = "driver_id"),
        @Index(name = "idx_rating_driver",columnList = "driver_id")
})
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Ride ride;

    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Rider rider;

    private Integer driverRating;
    private Integer riderRating;
}
