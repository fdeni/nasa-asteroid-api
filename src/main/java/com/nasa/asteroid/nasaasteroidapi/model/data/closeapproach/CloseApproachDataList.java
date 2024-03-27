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
public class CloseApproachDataList {
    @JsonProperty(value = "closeApproachData")
    private CloseApproachData closeApproachData;
}
