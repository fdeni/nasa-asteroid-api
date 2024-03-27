package com.nasa.asteroid.nasaasteroidapi.model.data.diameter;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstimatedDiameterData {
    @JsonProperty(value = "kilometers")
    private KilometersData kilometers;

    @JsonProperty(value = "meters")
    private MetersData meters;

    @JsonProperty(value = "miles")
    private MilesData miles;

    @JsonProperty(value = "feet")
    private FeetData feet;
}
