package com.example.demo.controller;

import com.example.demo.service.cvicenieService;
import com.example.demo.service.cvicenieDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class cvicenieControllerTests {

    @Mock
    private cvicenieService cvicenieService;

    @InjectMocks
    private cvicenieController cvicenieController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateCvicenie() {
        // Arrange
        cvicenieDTO mockCvicenie = new cvicenieDTO();
        mockCvicenie.setCvicenieid(1L);
        when(cvicenieService.createCvicenie(any())).thenReturn(1L);

        // Act
        Long createdId = cvicenieController.createCvicenie(mockCvicenie);

        // Assert
        assertEquals(1L, createdId);
    }

    @Test
    public void testGetCvicenie() {
        // Arrange
        Long cvicenieId = 1L;
        cvicenieDTO mockCvicenie = new cvicenieDTO();
        mockCvicenie.setCvicenieid(cvicenieId);
        when(cvicenieService.getCvicenie(cvicenieId)).thenReturn(mockCvicenie);

        // Act
        cvicenieDTO retrievedCvicenie = cvicenieController.getCvicenie(cvicenieId);

        // Assert
        assertEquals(mockCvicenie, retrievedCvicenie);
    }

    @Test
    public void testGetAllCvicenia() {
        // Arrange
        List<cvicenieDTO> mockCviceniaList = new ArrayList<>();
        when(cvicenieService.getAllCvicenie()).thenReturn((ArrayList<cvicenieDTO>) mockCviceniaList);

        // Act
        ArrayList<cvicenieDTO> retrievedCviceniaList = cvicenieController.getAllCvicenia();

        // Assert
        assertEquals(mockCviceniaList, retrievedCviceniaList);
    }
}
