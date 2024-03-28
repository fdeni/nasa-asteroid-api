package com.nasa.asteroid.nasaasteroidapi.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DefaultData {
    @JsonProperty(value = "estimated_diameter_min")
    private Double estimatedDiameterMin;

    @JsonProperty(value = "estimated_diameter_max")
    private Double estimatedDiameterMax;
}
