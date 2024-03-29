package com.nasa.asteroid.nasaasteroidapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.asteroid.nasaasteroidapi.model.data.AsteroidListData;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class NasaAsteroidServiceImplTests {
    @InjectMocks
    private NasaAsteroidServiceImpl nasaAsteroidService;

    @Mock
    RestTemplate restTemplate;

    @Mock
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void getAsteroidNearEarthShouldSucces() {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("2022-01-01", Collections.singletonList(new AsteroidListData()));

        ResponseEntity<Map> responseEntity = new ResponseEntity<>(Collections.singletonMap("near_earth_objects", responseData), HttpStatus.OK);

        when(restTemplate.getForEntity("url", Map.class)).thenReturn(responseEntity);

        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 1);
        NearEarthResponse result = nasaAsteroidService.getAsteroidNearEarth(startDate, endDate);

        assertEquals(23, result.getAsteroidListData().size());
    }

    @Test
    public void getAsteroidNearEarthWithException(){
        LocalDate startDate = LocalDate.of(2022, 1, 1);
        LocalDate endDate = LocalDate.of(2022, 1, 10);
        Throwable exception = assertThrows(
                ResponseStatusException.class, () -> nasaAsteroidService.getAsteroidNearEarth(startDate, endDate)
        );

        assertEquals("500 INTERNAL_SERVER_ERROR \"400 Bad Request: \"{\"code\":400,\"http_error\":\"BAD_REQUEST\",\"error_message\":\"Date Format Exception - Expected format (yyyy-mm-dd) - The Feed date limit is only 7 Days\",\"request\":\"http://api.nasa.gov/rest/v1/feed?start_date=2022-01-01&end_date=2022-01-10\"}\"\"", exception.getMessage());
    }

    @Test
    public void getAsteroidDetail_Exception() {
        ResponseStatusException responseStatusException = new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error message");
        when(restTemplate.getForEntity(any(URI.class), any(Class.class))).thenThrow(responseStatusException);

        assertThrows(ResponseStatusException.class, () -> nasaAsteroidService.getAsteroidDetail(1L));
    }
}
