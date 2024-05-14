package com.core.velocity.spirent.birdapi.integration;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.core.velocity.spirent.birdapi.dto.BirdDTO;
import com.core.velocity.spirent.birdapi.service.BirdService;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class BirdControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BirdService birdService;

    @Before(value = "")
    public void setUp() throws Exception {
    }

    @Test
    public void testGetAllBirds() throws Exception {
        // Mock data for the Page<BirdDTO> returned by birdService
        List<BirdDTO> birdDTOList = List.of(
                new BirdDTO("id1", "Sparrow", "Brown", 1.1, 2.2),
                new BirdDTO("id2", "Eagle", "Black", 3.3, 4.4)
        // Add more mock data if needed
        );
        Page<BirdDTO> birdPage = new PageImpl<>(birdDTOList);

        // Mock behavior of birdService.getAllBirds()
        when(birdService.getAllBirds(any(PageRequest.class))).thenReturn(birdPage);

        // Perform GET request to /birds/all with query parameters
        mockMvc.perform(MockMvcRequestBuilders.get("/birds/all")
                .param("page", "0")
                .param("size", "10")
                .param("sort", "id"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2)) // Verify the number of elements in the returned
                                                                    // page
                .andExpect(jsonPath("$.content[0].id").value("id1")) // Verify the id of the first element
                .andExpect(jsonPath("$.content[0].name").value("Sparrow")) // Verify the name of the first element
                .andExpect(jsonPath("$.content[0].color").value("Brown")); // Verify the color of the first element
        // Add more assertions as needed
    }

    // @Test
    // public void testGetBirdById() throws Exception {
    // AddBirdDTO testBird = new AddBirdDTO();
    // testBird.setColor("red");
    // testBird.setHeight(1.1);
    // testBird.setWeight(2.2);
    // testBird.setName("TestBird1");

    // BirdDTO savedTestBird = service.addBird(testBird);

    // mockMvc.perform(MockMvcRequestBuilders.get("/birds/{id}", 1) // Perform a GET
    // request to /birds/{id}
    // .contentType(MediaType.APPLICATION_JSON)) // Set content type to JSON
    // .andExpect(MockMvcResultMatchers.status().isOk()) // Verify that response
    // status is OK (200)
    // .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1)); // Verify JSON
    // response contains expected
    // // id
    // }
}
