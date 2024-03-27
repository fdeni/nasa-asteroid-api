package com.nasa.asteroid.nasaasteroidapi.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nasa.asteroid.nasaasteroidapi.model.data.orbit.OrbitalData;
import com.nasa.asteroid.nasaasteroidapi.model.data.closeapproach.CloseApproachDataList;
import com.nasa.asteroid.nasaasteroidapi.model.data.diameter.EstimatedDiameterData;

import java.util.List;

public class AsteroidDetail {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "referenceNo")
    private String referenceNo;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "designation")
    private String designation;

    @JsonProperty(value = "absoluteMagnitude")
    private String absoluteMagnitude;

    @JsonProperty(value = "estimatedDiameterData")
    private EstimatedDiameterData estimatedDiameterData;

    @JsonProperty(value = "isHazardous")
    private Boolean isHazardous;

    @JsonProperty(value = "closeApproachDataLists")
    private List<CloseApproachDataList> closeApproachDataLists;
    @JsonProperty(value = "orbitalData")
    private OrbitalData orbitalData;

    @JsonProperty(value = "isSentry")
    private Boolean isSentry;
}
