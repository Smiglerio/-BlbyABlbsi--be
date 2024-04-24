package com.example.demo.controller;
import com.example.demo.security.service.AuthenticationService;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.uzivatelService;
import com.example.demo.service.uzivatelDTO;

// pridane
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("fitness")
public class uzivatelController {
    @Autowired
    private uzivatelService uzivatelService;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping("/api/uzivatel/{id}")
    public uzivatelDTO getUzivatel(@PathVariable Long id) {
        return uzivatelService.getUzivatel(id);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody uzivatelDTO userLogin, @RequestHeader(HttpHeaders.AUTHORIZATION) String auth) {
        String token = uzivatelService.authenticate(userLogin.getUsername(), userLogin.getHeslo());
        return token;
    }

    // vytvorenie uzivatela
    @PostMapping("/api/uzivatel")
    public Long createUzivatel(@RequestBody uzivatelDTO uzivatel) {
        return uzivatelService.createUzivatel(uzivatel);
    }

    // získanie all uzívateľov
    @GetMapping("/api/uzivatel/all")
    public ArrayList<uzivatelDTO> getAllUzivatelia() {
        return uzivatelService.getAllUzivatelia();
    }

    //aktualizaci existujúceho uzívateľa
    @PutMapping("/api/uzivatel/{id}")
    public uzivatelDTO updateUzivatel(@PathVariable Long id, @RequestBody uzivatelDTO uzivatel) {
        return uzivatelService.updateUzivatel(id, uzivatel);
    }

    //vymazanie existujúceho uzívateľa
    @DeleteMapping("/api/uzivatel/{id}")
    public boolean deleteUzivatel(@PathVariable Long id) {
        return uzivatelService.deleteUzivatel(id);
    }
}
