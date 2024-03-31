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
@Table(name = "close_approach_data")
public class CloseApproachDataAsteroid {
    @Id
    @Column(name = "cad_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cad_asteroid_id")
    private String asteroidId;

    @Column(name = "cad_close_approach_date")
    private String closeApproachDate;

    @Column(name = "cad_close_approach_date_full")
    private String closeApproachDateFull;

    @Column(name = "cad_epoch_date_close_approach")
    private Long epochDateCloseApproach;

    @Column(name = "cad_kilometers_per_second")
    private String kmPerSecond;

    @Column(name = "cad_kilometers_per_hour")
    private String kmPerHour;

    @Column(name = "cad_miles_per_hour")
    private String milePerHour;

    @Column(name = "cad_astronomical")
    private String astronomical;

    @Column(name = "cad_lunar")
    private String lunar;

    @Column(name = "cad_kilometers")
    private String kilometers;

    @Column(name = "cad_miles")
    private String miles;

    @Column(name = "cad_orbiting_body")
    private String orbitBody;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "cad_created_at")
    private Date createdAt;

    @Column(name = "cad_created_by")
    private Long createdBy;
}
