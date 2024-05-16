package com.core.velocity.spirent.birdapi.test.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.core.velocity.spirent.birdapi.model.Bird;
import com.core.velocity.spirent.birdapi.model.Sighting;
import com.core.velocity.spirent.birdapi.repository.SightingRepository;

@ActiveProfiles("test")
@SpringBootTest
public class SightingRepositoryTest {

    @Test
    void testFindByBird() {
        SightingRepository sightingRepository = mock(SightingRepository.class);
        Bird bird = Bird.builder().id("1").name("Sparrow").color("Brown").weight(0.05).height(0.1).build();
        Sighting sighting1 = new Sighting("1", bird, "Location 1", LocalDateTime.now());
        Sighting sighting2 = new Sighting("2", bird, "Location 2", LocalDateTime.now());
        when(sightingRepository.findByBird(bird)).thenReturn(Arrays.asList(sighting1, sighting2));
        List<Sighting> sightings = sightingRepository.findByBird(bird);
        assertEquals(2, sightings.size());
        assertEquals("Location 1", sightings.get(0).getLocation());
        assertEquals("Location 2", sightings.get(1).getLocation());
    }

    @Test
    void testFindByLocation() {
        SightingRepository sightingRepository = mock(SightingRepository.class);
        String location = "Location";
        Sighting sighting1 = new Sighting("1", null, location, LocalDateTime.now());
        Sighting sighting2 = new Sighting("2", null, location, LocalDateTime.now());
        when(sightingRepository.findByLocation(location)).thenReturn(Arrays.asList(sighting1, sighting2));
        List<Sighting> sightings = sightingRepository.findByLocation(location);
        assertEquals(2, sightings.size());
        assertEquals(location, sightings.get(0).getLocation());
        assertEquals(location, sightings.get(1).getLocation());
    }

    @Test
    void testFindByDateTimeBetween() {
        SightingRepository sightingRepository = mock(SightingRepository.class);
        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2022, 12, 31, 23, 59);
        Sighting sighting1 = new Sighting("1", null, "Location 1", LocalDateTime.of(2022, 5, 1, 12, 0));
        Sighting sighting2 = new Sighting("2", null, "Location 2", LocalDateTime.of(2022, 8, 15, 9, 0));
        when(sightingRepository.findByDateTimeBetween(startDateTime, endDateTime))
                .thenReturn(Arrays.asList(sighting1, sighting2));
        List<Sighting> sightings = sightingRepository.findByDateTimeBetween(startDateTime, endDateTime);
        assertEquals(2, sightings.size());
        assertEquals(LocalDateTime.of(2022, 5, 1, 12, 0), sightings.get(0).getDateTime());
        assertEquals(LocalDateTime.of(2022, 8, 15, 9, 0), sightings.get(1).getDateTime());
    }

    @Test
    void testFindByLocationAndDateTimeBetween() {
        SightingRepository sightingRepository = mock(SightingRepository.class);
        String location = "Location";
        LocalDateTime startDateTime = LocalDateTime.of(2022, 1, 1, 0, 0);
        LocalDateTime endDateTime = LocalDateTime.of(2022, 12, 31, 23, 59);
        Sighting sighting1 = new Sighting("1", null, location, LocalDateTime.of(2022, 5, 1, 12, 0));
        Sighting sighting2 = new Sighting("2", null, location, LocalDateTime.of(2022, 8, 15, 9, 0));
        when(sightingRepository.findByLocationAndDateTimeBetween(location, startDateTime, endDateTime))
                .thenReturn(Arrays.asList(sighting1, sighting2));
        List<Sighting> sightings = sightingRepository.findByLocationAndDateTimeBetween(location, startDateTime,
                endDateTime);
        assertEquals(2, sightings.size());
        assertEquals(location, sightings.get(0).getLocation());
        assertEquals(location, sightings.get(1).getLocation());
        assertEquals(LocalDateTime.of(2022, 5, 1, 12, 0), sightings.get(0).getDateTime());
        assertEquals(LocalDateTime.of(2022, 8, 15, 9, 0), sightings.get(1).getDateTime());
    }
}
