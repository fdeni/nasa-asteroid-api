package com.nasa.asteroid.nasaasteroidapi.service;


import com.nasa.asteroid.nasaasteroidapi.model.request.AsteroidData;
import com.nasa.asteroid.nasaasteroidapi.model.response.AsteroidDetail;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;

import java.time.LocalDate;

public interface NasaAsteroidService {
    NearEarthResponse getAsteroidNearEarth (LocalDate startDate, LocalDate endDate);
    AsteroidDetail getAsteroidDetail (Long asteroidId);

    void saveAsteroid(AsteroidData request);
}
