package com.nasa.asteroid.nasaasteroidapi.model.data.closeapproach;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CloseApproachData {
    @JsonProperty(value = "closeApproachDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate closeApproachDate;

    @JsonProperty(value = "closeApproachDateFull")
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:ss")
    private LocalDate closeApproachDateFull;

    @JsonProperty(value = "epochDateCloseApproach")
    private int epochDateCloseApproach;

    @JsonProperty(value = "relativeVelocity")
    private RelativeVelocityData relativeVelocity;

    @JsonProperty(value = "missDistance")
    private MissDistanceData missDistance;

    @JsonProperty(value = "orbitBody")
    private String orbitBody;
}
