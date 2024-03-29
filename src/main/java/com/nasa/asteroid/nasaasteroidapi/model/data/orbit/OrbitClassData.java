package com.nasa.asteroid.nasaasteroidapi.model.data.orbit;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrbitClassData {
    @JsonProperty(value = "orbit_class_type")
    private String type;

    @JsonProperty(value = "orbit_class_description")
    private String description;

    @JsonProperty(value = "orbit_class_range")
    private String range;
}
