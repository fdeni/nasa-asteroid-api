package com.nasa.asteroid.nasaasteroidapi.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nasa.asteroid.nasaasteroidapi.model.data.DefaultData;
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
    private DefaultData kilometers;

    @JsonProperty(value = "meters")
    private DefaultData meters;

    @JsonProperty(value = "miles")
    private DefaultData miles;

    @JsonProperty(value = "feet")
    private DefaultData feet;
}
