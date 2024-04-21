package com.example.demo.controller;

import com.example.demo.service.pokrokService;
import com.example.demo.service.pokrokDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class pokrokControllerTests {

    @Mock
    private pokrokService pokrokService;

    @InjectMocks
    private pokrokController pokrokController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreatePokrok() {
        // Arrange
        pokrokDTO mockPokrok = new pokrokDTO();
        mockPokrok.setPokrokid(1L);
        when(pokrokService.createPokrok(any())).thenReturn(1L);

        // Act
        ResponseEntity<Long> response = pokrokController.createPokrok(mockPokrok);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(1L, response.getBody());
    }

    @Test
    public void testGetAllPokroky() {
        // Arrange
        List<pokrokDTO> mockPokroky = new ArrayList<>();
        when(pokrokService.getAllPokroky()).thenReturn((ArrayList<pokrokDTO>) mockPokroky);

        // Act
        ResponseEntity<List<pokrokDTO>> response = pokrokController.getAllPokroky();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPokroky, response.getBody());
    }

    @Test
    public void testGetPokrokByIdFound() {
        // Arrange
        Long pokrokId = 1L;
        pokrokDTO mockPokrok = new pokrokDTO();
        mockPokrok.setPokrokid(pokrokId);
        when(pokrokService.getPokrok(pokrokId)).thenReturn(mockPokrok);

        // Act
        ResponseEntity<?> response = pokrokController.getPokrokById(pokrokId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockPokrok, response.getBody());
    }

    @Test
    public void testGetPokrokByIdNotFound() {
        // Arrange
        Long pokrokId = 1L;
        when(pokrokService.getPokrok(pokrokId)).thenReturn(null);

        // Act
        ResponseEntity<?> response = pokrokController.getPokrokById(pokrokId);

        // Assert
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testUpdatePokrok() {
        // Arrange
        Long pokrokId = 1L;
        pokrokDTO mockUpdatedPokrok = new pokrokDTO();
        mockUpdatedPokrok.setPokrokid(pokrokId);
        when(pokrokService.updatePokrok(eq(pokrokId), any())).thenReturn(mockUpdatedPokrok);

        // Act
        ResponseEntity<?> response = pokrokController.updatePokrok(pokrokId, new pokrokDTO());

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUpdatedPokrok, response.getBody());
    }

    @Test
    public void testDeletePokrok() {
        // Arrange
        Long pokrokId = 1L;
        when(pokrokService.deletePokrok(pokrokId)).thenReturn(true);

        // Act
        ResponseEntity<?> response = pokrokController.deletePokrok(pokrokId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
