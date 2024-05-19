package com.core.velocity.spirent.birdapi.test.controller;

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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.core.velocity.spirent.birdapi.config.exceptions.RestExceptionHandler;
import com.core.velocity.spirent.birdapi.controller.BirdController;
import com.core.velocity.spirent.birdapi.dto.AddBirdDTO;
import com.core.velocity.spirent.birdapi.dto.BirdDTO;
import com.core.velocity.spirent.birdapi.service.BirdService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(BirdController.class)
public class BirdControllerTest {

        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private BirdService birdService;

        private ObjectMapper objectMapper;

        @BeforeEach
        public void setUp() {
                mockMvc = MockMvcBuilders.standaloneSetup(new BirdController(birdService))
                                .setControllerAdvice(new RestExceptionHandler())
                                .build();
                objectMapper = new ObjectMapper();
        }

        @Test
        public void testGetAll() throws Exception {
                BirdDTO bird = new BirdDTO("1", "Parrot", "Green", 1.5, 10.0);
                Page<BirdDTO> page = new PageImpl<>(Collections.singletonList(bird));

                when(birdService.getAllBirds(any(PageRequest.class))).thenReturn(page);

                mockMvc.perform(get("/birds/all")
                                .param("page", "0")
                                .param("size", "10")
                                .param("sort", "id"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.content", hasSize(1)))
                                .andExpect(jsonPath("$.content[0].id").value("1"))
                                .andExpect(jsonPath("$.content[0].name").value("Parrot"))
                                .andExpect(jsonPath("$.content[0].color").value("Green"))
                                .andExpect(jsonPath("$.content[0].weight").value(1.5))
                                .andExpect(jsonPath("$.content[0].height").value(10.0));
        }

        @Test
        public void testAddBird() throws Exception {
                AddBirdDTO addBirdDTO = new AddBirdDTO("Parrot", "Green", 1.5, 10.0);
                BirdDTO bird = new BirdDTO("1", "Parrot", "Green", 1.5, 10.0);

                when(birdService.addBird(any(AddBirdDTO.class))).thenReturn(bird);

                mockMvc.perform(post("/birds/add")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(addBirdDTO)))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$.id").value("1"))
                                .andExpect(jsonPath("$.name").value("Parrot"))
                                .andExpect(jsonPath("$.color").value("Green"))
                                .andExpect(jsonPath("$.weight").value(1.5))
                                .andExpect(jsonPath("$.height").value(10.0));
        }

        @Test
        public void testGetBirdsByNameAndColor() throws Exception {
                BirdDTO bird = new BirdDTO("1", "Parrot", "Green", 1.5, 10.0);
                when(birdService.getBirdsByNameAndColor(anyString(), anyString()))
                                .thenReturn(Collections.singletonList(bird));

                mockMvc.perform(get("/birds/filter")
                                .param("name", "Parrot")
                                .param("color", "Green"))
                                .andExpect(status().isOk())
                                .andExpect(jsonPath("$", hasSize(1)))
                                .andExpect(jsonPath("$[0].id").value("1"))
                                .andExpect(jsonPath("$[0].name").value("Parrot"))
                                .andExpect(jsonPath("$[0].color").value("Green"))
                                .andExpect(jsonPath("$[0].weight").value(1.5))
                                .andExpect(jsonPath("$[0].height").value(10.0));
        }

        @Test
        public void testGetBirdsByNameAndColor_NotFound() throws Exception {
                when(birdService.getBirdsByNameAndColor(anyString(), anyString()))
                                .thenReturn(Collections.emptyList());

                mockMvc.perform(get("/birds/filter")
                                .param("name", "Unknown")
                                .param("color", "Unknown"))
                                .andExpect(status().isNotFound());
        }

         @Test
    public void testModifyBird() throws Exception {
        String birdId = "1";
        AddBirdDTO addBirdDTO = new AddBirdDTO("Parrot", "Green", 1.5, 10.0);
        BirdDTO expectedBirdDTO = new BirdDTO(birdId, "Parrot", "Green", 1.5, 10.0); // populate with expected data

        when(birdService.modifyBird(eq(birdId), any(AddBirdDTO.class))).thenReturn(expectedBirdDTO);

        mockMvc.perform(put("/birds/modify/{id}", birdId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(addBirdDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(expectedBirdDTO)));

        verify(birdService).modifyBird(eq(birdId), any(AddBirdDTO.class));
    }

    @Test
    public void testDeleteBird() throws Exception {
        String birdId = "1";
        doNothing().when(birdService).deleteBird(birdId);

        mockMvc.perform(delete("/birds/delete/{id}", birdId))
                .andExpect(status().isOk());

        verify(birdService).deleteBird(birdId);
    }
}
