package com.core.velocity.spirent.birdapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.velocity.spirent.birdapi.service.BirdService;
import com.core.velocity.spirent.birdapi.config.exceptions.ResourceNotFoundException;
import com.core.velocity.spirent.birdapi.dto.AddBirdDTO;
import com.core.velocity.spirent.birdapi.dto.BirdDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BirdController {

    @Autowired
    final BirdService birdService;

    @GetMapping("/birds/all")
    public Page<BirdDTO> getAll(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
        return birdService.getAllBirds(pageable);
    }

    @PostMapping("/birds/add")
    public BirdDTO add(@RequestBody AddBirdDTO addBirdDTO) {
        return birdService.addBird(addBirdDTO);
    }

    @GetMapping("/birds/filter")
    public List<BirdDTO> getBirdsByNameAndColor(@RequestParam(name = "name") String name,
            @RequestParam(name = "color") String color) {
        List<BirdDTO> bird = birdService.getBirdsByNameAndColor(name, color);
        if (null == bird || bird.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return bird;
    }

}
