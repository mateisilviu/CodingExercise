package com.core.velocity.spirent.birdapi.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.core.velocity.spirent.birdapi.dto.BirdDTO;

public interface BirdService {

    public Page<BirdDTO> getAllBirds(Pageable pageable);

    public List<BirdDTO> getBirdsByName(String name);

    public List<BirdDTO> getBirdsByColor(String color);

}
