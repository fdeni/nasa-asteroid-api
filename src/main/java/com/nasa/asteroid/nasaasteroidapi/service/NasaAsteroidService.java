package com.nasa.asteroid.nasaasteroidapi.service;

import com.nasa.asteroid.nasaasteroidapi.model.data.AsteroidListData;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;

import java.time.LocalDate;
import java.util.List;

public interface NasaAsteroidService {
    public List<AsteroidListData> getAsteroidNearEarth (LocalDate startDate, LocalDate endDate) throws Exception;
}
