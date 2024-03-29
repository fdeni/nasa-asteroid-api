package com.nasa.asteroid.nasaasteroidapi.controller;

import com.nasa.asteroid.nasaasteroidapi.model.response.AsteroidDetail;
import com.nasa.asteroid.nasaasteroidapi.model.response.BaseResponse;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;
import com.nasa.asteroid.nasaasteroidapi.service.NasaAsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(value = "nasa-api")
public class NasaAsteroidController {
    @Autowired
    private NasaAsteroidService nasaAsteroidService;

    @GetMapping("neo-feed")
    private BaseResponse<NearEarthResponse> getAsteroidNearEarth(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) throws Exception {
        return BaseResponse.<NearEarthResponse>builder()
                .data(nasaAsteroidService.getAsteroidNearEarth(startDate, endDate))
                .status(HttpStatus.OK.value())
                .build();
    }

    @GetMapping("neo-look-up/{asteroidId}")
    private BaseResponse<AsteroidDetail> getAsteroidDetail(@PathVariable Long asteroidId) throws Exception {
        return BaseResponse.<AsteroidDetail>builder()
                .data(nasaAsteroidService.getAsteroidDetail(asteroidId))
                .status(HttpStatus.OK.value())
                .build();
    }
}
