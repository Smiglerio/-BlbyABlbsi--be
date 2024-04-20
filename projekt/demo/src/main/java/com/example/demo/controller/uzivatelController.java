package com.example.demo.controller;
import com.example.demo.security.persistence.UserEntity;
import com.example.demo.security.persistence.UserRepository;
import com.example.demo.security.service.AuthenticationService;
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
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/uzivatel/{id}")
    public uzivatelDTO getUzivatel(@PathVariable Long id) {
        return uzivatelService.getUzivatel(id);
    }

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody uzivatelDTO userLogin) {
        boolean isAuthenticated = uzivatelService.authenticate(userLogin.getUsername(), userLogin.getHeslo());
        if (isAuthenticated) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
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

    /*
    public String PostLogin(uzivatelDTO uzivatel) {
        String token = authenticationService.authenticate(uzivatel.getUsername(), uzivatel.getHeslo());

        if (token != null){
            Optional<UserEntity> userOptional = userRepository.findByUsername(uzivatel.getUsername());
            if (userOptional.isPresent()) {
                return token;
            }
        }

    }
    */

}
