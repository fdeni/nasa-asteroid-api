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
@Table(name = "mr_asteroid")
public class Asteroid {
    @Id
    @Column(name = "ma_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ma_asteroid_id")
    private String asteroidId;

    @Column(name = "ma_neo_reference_id")
    private String neoRefId;

    @Column(name = "ma_name")
    private String name;

    @Column(name = "ma_designation")
    private String designation;

    @Column(name = "ma_absolute_magnitude_h")
    private String absoluteMagnitude;

    @Column(name = "ma_is_potentially_hazardous_asteroid")
    private Boolean isHazardousAsteroid;

    @Column(name = "is_sentry_object")
    private Boolean isSentry;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ma_created_at")
    private Date createdAt;

    @Column(name = "ma_created_by")
    private Long createdBy;
}
