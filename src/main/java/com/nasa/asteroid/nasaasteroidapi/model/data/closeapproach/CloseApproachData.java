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
public class CloseApproachData {
    @JsonProperty(value = "close_approach_date")
    private String closeApproachDate;

    @JsonProperty(value = "close_approach_date_full")
    private String closeApproachDateFull;

    @JsonProperty(value = "epoch_date_close_approach")
    private Long epochDateCloseApproach;

    @JsonProperty(value = "relative_velocity")
    private RelativeVelocityData relativeVelocity;

    @JsonProperty(value = "miss_distance")
    private MissDistanceData missDistance;

    @JsonProperty(value = "orbiting_body")
    private String orbitBody;
}
