package com.nasa.asteroid.nasaasteroidapi.controller;

import com.nasa.asteroid.nasaasteroidapi.model.data.AsteroidListData;
import com.nasa.asteroid.nasaasteroidapi.model.response.BaseResponse;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;
import com.nasa.asteroid.nasaasteroidapi.service.NasaAsteroidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "nasa-api")
public class NasaAsteroidController {
    @Autowired
    private NasaAsteroidService nasaAsteroidService;

    @GetMapping
    private BaseResponse<List<AsteroidListData>> getAsteroidNearEarth(@RequestParam LocalDate startDate, @RequestParam LocalDate endDate) throws Exception {
        return BaseResponse.<List<AsteroidListData>>builder()
                .data(nasaAsteroidService.getAsteroidNearEarth(startDate, endDate))
                .status(HttpStatus.OK.value())
                .build();
    }
}
