package com.example.demo.controller;

import com.example.demo.service.cvicenieDTO;
import com.example.demo.service.cvicenieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class cvicenieControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private cvicenieService cvicenieService;

    @Test
    public void testCreateCvicenie() throws Exception {
        String requestBody = "{ \"nazovCviku\": \"Kliky\", \"planid\": 1, \"popisCviku\": \"robis kliky gadžo\", \"narocnostCviku\": \"fuj\" }";
        mockMvc.perform(MockMvcRequestBuilders.post("/api/cvicenie/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetCvicenie() throws Exception {
        cvicenieDTO cvicenie = new cvicenieDTO();
        cvicenie.setNazovCviku("zhyby");
        cvicenie.setPlanid(1L);
        cvicenie.setPopisCviku("lol");
        cvicenie.setNarocnostCviku("hard");

        Long cvicenieId = cvicenieService.createCvicenie(cvicenie);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/cvicenie/" + cvicenieId)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.nazovCviku").value("zhyby"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.narocnostCviku").value("hard"));
    }

}
