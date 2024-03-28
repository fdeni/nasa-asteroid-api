package com.nasa.asteroid.nasaasteroidapi.model.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nasa.asteroid.nasaasteroidapi.model.data.closeapproach.CloseApproachData;
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
public class AsteroidListData {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "neo_reference_id")
    private String referenceNo;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "absolute_magnitude_h")
    private String absoluteMagnitude;

    @JsonProperty(value = "estimated_diameter")
    private EstimatedDiameterData estimatedDiameterData;

    @JsonProperty(value = "close_approach_data")
    private List<CloseApproachData> closeApproachDataLists;

    @JsonProperty(value = "is_potentially_hazardous_asteroid")
    private Boolean isHazardous;

    @JsonProperty(value = "is_sentry_object")
    private Boolean isSentry;
}
