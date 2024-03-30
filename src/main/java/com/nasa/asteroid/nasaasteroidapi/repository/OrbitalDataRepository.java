package com.nasa.asteroid.nasaasteroidapi.repository;

import com.nasa.asteroid.nasaasteroidapi.entity.OrbitalDataAsteroid;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrbitalDataRepository extends JpaRepository<OrbitalDataAsteroid, Long> {
}
