package com.core.velocity.spirent.birdapi.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddSightingDTO {

    private String birdId;
    private String location;
    private LocalDateTime dateTime;

}
