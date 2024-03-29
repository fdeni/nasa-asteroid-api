package com.nasa.asteroid.nasaasteroidapi.model.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nasa.asteroid.nasaasteroidapi.model.data.closeapproach.CloseApproachData;
import com.nasa.asteroid.nasaasteroidapi.model.data.orbit.OrbitalData;
import com.nasa.asteroid.nasaasteroidapi.model.data.EstimatedDiameterData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AsteroidDetail {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "neo_reference_id")
    private String referenceNo;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "designation")
    private String designation;

    @JsonProperty(value = "absolute_magnitude_h")
    private String absoluteMagnitude;

    @JsonProperty(value = "estimated_diameter")
    private EstimatedDiameterData estimatedDiameterData;

    @JsonProperty(value = "is_potentially_hazardous_asteroid")
    private Boolean isHazardous;

    @JsonProperty(value = "close_approach_data")
    private List<CloseApproachData> closeApproachDataLists;

    @JsonProperty(value = "orbital_data")
    private OrbitalData orbitalData;

    @JsonProperty(value = "is_sentry_object")
    private Boolean isSentry;
}
