package com.nasa.asteroid.nasaasteroidapi.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nasa.asteroid.nasaasteroidapi.model.data.closeapproach.CloseApproachDataList;
import com.nasa.asteroid.nasaasteroidapi.model.data.diameter.EstimatedDiameterData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AsteroidListData {
    @JsonProperty(value = "id")
    private String id;

    @JsonProperty(value = "referenceNo")
    private String referenceNo;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "absoluteMagnitude")
    private String absoluteMagnitude;

    @JsonProperty(value = "estimatedDiameterData")
    private EstimatedDiameterData estimatedDiameterData;

    @JsonProperty(value = "closeApproachDataLists")
    private List<CloseApproachDataList> closeApproachDataLists;

    @JsonProperty(value = "isHazardous")
    private Boolean isHazardous;

    @JsonProperty(value = "isSentry")
    private Boolean isSentry;
}
