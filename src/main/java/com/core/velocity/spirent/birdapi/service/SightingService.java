package com.core.velocity.spirent.birdapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.core.velocity.spirent.birdapi.model.Bird;
import com.core.velocity.spirent.birdapi.model.Sighting;

import java.time.LocalDateTime;
import java.util.List;

public interface SightingService {

    public Page<Sighting> getAllSightings(Pageable pageable);

    public List<Sighting> getSightingsByBird(Bird bird);

    public List<Sighting> getSightingsByLocation(String location);

    public List<Sighting> getSightingsByTimeInterval(LocalDateTime startDateTime, LocalDateTime endDateTime);
}
