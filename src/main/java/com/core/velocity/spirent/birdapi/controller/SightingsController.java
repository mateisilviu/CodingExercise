package com.core.velocity.spirent.birdapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.velocity.spirent.birdapi.config.exceptions.BadRequestException;
import com.core.velocity.spirent.birdapi.service.SightingService;
import com.core.velocity.spirent.birdapi.dto.AddSightingDTO;
import com.core.velocity.spirent.birdapi.dto.SightingDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SightingsController {

    @Autowired
    final SightingService sightingService;

    @GetMapping("/sightings/all")
    public Page<SightingDTO> getAll(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
        return sightingService.getAllSightings(pageable);
    }

    @PostMapping("/sightings/add")
    public SightingDTO add(@RequestBody AddSightingDTO addSightingDTO) {
        return sightingService.addSighting(addSightingDTO);
    }

    @GetMapping("/sightings/filter")
    public ResponseEntity<List<SightingDTO>> getSightingsByBirdLocationAndTimeInterval(
            @RequestParam(name = "birdId", required = false) String birdId,
            @RequestParam(name = "location", required = false) String location,
            @RequestParam(name = "startDateTime", required = false) String startDateTimeStr,
            @RequestParam(name = "endDateTime", required = false) String endDateTimeStr) {

        if (birdId != null) {
            return new ResponseEntity<>(sightingService.getSightingsByBirdId(birdId), HttpStatus.OK);
        } else if (location != null && startDateTimeStr != null && endDateTimeStr != null) {
            LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr);
            LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr);
            return new ResponseEntity<>(sightingService.getSightingsByFilters(location, startDateTime, endDateTime),
                    HttpStatus.OK);
        } else if (location != null) {
            return new ResponseEntity<>(sightingService.getSightingsByLocation(location), HttpStatus.OK);
        } else if (startDateTimeStr != null && endDateTimeStr != null) {
            LocalDateTime startDateTime = LocalDateTime.parse(startDateTimeStr);
            LocalDateTime endDateTime = LocalDateTime.parse(endDateTimeStr);
            return new ResponseEntity<>(sightingService.getSightingsByTimeInterval(startDateTime, endDateTime),
                    HttpStatus.OK);
        } else {
            // If none of the parameters are provided, return a bad request response
            throw new BadRequestException();
        }
    }
}
