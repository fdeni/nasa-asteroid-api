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
    @JsonProperty(value = "kilometers_per_second")
    private String kmPerSecond;

    @JsonProperty(value = "kilometers_per_hour")
    private String kmPerHour;

    @JsonProperty(value = "miles_per_hour")
    private String milePerHour;
}
