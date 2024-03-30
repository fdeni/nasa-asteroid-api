package com.nasa.asteroid.nasaasteroidapi.entity;

import com.nasa.asteroid.nasaasteroidapi.model.data.orbit.OrbitClassData;
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
@Table(name = "orbital_data")
public class OrbitalDataAsteroid {
    @Id
    @Column(name = "od_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "od_asteroid_id")
    private String asteroidId;

    @Column(name = "od_orbit_id")
    private String orbitId;

    @Column(name = "od_orbit_determination_date")
    private String orbitDeterminationDate;

    @Column(name = "od_first_observation_date")
    private String firstObservationDate;

    @Column(name = "od_last_observation_date")
    private String lastObservationDate;

    @Column(name = "od_data_arc_in_days")
    private int dataArcInDays;

    @Column(name = "od_observations_used")
    private int observationsUsed;

    @Column(name = "od_orbit_uncertainty")
    private String orbitUncertainty;

    @Column(name = "od_minimum_orbit_intersection")
    private String minimumOrbitIntersection;

    @Column(name = "od_jupiter_tisserand_invariant")
    private String jupiterTisserandInvariant;

    @Column(name = "od_epoch_osculation")
    private String epochOsculation;

    @Column(name = "od_eccentricity")
    private String eccentricity;

    @Column(name = "od_semi_major_axis")
    private String semiMajorAxis;

    @Column(name = "od_inclination")
    private String inclination;

    @Column(name = "od_ascending_node_longitude")
    private String ascendingNodeLongitude;

    @Column(name = "od_orbital_period")
    private String orbitalPeriod;

    @Column(name = "od_perihelion_distance")
    private String perihelionDistance;

    @Column(name = "od_perihelion_argument")
    private String perihelionArgument;

    @Column(name = "od_aphelion_distance")
    private String aphelionDistance;

    @Column(name = "od_perihelion_time")
    private String perihelionTime;

    @Column(name = "od_mean_anomaly")
    private String meanAnomaly;

    @Column(name = "od_mean_motion")
    private String meanMotion;

    @Column(name = "od_equinox")
    private String equinox;

    @Column(name = "od_orbit_class_type")
    private String type;

    @Column(name = "od_orbit_class_description")
    private String description;

    @Column(name = "od_orbit_class_range")
    private String range;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "od_created_at")
    private Date createdAt;

    @Column(name = "od_created_by")
    private Long createdBy;
}
