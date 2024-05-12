package com.core.velocity.spirent.birdapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.core.velocity.spirent.birdapi.model.Bird;
import com.core.velocity.spirent.birdapi.model.Sighting;
import com.core.velocity.spirent.birdapi.repository.SightingRepository;
import com.core.velocity.spirent.birdapi.service.SightingService;

@Service
public class SightingServiceImpl implements SightingService {

    @Autowired
    private SightingRepository sightingRepository;

    @Override
    public Page<Sighting> getAllSightings(Pageable pageable) {
        return sightingRepository.findAll(pageable);
    }

    @Override
    public List<Sighting> getSightingsByBird(Bird bird) {
        return sightingRepository.findByBird(bird);
    }

    @Override
    public List<Sighting> getSightingsByLocation(String location) {
        return sightingRepository.findByLocation(location);
    }

    @Override
    public List<Sighting> getSightingsByTimeInterval(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return sightingRepository.findByDateTimeBetween(startDateTime, endDateTime);
    }

}
