package com.core.velocity.spirent.birdapi.test.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import com.core.velocity.spirent.birdapi.model.Bird;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
public class BirdTest {

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
    }

    @Test
    void testGettersAndSetters() {
        assertNotNull(bird.getId());
        assertEquals("Robin", bird.getName());
        assertEquals("Red", bird.getColor());
        assertEquals(0.1, bird.getWeight());
        assertEquals(0.2, bird.getHeight());
    }

    @Test
    void testBuilder() {
        Bird bird = Bird.builder()
                .id("2")
                .name("Sparrow")
                .color("Brown")
                .weight(0.05)
                .height(0.15)
                .build();

        assertNotNull(bird);
        assertEquals("2", bird.getId());
        assertEquals("Sparrow", bird.getName());
        assertEquals("Brown", bird.getColor());
        assertEquals(0.05, bird.getWeight());
        assertEquals(0.15, bird.getHeight());
    }
}
