package com.nasa.asteroid.nasaasteroidapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.asteroid.nasaasteroidapi.model.data.AsteroidListData;
import com.nasa.asteroid.nasaasteroidapi.model.response.AsteroidDetail;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class NasaAsteroidServiceImpl implements NasaAsteroidService {
    @Value("${neo.apiKey}")
    private String apiKey;

    @Value("${neo.feed.url}")
    private String neoFeedUrl;

    @Value("${neo.lookup.url}")
    private String neoLookUpUrl;


    @Override
    public NearEarthResponse getAsteroidNearEarth(LocalDate startDate, LocalDate endDate) {
        String url = neoFeedUrl + "start_date=" + startDate + "&end_date=" + endDate + "&api_key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<AsteroidListData> asteroids = new ArrayList<>();

        try {
            ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
            Map<String, Object> responseData = (Map<String, Object>) responseEntity.getBody().get("near_earth_objects");
            for (Map.Entry<String, Object> entry : responseData.entrySet()) {
                List<AsteroidListData> asteroidList = mapper.convertValue(entry.getValue(), new TypeReference<>() {
                });
                asteroids.addAll(asteroidList);

            }
            return NearEarthResponse.builder().asteroidListData(asteroids).build();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

    @Override
    public AsteroidDetail getAsteroidDetail(Long asteroidId) {
        String url = neoLookUpUrl + asteroidId + "?" + "&api_key=" + apiKey;
        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();

        try {
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
            String responseData = responseEntity.getBody();

            return mapper.readValue(responseData, AsteroidDetail.class);

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
