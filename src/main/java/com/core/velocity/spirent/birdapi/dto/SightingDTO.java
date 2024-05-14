package com.core.velocity.spirent.birdapi.dto;

import java.time.LocalDateTime;

import org.springframework.beans.BeanUtils;

import com.core.velocity.spirent.birdapi.model.Sighting;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SightingDTO {

    public SightingDTO(Sighting sighting) {
        BeanUtils.copyProperties(sighting, this);
        this.bird = new BirdDTO(sighting.getBird());
    }

    private String id;
    private BirdDTO bird;
    private String location;
    private LocalDateTime dateTime;

}
