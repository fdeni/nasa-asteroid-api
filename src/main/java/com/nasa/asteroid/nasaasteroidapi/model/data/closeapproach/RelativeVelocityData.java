package com.nasa.asteroid.nasaasteroidapi.model.data.closeapproach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RelativeVelocityData {
    @JsonProperty(value = "kmPerSecond")
    private String kmPerSecond;

    @JsonProperty(value = "kmPerHour")
    private String kmPerHour;

    @JsonProperty(value = "milePerHour")
    private String milePerHour;
}
