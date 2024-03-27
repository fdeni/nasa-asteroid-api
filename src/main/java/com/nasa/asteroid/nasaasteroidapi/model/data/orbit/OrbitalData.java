package com.nasa.asteroid.nasaasteroidapi.model.data.orbit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrbitalData {
    @JsonProperty(value = "orbitId")
    private String orbitId;

    @JsonProperty(value = "orbitDeterminationDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDate orbitDeterminationDate;

    @JsonProperty(value = "firstObservationDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate firstObservationDate;

    @JsonProperty(value = "lastObservationDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate lastObservationDate;

    @JsonProperty(value = "dataArcInDays")
    private int dataArcInDays;

    @JsonProperty(value = "observationsUsed")
    private int observationsUsed;

    @JsonProperty(value = "orbitUncertainty")
    private String orbitUncertainty;

    @JsonProperty(value = "minimumOrbitIntersection")
    private String minimumOrbitIntersection;

    @JsonProperty(value = "jupiterTisserandInvariant")
    private String jupiterTisserandInvariant;

    @JsonProperty(value = "epochOsculation")
    private String epochOsculation;

    @JsonProperty(value = "eccentricity")
    private String eccentricity;

    @JsonProperty(value = "semiMajorAxis")
    private String semiMajorAxis;

    @JsonProperty(value = "inclination")
    private String inclination;

    @JsonProperty(value = "ascendingNodeLongitude")
    private String ascendingNodeLongitude;

    @JsonProperty(value = "orbitalPeriod")
    private String orbitalPeriod;

    @JsonProperty(value = "perihelionDistance")
    private String perihelionDistance;

    @JsonProperty(value = "perihelionArgument")
    private String perihelionArgument;

    @JsonProperty(value = "aphelionDistance")
    private String aphelionDistance;

    @JsonProperty(value = "perihelionTime")
    private String perihelionTime;

    @JsonProperty(value = "meanAnomaly")
    private String meanAnomaly;

    @JsonProperty(value = "meanMotion")
    private String meanMotion;

    @JsonProperty(value = "equinox")
    private String equinox;

    @JsonProperty(value = "orbitClass")
    private OrbitClassData orbitClassData;
}
