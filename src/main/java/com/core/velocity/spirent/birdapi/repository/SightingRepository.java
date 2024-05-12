package com.core.velocity.spirent.birdapi.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.core.velocity.spirent.birdapi.model.Bird;
import com.core.velocity.spirent.birdapi.model.Sighting;

public interface SightingRepository extends JpaRepository<Sighting, String> {

    List<Sighting> findByBird(Bird bird);

    List<Sighting> findByLocation(String location);

    List<Sighting> findByDateTimeBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
