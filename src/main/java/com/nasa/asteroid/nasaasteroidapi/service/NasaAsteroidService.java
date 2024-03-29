package com.nasa.asteroid.nasaasteroidapi.service;


import com.nasa.asteroid.nasaasteroidapi.model.response.AsteroidDetail;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;

import java.time.LocalDate;

public interface NasaAsteroidService {
    NearEarthResponse getAsteroidNearEarth (LocalDate startDate, LocalDate endDate) throws Exception;
    AsteroidDetail getAsteroidDetail (Long asteroidId) throws Exception;
}
