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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.core.velocity.spirent.birdapi.service.BirdService;
import com.core.velocity.spirent.birdapi.dto.AddBirdDTO;
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

    @PostMapping("/")
    public BirdDTO add(@RequestBody AddBirdDTO addBirdDTO) {
        return birdService.addBird(addBirdDTO);
    }

    @GetMapping("/{name}")
    public List<BirdDTO> getByName(@PathVariable String name) {
        List<BirdDTO> bird = birdService.getBirdsByName(name);
        if (null == bird || bird.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No birds found with name " + name);
        }
        return bird;
    }

    @GetMapping("/{color}")
    public List<BirdDTO> getByColor(@PathVariable String color) {
        List<BirdDTO> bird = birdService.getBirdsByColor(color);
        if (null == bird || bird.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No birds found with color " + color);
        }
        return bird;
    }

}
