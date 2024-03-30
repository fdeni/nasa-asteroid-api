package com.nasa.asteroid.nasaasteroidapi.repository;

import com.nasa.asteroid.nasaasteroidapi.entity.Asteroid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsteroidRepository extends JpaRepository<Asteroid, Long>{
}
