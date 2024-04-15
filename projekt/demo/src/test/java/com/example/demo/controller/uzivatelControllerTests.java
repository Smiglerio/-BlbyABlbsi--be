package com.example.demo.controller;

import com.example.demo.service.uzivatelService;
import com.example.demo.service.uzivatelDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class uzivatelControllerTests {

    @Mock
    private uzivatelService uzivatelService;

    @InjectMocks
    private uzivatelController controller;

    @Test
    public void testGetUzivatel() {

        Long testUserId = 1L;
        uzivatelDTO testUzivatelDTO = new uzivatelDTO();
        testUzivatelDTO.setMeno("test1M");
        testUzivatelDTO.setPriezvisko("test1Pr");
        testUzivatelDTO.setUsername("testuser");
        testUzivatelDTO.setHeslo("testheslo");
        testUzivatelDTO.setVaha(75);
        testUzivatelDTO.setVek(25);
        testUzivatelDTO.setVyska(180);
        testUzivatelDTO.setPohlavie("muz");

        when(uzivatelService.createUzivatel(any(uzivatelDTO.class))).thenReturn(testUserId);
        when(uzivatelService.getUzivatel(testUserId)).thenReturn(testUzivatelDTO);
        uzivatelDTO result = controller.getUzivatel(testUserId);


        assertEquals(testUzivatelDTO, result);
        verify(uzivatelService, times(1)).createUzivatel(any(uzivatelDTO.class));
        verify(uzivatelService, times(1)).getUzivatel(testUserId);
    }

    @Test
    public void testCreateUzivatel() {
        // Arrange
        uzivatelDTO testUzivatelDTO = new uzivatelDTO();
        testUzivatelDTO.setMeno("test1M");
        testUzivatelDTO.setPriezvisko("test1Pr");
        testUzivatelDTO.setUsername("testuser");
        testUzivatelDTO.setHeslo("testheslo");
        testUzivatelDTO.setVaha(75);
        testUzivatelDTO.setVek(25);
        testUzivatelDTO.setVyska(180);
        testUzivatelDTO.setPohlavie("muz");

        Long testUserId = 1L;
        when(uzivatelService.createUzivatel(any(uzivatelDTO.class))).thenReturn(testUserId);


        Long result = controller.createUzivatel(testUzivatelDTO);


        assertEquals(testUserId, result);
        verify(uzivatelService, times(1)).createUzivatel(any(uzivatelDTO.class));
    }

    @Test
    public void testGetAllUzivatelia() {

        ArrayList<uzivatelDTO> testUzivateliaList = new ArrayList<>();


        when(uzivatelService.getAllUzivatelia()).thenReturn(testUzivateliaList);


        ArrayList<uzivatelDTO> result = controller.getAllUzivatelia();


        assertEquals(testUzivateliaList, result);
        verify(uzivatelService, times(1)).getAllUzivatelia();
    }

    @Test
    public void testUpdateUzivatel() {

        Long testUserId = 1L;
        uzivatelDTO updatedUzivatelDTO = new uzivatelDTO();
        when(uzivatelService.updateUzivatel(eq(testUserId), any(uzivatelDTO.class))).thenReturn(updatedUzivatelDTO);
        uzivatelDTO result = controller.updateUzivatel(testUserId, updatedUzivatelDTO);


        assertEquals(updatedUzivatelDTO, result);
        verify(uzivatelService, times(1)).updateUzivatel(eq(testUserId), any(uzivatelDTO.class));
    }

    @Test
    public void testDeleteUzivatel() {
        // Arrange
        Long testUserId = 1L;
        when(uzivatelService.deleteUzivatel(testUserId)).thenReturn(true);

        // Act
        boolean result = controller.deleteUzivatel(testUserId);

        // Assert
        assertTrue(result);
        verify(uzivatelService, times(1)).deleteUzivatel(testUserId);
    }
}
