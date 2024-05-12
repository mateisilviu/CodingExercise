package com.core.velocity.spirent.birdapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddBirdDTO {

    private String name;
    private String color;
    private double weight;
    private double height;

}
