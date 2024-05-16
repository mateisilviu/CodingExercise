package com.core.velocity.spirent.birdapi.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.core.velocity.spirent.birdapi.dto.BirdDTO;
import com.core.velocity.spirent.birdapi.model.Bird;
import com.core.velocity.spirent.birdapi.repository.BirdRepository;
import com.core.velocity.spirent.birdapi.service.impl.BirdServiceImpl;

@ActiveProfiles("test")
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BirdRepositoryTest {

    @Mock
    private BirdRepository birdRepository;

    @InjectMocks
    private BirdServiceImpl birdService;

    @BeforeEach
    void setUp() {
        Bird bird1 = Bird.builder()
                .id("1")
                .name("Sparrow")
                .color("Brown")
                .weight(0.05)
                .height(0.1)
                .build();

        Bird bird2 = Bird.builder()
                .id("2")
                .name("Blue Jay")
                .color("Blue")
                .weight(0.07)
                .height(0.12)
                .build();

        Mockito.when(birdRepository.findByNameAndColor("Sparrow", "Brown")).thenReturn(Arrays.asList(bird1));
        Mockito.when(birdRepository.findByNameAndColor("Blue Jay", "Blue")).thenReturn(Arrays.asList(bird2));
    }

    @Test
    void testFindByNameAndColor() {
        List<BirdDTO> sparrows = birdService.getBirdsByNameAndColor("Sparrow", "Brown");
        assertNotNull(sparrows);
        assertEquals(1, sparrows.size());
        assertEquals("Sparrow", sparrows.get(0).getName());
        assertEquals("Brown", sparrows.get(0).getColor());

        List<BirdDTO> blueJays = birdService.getBirdsByNameAndColor("Blue Jay", "Blue");
        assertNotNull(blueJays);
        assertEquals(1, blueJays.size());
        assertEquals("Blue Jay", blueJays.get(0).getName());
        assertEquals("Blue", blueJays.get(0).getColor());
    }
}
