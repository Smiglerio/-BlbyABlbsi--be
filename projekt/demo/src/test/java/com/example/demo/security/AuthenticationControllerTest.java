package com.example.demo.security;

import com.example.demo.security.controller.AuthenticationController;
import com.example.demo.security.service.AuthenticationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;

import jakarta.servlet.http.HttpServletResponse;  // Zmena importu na javax.servlet.http.HttpServletResponse
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private HttpServletResponse response;

    @BeforeEach
    void setUp() {
        response = mock(HttpServletResponse.class);
    }
/*
    @Test
    void testLoginSuccess() throws Exception {
        String username = "username";
        String password = "password";
        String token = "sample_token";

        when(authenticationService.authenticate(username, password)).thenReturn(token);

        authenticationController.login(Optional.of("Basic dXNlcm5hbWU6cGFzc3dvcmQ="), response);  // Odstránenie castovania na jakarta.servlet.http.HttpServletResponse

        verify(response).setStatus(HttpServletResponse.SC_OK);
        verify(response).addHeader("Authorization", "Bearer " + token);
    }*/



}
