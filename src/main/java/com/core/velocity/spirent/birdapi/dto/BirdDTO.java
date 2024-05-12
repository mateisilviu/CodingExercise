package com.core.velocity.spirent.birdapi.dto;

import org.springframework.beans.BeanUtils;

import com.core.velocity.spirent.birdapi.model.Bird;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BirdDTO {

    public BirdDTO(Bird bird) {
        BeanUtils.copyProperties(bird, this);
    }

    private String id;

    private String name;
    private String color;
    private double weight;
    private double height;

}
