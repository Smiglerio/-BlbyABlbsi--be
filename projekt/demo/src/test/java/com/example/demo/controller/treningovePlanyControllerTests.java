package com.example.demo.controller;

import com.example.demo.service.cvicenieDTO;
import com.example.demo.service.treningovyPlanService;
import com.example.demo.service.treningovyPlanDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class treningovePlanyControllerTests {

    @Mock
    private treningovyPlanService treningovyPlanService;

    @InjectMocks
    private treningovePlanyController treningovePlanyController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePlan() {
        // Arrange
        treningovyPlanDTO mockPlan = new treningovyPlanDTO();
        mockPlan.setPlanId(1L);
        when(treningovyPlanService.createTreningovyPlan(any())).thenReturn(1L);

        // Act
        Long createdId = treningovePlanyController.createPlan(mockPlan);

        // Assert
        assertEquals(1L, createdId);
    }

    @Test
    public void testGetTreningovyPlan() {
        // Arrange
        Long planId = 1L;
        treningovyPlanDTO mockPlan = new treningovyPlanDTO();
        mockPlan.setPlanId(planId);
        when(treningovyPlanService.getTreningovyPlan(planId)).thenReturn(mockPlan);

        // Act
        treningovyPlanDTO retrievedPlan = treningovePlanyController.getTreningovyPlan(planId);

        // Assert
        assertEquals(mockPlan, retrievedPlan);
    }

    @Test
    public void testGetAllTreningovePlany() {
        // Arrange
        List<treningovyPlanDTO> mockPlans = new ArrayList<>();
        when(treningovyPlanService.getAllTreningovePlany()).thenReturn((ArrayList<treningovyPlanDTO>) mockPlans);

        // Act
        ArrayList<treningovyPlanDTO> retrievedPlans = treningovePlanyController.getAllTreningovePlany();

        // Assert
        assertEquals(mockPlans, retrievedPlans);
    }

    @Test
    public void testGetCviceniaByPlan() {
        // Arrange
        Long planId = 1L;
        List<cvicenieDTO> mockCvicenia = new ArrayList<>();
        when(treningovyPlanService.getCviceniaByPlan(planId)).thenReturn((ArrayList<cvicenieDTO>) mockCvicenia);

        // Act
        List<cvicenieDTO> retrievedCvicenia = treningovePlanyController.getCviceniaByPlan(planId);

        // Assert
        assertEquals(mockCvicenia, retrievedCvicenia);
    }
}
