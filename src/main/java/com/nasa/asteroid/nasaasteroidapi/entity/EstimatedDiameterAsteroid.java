package com.nasa.asteroid.nasaasteroidapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;

@Getter
@Setter
@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "estimated_diameter")
public class EstimatedDiameterAsteroid {
    @Id
    @Column(name = "ed_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ed_asteroid_id")
    private String asteroidId;

    @Column(name = "ed_kilometer_estimated_diameter_min")
    private Double kilometerEstimatedDiameterMin;

    @Column(name = "ed_kilometer_estimated_diameter_max")
    private Double kilometerEstimatedDiameterMax;

    @Column(name = "ed_meter_estimated_diameter_min")
    private Double meterEstimatedDiameterMin;

    @Column(name = "ed_meter_estimated_diameter_max")
    private Double meterEstimatedDiameterMax;

    @Column(name = "ed_miles_estimated_diameter_min")
    private Double milesEstimatedDiameterMin;

    @Column(name = "ed_miles_estimated_diameter_max")
    private Double milesEstimatedDiameterMax;

    @Column(name = "ed_feet_estimated_diameter_min")
    private Double feetEstimatedDiameterMin;

    @Column(name = "ed_feet_estimated_diameter_max")
    private Double feetEstimatedDiameterMax;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ed_created_at")
    private Date createdAt;

    @Column(name = "ed_created_by")
    private Long createdBy;
}
