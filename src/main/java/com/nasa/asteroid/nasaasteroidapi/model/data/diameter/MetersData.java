package com.nasa.asteroid.nasaasteroidapi.model.data.diameter;

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
public class MetersData {
    @JsonProperty(value = "meters")
    private DefaultData meters;
}
