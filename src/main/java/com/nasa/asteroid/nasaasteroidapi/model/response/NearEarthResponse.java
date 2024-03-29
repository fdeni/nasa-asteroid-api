package com.nasa.asteroid.nasaasteroidapi.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.nasa.asteroid.nasaasteroidapi.model.data.AsteroidListData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NearEarthResponse {
    @JsonProperty(value = "near_earth_objects")
    private List<AsteroidListData> asteroidListData;
}


