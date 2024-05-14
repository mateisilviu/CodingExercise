package com.core.velocity.spirent.birdapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.core.velocity.spirent.birdapi.dto.AddSightingDTO;
import com.core.velocity.spirent.birdapi.dto.SightingDTO;
import com.core.velocity.spirent.birdapi.model.Bird;
import com.core.velocity.spirent.birdapi.model.Sighting;
import com.core.velocity.spirent.birdapi.repository.BirdRepository;
import com.core.velocity.spirent.birdapi.repository.SightingRepository;
import com.core.velocity.spirent.birdapi.service.SightingService;

@Service
public class SightingServiceImpl implements SightingService {

    @Autowired
    private SightingRepository sightingRepository;

    @Autowired
    private BirdRepository birdRepository;

    @Override
    public Page<SightingDTO> getAllSightings(Pageable pageable) {
        Page<Sighting> sightings = sightingRepository.findAll(pageable);
        return sightings.map(SightingDTO::new);
    }

    @Override
    public List<SightingDTO> getSightingsByBirdId(String birdId) {
        var bird = new Bird();
        bird.setId(birdId);
        var sightings = sightingRepository.findByBird(bird);
        return sightings.stream().map(SightingDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<SightingDTO> getSightingsByLocation(String location) {
        var sightings = sightingRepository.findByLocation(location);
        return sightings.stream().map(SightingDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<SightingDTO> getSightingsByTimeInterval(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        var sightings = sightingRepository.findByDateTimeBetween(startDateTime, endDateTime);
        return sightings.stream().map(SightingDTO::new).collect(Collectors.toList());
    }

    @Override
    public SightingDTO addSighting(AddSightingDTO addSightingDTO) {
        // Validate if the bird exists
        Bird bird = birdRepository.findById(addSightingDTO.getBirdId())
                .orElseThrow(() -> new IllegalArgumentException("Bird not found"));

        var sighting = new Sighting();
        sighting.setDateTime(addSightingDTO.getDateTime());
        sighting.setLocation(addSightingDTO.getLocation());
        sighting.setBird(bird);
        Sighting savedSighting = sightingRepository.save(sighting);
        return new SightingDTO(savedSighting);
    }

    @Override
    public List<SightingDTO> getSightingsByFilters(String location, LocalDateTime startDateTime,
            LocalDateTime endDateTime) {
        var sightings = sightingRepository.findByLocationAndDateTimeBetween(location, startDateTime, endDateTime);
        return sightings.stream().map(SightingDTO::new).collect(Collectors.toList());
    }

}
