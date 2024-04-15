package com.example.demo.controller;

import com.example.demo.persistence.treningovePlanyEntity;
import com.example.demo.service.treningovePlanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class treningovePlanyControllerTests {

    @Mock
    private treningovePlanyService treningovePlanyService;

    @InjectMocks
    private treningovePlanyController treningovePlanyController;

    private MockMvc mockMvc;


    @Test
    void testCreateTreningovyPlan() throws Exception {
        when(treningovePlanyService.createTreningovyPlan(any())).thenReturn(1L);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/treningove-plany/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("1"));
    }

    @Test
    void testUpdateTreningovyPlan() throws Exception {

        treningovePlanyEntity plan = new treningovePlanyEntity();
        plan.setPlanId(1L);
        plan.setNazov("Updated Plan");

        when(treningovePlanyService.updateTreningovyPlan(any(Long.class), any(treningovePlanyEntity.class))).thenReturn(plan);


        mockMvc.perform(MockMvcRequestBuilders.put("/api/treningove-plany/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"nazov\": \"Updated Plan\" }"))

                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$.nazov").value("Updated Plan"));
    }

    @Test
    void testGetTreningovyPlan() throws Exception {
        treningovePlanyEntity plan = new treningovePlanyEntity();
        plan.setPlanId(1L);
        when(treningovePlanyService.getTreningovyPlan(1L)).thenReturn(plan);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/treningove-plany/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.planId").value(1));
    }

    @Test
    void testGetAllTreningovePlany() throws Exception {
        treningovePlanyEntity plan = new treningovePlanyEntity();
        plan.setPlanId(1L);
        when(treningovePlanyService.getAllTreningovePlany()).thenReturn(Collections.singletonList(plan));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/treningove-plany/all"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].planId").value(1));
    }






    @Test
    void testDeleteTreningovyPlan() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/treningove-plany/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(treningovePlanyController).build();
    }
}
