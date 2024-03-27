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
public class MilesData {
    @JsonProperty(value = "miles")
    private DefaultData miles;
}
