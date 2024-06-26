package com.example.demo.security.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.example.demo.security.service.AuthenticationService;
import com.example.demo.security.service.UserRolesDto;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Optional;

@RestController
public class AuthenticationController {

    private final String AUTHORIZATION_HEADER = "Authorization";

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/api/authentication")
    public void login(@RequestHeader(value = AUTHORIZATION_HEADER, required = false) Optional<String> authentication,
                      HttpServletResponse response) {
        if (authentication.isEmpty()) {
            response.setStatus(HttpStatus.FORBIDDEN.value());
            return;
        }
    }

    private static String[] credentialsDecode(String authorization) {
        String base64Credentials = authorization.substring("Basic".length()).trim();
        byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
        String credentials = new String(credDecoded, StandardCharsets.UTF_8);
        return  credentials.split(":", 2);
    }

/*    @GetMapping("/api/authentication")
    public UserRolesDto getRoles(@RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = auth.substring("Bearer".length()).trim();
        return authenticationService.authenticate(token);
    }*/

    @DeleteMapping("/api/authentication/logout")
    public void logout(@RequestHeader(value = AUTHORIZATION_HEADER, required = false) Optional<String> authentication) {
        String token = authentication.get().substring("Bearer".length()).trim();
        if (authentication.isPresent()) {
            authenticationService.tokenRemove(token);
        } else {
            System.out.println("nič sa nestalo");
        }
    }
}

