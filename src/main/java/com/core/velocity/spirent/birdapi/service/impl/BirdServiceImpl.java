package com.core.velocity.spirent.birdapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.core.velocity.spirent.birdapi.dto.AddBirdDTO;
import com.core.velocity.spirent.birdapi.dto.BirdDTO;
import com.core.velocity.spirent.birdapi.model.Bird;
import com.core.velocity.spirent.birdapi.repository.BirdRepository;
import com.core.velocity.spirent.birdapi.service.BirdService;

@Service
public class BirdServiceImpl implements BirdService {

    @Autowired
    private BirdRepository birdRepository;

    @Override
    public Page<BirdDTO> getAllBirds(Pageable pageable) {
        Page<Bird> birds = birdRepository.findAll(pageable);
        return birds.map(BirdDTO::new);
    }

    @Override
    public List<BirdDTO> getBirdsByNameAndColor(String name, String color) {
        List<Bird> birds = birdRepository.findByNameAndColor(name, color);
        return birds.stream()
                .map(BirdDTO::new)
                .collect(Collectors.toList());

    }

    @Override
    public BirdDTO addBird(AddBirdDTO addBirdDTO) {
        Bird bird = new Bird();
        bird.setName(addBirdDTO.getName());
        bird.setColor(addBirdDTO.getColor());
        bird.setHeight(addBirdDTO.getHeight());
        bird.setWeight(addBirdDTO.getWeight());
        Bird savedBirdInDB = birdRepository.save(bird);
        return new BirdDTO(savedBirdInDB);
    }

}
