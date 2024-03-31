package com.nasa.asteroid.nasaasteroidapi.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nasa.asteroid.nasaasteroidapi.entity.Asteroid;
import com.nasa.asteroid.nasaasteroidapi.entity.CloseApproachDataAsteroid;
import com.nasa.asteroid.nasaasteroidapi.entity.EstimatedDiameterAsteroid;
import com.nasa.asteroid.nasaasteroidapi.entity.OrbitalDataAsteroid;
import com.nasa.asteroid.nasaasteroidapi.model.data.AsteroidListData;
import com.nasa.asteroid.nasaasteroidapi.model.data.EstimatedDiameterData;
import com.nasa.asteroid.nasaasteroidapi.model.data.closeapproach.CloseApproachData;
import com.nasa.asteroid.nasaasteroidapi.model.data.orbit.OrbitClassData;
import com.nasa.asteroid.nasaasteroidapi.model.data.orbit.OrbitalData;
import com.nasa.asteroid.nasaasteroidapi.model.request.AsteroidData;
import com.nasa.asteroid.nasaasteroidapi.model.response.AsteroidDetail;
import com.nasa.asteroid.nasaasteroidapi.model.response.NearEarthResponse;
import com.nasa.asteroid.nasaasteroidapi.repository.AsteroidRepository;
import com.nasa.asteroid.nasaasteroidapi.repository.CloseApproachDataRepository;
import com.nasa.asteroid.nasaasteroidapi.repository.EstimatedDiameterAsteroidRepository;
import com.nasa.asteroid.nasaasteroidapi.repository.OrbitalDataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
public class NasaAsteroidServiceImpl implements NasaAsteroidService {
    private final String NEO_FEED_URL= "https://api.nasa.gov/neo/rest/v1/feed?";
    private final String NEO_LOOK_UP_URL= "https://api.nasa.gov/neo/rest/v1/neo/";
    private final String API_KEY ="DEMO_KEY";

    @Autowired
    private AsteroidRepository asteroidRepository;

    @Autowired
    private EstimatedDiameterAsteroidRepository estimatedDiameterAsteroidRepository;

    @Autowired
    private OrbitalDataRepository orbitalDataRepository;

    @Autowired
    private CloseApproachDataRepository closeApproachDataRepository;

    @Override
    public NearEarthResponse getAsteroidNearEarth(LocalDate startDate, LocalDate endDate) {
        String start_date = "start_date=";
        String end_date = "&end_date=";
        String api_key = "&api_key=";
        String key_object = "near_earth_objects";

        String url = NEO_FEED_URL + start_date + startDate + end_date + endDate + api_key + API_KEY;

        log.info("neo feed url {}:", url);

        RestTemplate restTemplate = new RestTemplate();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<AsteroidListData> asteroids = new ArrayList<>();

        try {
            ResponseEntity<Map> responseEntity = restTemplate.getForEntity(url, Map.class);
            Map<String, Object> responseData = (Map<String, Object>) responseEntity.getBody().get(key_object);
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
        String api_key = "&api_key=";
        String url = NEO_LOOK_UP_URL + asteroidId + "?" + api_key + API_KEY;

        log.info("neo look up url {}:", url);

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

    @Override
    public void saveAsteroid(AsteroidData request) {
        Asteroid asteroid = new Asteroid();
        asteroid.setAsteroidId(request.getId());
        asteroid.setNeoRefId(request.getReferenceNo());
        asteroid.setName(request.getName());
        asteroid.setDesignation(request.getDesignation());
        asteroid.setAbsoluteMagnitude(request.getAbsoluteMagnitude());
        asteroid.setIsHazardousAsteroid(request.getIsHazardous());
        asteroid.setIsSentry(request.getIsSentry());
        asteroid.setCreatedBy(Long.valueOf(request.getId()));

        asteroidRepository.save(asteroid);
        log.info("success save asteroid data");

        //save estimated diameter data
        EstimatedDiameterData diameterData = request.getEstimatedDiameterData();
        EstimatedDiameterAsteroid estimatedDiameter = new EstimatedDiameterAsteroid();
        estimatedDiameter.setAsteroidId(request.getId());
        estimatedDiameter.setKilometerEstimatedDiameterMin(diameterData.getKilometers().getEstimatedDiameterMin());
        estimatedDiameter.setKilometerEstimatedDiameterMax(diameterData.getKilometers().getEstimatedDiameterMax());
        estimatedDiameter.setMeterEstimatedDiameterMin(diameterData.getMeters().getEstimatedDiameterMin());
        estimatedDiameter.setMeterEstimatedDiameterMax(diameterData.getMeters().getEstimatedDiameterMax());
        estimatedDiameter.setMilesEstimatedDiameterMin(diameterData.getMiles().getEstimatedDiameterMin());
        estimatedDiameter.setMilesEstimatedDiameterMax(diameterData.getMiles().getEstimatedDiameterMax());
        estimatedDiameter.setFeetEstimatedDiameterMin(diameterData.getFeet().getEstimatedDiameterMin());
        estimatedDiameter.setFeetEstimatedDiameterMax(diameterData.getFeet().getEstimatedDiameterMax());
        estimatedDiameter.setCreatedBy(Long.valueOf(request.getId()));

        estimatedDiameterAsteroidRepository.save(estimatedDiameter);
        log.info("success save estimated diameter asteroid data");

        //save close approach data
        List<CloseApproachData> closeApproachDataList = request.getCloseApproachDataLists();
        for (CloseApproachData closeApproachData: closeApproachDataList) {
            CloseApproachDataAsteroid closeApproachDataAsteroid = new CloseApproachDataAsteroid();
            closeApproachDataAsteroid.setAsteroidId(request.getId());
            closeApproachDataAsteroid.setCloseApproachDate(closeApproachData.getCloseApproachDate());
            closeApproachDataAsteroid.setCloseApproachDateFull(closeApproachData.getCloseApproachDateFull());
            closeApproachDataAsteroid.setEpochDateCloseApproach(closeApproachData.getEpochDateCloseApproach());
            closeApproachDataAsteroid.setKmPerSecond(closeApproachData.getRelativeVelocity().getKmPerSecond());
            closeApproachDataAsteroid.setKmPerHour(closeApproachData.getRelativeVelocity().getKmPerHour());
            closeApproachDataAsteroid.setMilePerHour(closeApproachData.getRelativeVelocity().getMilePerHour());
            closeApproachDataAsteroid.setAstronomical(closeApproachData.getMissDistance().getAstronomical());
            closeApproachDataAsteroid.setLunar(closeApproachData.getMissDistance().getLunar());
            closeApproachDataAsteroid.setKilometers(closeApproachData.getMissDistance().getKilometers());
            closeApproachDataAsteroid.setMiles(closeApproachData.getMissDistance().getMiles());
            closeApproachDataAsteroid.setOrbitBody(closeApproachData.getOrbitBody());
            closeApproachDataAsteroid.setCreatedBy(Long.valueOf(request.getId()));

            closeApproachDataRepository.save(closeApproachDataAsteroid);
        }

        log.info("success save close approach asteroid data");

        //save orbital data
        OrbitalData orbitalData = request.getOrbitalData();
        OrbitClassData orbitClassData = request.getOrbitalData().getOrbitClassData();
        OrbitalDataAsteroid orbitalDataAsteroid = new OrbitalDataAsteroid();
        orbitalDataAsteroid.setAsteroidId(request.getId());
        orbitalDataAsteroid.setOrbitId(orbitalData.getOrbitId());
        orbitalDataAsteroid.setOrbitDeterminationDate(orbitalData.getOrbitDeterminationDate());
        orbitalDataAsteroid.setFirstObservationDate(orbitalData.getFirstObservationDate());
        orbitalDataAsteroid.setLastObservationDate(orbitalData.getLastObservationDate());
        orbitalDataAsteroid.setDataArcInDays(orbitalData.getDataArcInDays());
        orbitalDataAsteroid.setObservationsUsed(orbitalData.getObservationsUsed());
        orbitalDataAsteroid.setOrbitUncertainty(orbitalData.getOrbitUncertainty());
        orbitalDataAsteroid.setMinimumOrbitIntersection(orbitalData.getMinimumOrbitIntersection());
        orbitalDataAsteroid.setJupiterTisserandInvariant(orbitalData.getJupiterTisserandInvariant());
        orbitalDataAsteroid.setEpochOsculation(orbitalData.getEpochOsculation());
        orbitalDataAsteroid.setEccentricity(orbitalData.getEccentricity());
        orbitalDataAsteroid.setSemiMajorAxis(orbitalData.getSemiMajorAxis());
        orbitalDataAsteroid.setInclination(orbitalData.getInclination());
        orbitalDataAsteroid.setAscendingNodeLongitude(orbitalData.getAscendingNodeLongitude());
        orbitalDataAsteroid.setOrbitalPeriod(orbitalData.getOrbitalPeriod());
        orbitalDataAsteroid.setPerihelionDistance(orbitalData.getPerihelionDistance());
        orbitalDataAsteroid.setPerihelionArgument(orbitalData.getPerihelionArgument());
        orbitalDataAsteroid.setAphelionDistance(orbitalData.getAphelionDistance());
        orbitalDataAsteroid.setPerihelionTime(orbitalData.getPerihelionTime());
        orbitalDataAsteroid.setMeanAnomaly(orbitalData.getMeanAnomaly());
        orbitalDataAsteroid.setMeanMotion(orbitalData.getMeanMotion());
        orbitalDataAsteroid.setEquinox(orbitalData.getEquinox());
        orbitalDataAsteroid.setType(orbitClassData.getType());
        orbitalDataAsteroid.setDescription(orbitClassData.getDescription());
        orbitalDataAsteroid.setRange(orbitClassData.getRange());

        orbitalDataRepository.save(orbitalDataAsteroid);
        log.info("success save orbital asteroid data");
    }
}
