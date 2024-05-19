package com.core.velocity.spirent.birdapi.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.core.velocity.spirent.birdapi.controller.SightingsController;
import com.core.velocity.spirent.birdapi.dto.AddSightingDTO;
import com.core.velocity.spirent.birdapi.dto.SightingDTO;
import com.core.velocity.spirent.birdapi.service.SightingService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ActiveProfiles("test")
@WebMvcTest(SightingsController.class)
public class SightingsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SightingService sightingService;

    @Autowired
    private ObjectMapper objectMapper;

    private SightingDTO sightingDTO;
    private AddSightingDTO addSightingDTO;
    AddSightingDTO modifySightingDTO;

    @BeforeEach
    void setUp() {
        sightingDTO = new SightingDTO();
        addSightingDTO = new AddSightingDTO();
        modifySightingDTO = new AddSightingDTO();
    }

    @Test
    void testGetAllSightings() throws Exception {
        Page<SightingDTO> page = new PageImpl<>(Collections.singletonList(sightingDTO));
        when(sightingService.getAllSightings(any(PageRequest.class))).thenReturn(page);

        mockMvc.perform(get("/sightings/all")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "id"))
                .andExpect(status().isOk());
    }

    @Test
    void testAddSighting() throws Exception {
        when(sightingService.addSighting(any(AddSightingDTO.class))).thenReturn(sightingDTO);

        mockMvc.perform(post("/sightings/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addSightingDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSightingsByBirdId() throws Exception {
        when(sightingService.getSightingsByBirdId("birdId")).thenReturn(Collections.singletonList(sightingDTO));

        mockMvc.perform(get("/sightings/filter")
                .param("birdId", "birdId"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSightingsByLocationAndTimeInterval() throws Exception {
        when(sightingService.getSightingsByFilters(any(String.class), any(LocalDateTime.class),
                any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(sightingDTO));

        mockMvc.perform(get("/sightings/filter")
                .param("location", "location")
                .param("startDateTime", "2024-05-15T10:00:00")
                .param("endDateTime", "2024-05-15T12:00:00"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSightingsByLocation() throws Exception {
        when(sightingService.getSightingsByLocation("location")).thenReturn(Collections.singletonList(sightingDTO));

        mockMvc.perform(get("/sightings/filter")
                .param("location", "location"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSightingsByTimeInterval() throws Exception {
        when(sightingService.getSightingsByTimeInterval(any(LocalDateTime.class), any(LocalDateTime.class)))
                .thenReturn(Collections.singletonList(sightingDTO));

        mockMvc.perform(get("/sightings/filter")
                .param("startDateTime", "2024-05-15T10:00:00")
                .param("endDateTime", "2024-05-15T12:00:00"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetSightingsByInvalidFilters() throws Exception {
        mockMvc.perform(get("/sightings/filter"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testModifySighting() throws Exception {
        when(sightingService.modifySighting(any(String.class), any(AddSightingDTO.class))).thenReturn(sightingDTO);

        mockMvc.perform(put("/sightings/modify/{id}", "sightingId")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modifySightingDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testDeleteSighting() throws Exception {
        doNothing().when(sightingService).deleteSighting("sightingId");

        mockMvc.perform(delete("/sightings/delete/{id}", "sightingId"))
                .andExpect(status().isNoContent());
    }

}
