package com.core.velocity.spirent.birdapi.dto;

import java.util.Date;

import org.springframework.beans.BeanUtils;

import com.core.velocity.spirent.birdapi.model.Bird;
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
    }

    private String id;
    private Bird bird;
    private String location;
    private Date dateTime;

}
