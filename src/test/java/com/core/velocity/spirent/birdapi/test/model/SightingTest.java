package com.core.velocity.spirent.birdapi.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.core.velocity.spirent.birdapi.model.Bird;
import com.core.velocity.spirent.birdapi.model.Sighting;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class SightingTest {

    private Sighting sighting;
    private Bird bird;

    @BeforeEach
    void setUp() {
        bird = Bird.builder()
                .id("1")
                .name("Robin")
                .color("Red")
                .weight(0.1)
                .height(0.2)
                .build();

        sighting = Sighting.builder()
                .id("1")
                .bird(bird)
                .location("Forest")
                .dateTime(LocalDateTime.now())
                .build();
    }

    @Test
    void testGettersAndSetters() {
        assertNotNull(sighting.getId());
        assertNotNull(sighting.getBird());
        assertEquals("Forest", sighting.getLocation());
        assertNotNull(sighting.getDateTime());
    }

    @Test
    void testBuilder() {
        Sighting sighting = Sighting.builder()
                .id("2")
                .bird(bird)
                .location("Park")
                .dateTime(LocalDateTime.now())
                .build();

        assertNotNull(sighting);
        assertEquals("2", sighting.getId());
        assertNotNull(sighting.getBird());
        assertEquals("Park", sighting.getLocation());
        assertNotNull(sighting.getDateTime());
    }
}
