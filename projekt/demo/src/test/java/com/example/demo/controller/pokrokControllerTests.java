package com.example.demo.controller;

import com.example.demo.service.pokrokService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class pokrokControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private pokrokService pokrokService;

    @Test
    public void testCreatePokrok() throws Exception {
        String requestBody = "{ \"cvicenieid\": 1, \"datum\": \"2024-04-12\", \"userid\": 1 }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/pokrok/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

  /*  @Test
    public void testGetPokrok() throws Exception {
        pokrokDTO pokrok = new pokrokDTO();
        pokrok.setCvicenieid(1L);
        pokrok.setDatum("2024-04-12");
        pokrok.setUserid(1L);

        Long pokrokId = pokrokService.createPokrok(pokrok);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/pokrok/" + pokrokId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cvicenieid").value(1))
                .andExpect(jsonPath("$.datum").value("2024-04-12")); // Kontrola iba hodnoty, nie formátu
    }
*/
}
