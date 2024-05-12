package com.core.velocity.spirent.birdapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.velocity.spirent.birdapi.service.BirdService;
import com.core.velocity.spirent.birdapi.dto.BirdDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bird")
@RequiredArgsConstructor
public class BirdController {

    @Autowired
    final BirdService birdService;

    @GetMapping("/")
    public Page<BirdDTO> getAll(@RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "id") String sort) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by(sort));
        return birdService.getAllBirds(pageable);
    }

}
