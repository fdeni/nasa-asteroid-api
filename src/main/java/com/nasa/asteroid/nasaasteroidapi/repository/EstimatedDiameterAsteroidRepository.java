package com.nasa.asteroid.nasaasteroidapi.repository;

import com.nasa.asteroid.nasaasteroidapi.entity.EstimatedDiameterAsteroid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstimatedDiameterAsteroidRepository extends JpaRepository<EstimatedDiameterAsteroid, Long> {
}
