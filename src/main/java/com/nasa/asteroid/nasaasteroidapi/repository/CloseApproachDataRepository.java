package com.nasa.asteroid.nasaasteroidapi.repository;

import com.nasa.asteroid.nasaasteroidapi.entity.CloseApproachDataAsteroid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CloseApproachDataRepository extends JpaRepository<CloseApproachDataAsteroid, Long> {
    public List<CloseApproachDataAsteroid> findByAsteroidId(String asteroidId);
}
