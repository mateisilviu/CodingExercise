package com.core.velocity.spirent.birdapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.core.velocity.spirent.birdapi.dto.AddSightingDTO;
import com.core.velocity.spirent.birdapi.dto.SightingDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface SightingService {

    public Page<SightingDTO> getAllSightings(Pageable pageable);

    public List<SightingDTO> getSightingsByBirdId(String birdId);

    public List<SightingDTO> getSightingsByLocation(String location);

    public List<SightingDTO> getSightingsByTimeInterval(LocalDateTime startDateTime, LocalDateTime endDateTime);

    public SightingDTO addSighting(AddSightingDTO addSightingDTO);

    public List<SightingDTO> getSightingsByFilters(String location, LocalDateTime startDateTime,
            LocalDateTime endDateTime);

    public SightingDTO modifySighting(String id, AddSightingDTO modifySightingDTO);

    public void deleteSighting(String id);
}
