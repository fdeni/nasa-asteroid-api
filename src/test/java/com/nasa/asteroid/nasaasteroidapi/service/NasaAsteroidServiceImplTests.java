package com.nasa.asteroid.nasaasteroidapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.asteroid.nasaasteroidapi.model.data.AsteroidListData;
import com.nasa.asteroid.nasaasteroidapi.model.data.DefaultData;
import com.nasa.asteroid.nasaasteroidapi.model.data.EstimatedDiameterData;
import com.nasa.asteroid.nasaasteroidapi.model.data.orbit.OrbitClassData;
import com.nasa.asteroid.nasaasteroidapi.model.data.orbit.OrbitalData;
import com.nasa.asteroid.nasaasteroidapi.model.request.AsteroidData;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;
import com.nasa.asteroid.nasaasteroidapi.repository.AsteroidRepository;
import com.nasa.asteroid.nasaasteroidapi.repository.EstimatedDiameterAsteroidRepository;
import com.nasa.asteroid.nasaasteroidapi.repository.OrbitalDataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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

    @Mock
    private AsteroidRepository asteroidRepository;

    @Mock
    private EstimatedDiameterAsteroidRepository estimatedDiameterAsteroidRepository;

    @Mock
    private OrbitalDataRepository orbitalDataRepository;

    private AsteroidData createMockAsteroidData() {
        AsteroidData asteroidData = new AsteroidData();
        asteroidData.setId("1");
        asteroidData.setReferenceNo("REF123");
        asteroidData.setName("Mock Asteroid");
        asteroidData.setDesignation("1234");
        asteroidData.setAbsoluteMagnitude("5.6");
        asteroidData.setIsHazardous(false);
        asteroidData.setIsSentry(false);

        EstimatedDiameterData estimatedDiameterData = new EstimatedDiameterData();
        estimatedDiameterData.setKilometers(new DefaultData(1.2, 3.4));
        estimatedDiameterData.setMeters(new DefaultData(1200.0, 3400.0));
        estimatedDiameterData.setMiles(new DefaultData(0.75, 2.11));
        estimatedDiameterData.setFeet(new DefaultData(3937.0, 11155.0));
        asteroidData.setEstimatedDiameterData(estimatedDiameterData);

        OrbitalData orbitalData = new OrbitalData();
        orbitalData.setOrbitId("orbit123");
        orbitalData.setOrbitDeterminationDate("");
        orbitalData.setFirstObservationDate("");
        orbitalData.setLastObservationDate("");
        orbitalData.setDataArcInDays(10);
        orbitalData.setObservationsUsed(5);
        orbitalData.setOrbitUncertainty("0");
        orbitalData.setMinimumOrbitIntersection("0.1");
        orbitalData.setJupiterTisserandInvariant("3.4");
        orbitalData.setEpochOsculation("2459000.5");
        orbitalData.setEccentricity("0.1");
        orbitalData.setSemiMajorAxis("2.5");
        orbitalData.setInclination("10.5");
        orbitalData.setAscendingNodeLongitude("20.0");
        orbitalData.setOrbitalPeriod("3.0");
        orbitalData.setPerihelionDistance("0.5");
        orbitalData.setPerihelionArgument("30.0");
        orbitalData.setAphelionDistance("4.5");
        orbitalData.setPerihelionTime("2459000.0");
        orbitalData.setMeanAnomaly("15.0");
        orbitalData.setMeanMotion("0.5");
        orbitalData.setEquinox("J2000");

        OrbitClassData orbitClassData = new OrbitClassData();
        orbitClassData.setType("AMO");
        orbitClassData.setDescription("Near-Earth asteroid orbits similar to that of 1221 Amor");
        orbitClassData.setRange("1.017 AU < q (perihelion) < 1.3 AU");
        orbitalData.setOrbitClassData(orbitClassData);

        asteroidData.setOrbitalData(orbitalData);

        return asteroidData;
    }

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

    @Test
    public void saveAsteroidShouldSuccesSaveData(){
        AsteroidData request = createMockAsteroidData();
        nasaAsteroidService.saveAsteroid(request);

        Mockito.verify(asteroidRepository, Mockito.times(1)).save(any());
        Mockito.verify(estimatedDiameterAsteroidRepository, Mockito.times(1)).save(any());
        Mockito.verify(orbitalDataRepository, Mockito.times(1)).save(any());
    }
}
