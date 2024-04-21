package com.example.demo.controller;

import com.example.demo.service.typCviceniaService;
import com.example.demo.service.typCviceniaDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class typCviceniaControllerTests {

    @Mock
    private typCviceniaService typCviceniaService;

    @InjectMocks
    private typCviceniaController typCviceniaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateTypCvicenia() {
        // Arrange
        typCviceniaDTO mockTypCvicenia = new typCviceniaDTO();
        mockTypCvicenia.setIdTypCvicenia(1L);
        when(typCviceniaService.createTypCvicenia(any())).thenReturn(1L);

        // Act
        Long createdId = typCviceniaController.createTypCvicenia(mockTypCvicenia);

        // Assert
        assertEquals(1L, createdId);
    }

    @Test
    public void testGetTypCvicenia() {
        // Arrange
        Long typCviceniaId = 1L;
        typCviceniaDTO mockTypCvicenia = new typCviceniaDTO();
        mockTypCvicenia.setIdTypCvicenia(typCviceniaId);
        when(typCviceniaService.getTypCvicenia(typCviceniaId)).thenReturn(mockTypCvicenia);

        // Act
        typCviceniaDTO retrievedTypCvicenia = typCviceniaController.getTypCvicenia(typCviceniaId);

        // Assert
        assertEquals(mockTypCvicenia, retrievedTypCvicenia);
    }

    @Test
    public void testGetAllTypCvicenia() {
        // Arrange
        List<typCviceniaDTO> mockTypCviceniaList = new ArrayList<>();
        when(typCviceniaService.getAllTypCvicenia()).thenReturn((ArrayList<typCviceniaDTO>) mockTypCviceniaList);

        // Act
        ArrayList<typCviceniaDTO> retrievedTypCviceniaList = typCviceniaController.getAllTypCvicenia();

        // Assert
        assertEquals(mockTypCviceniaList, retrievedTypCviceniaList);
    }
}
