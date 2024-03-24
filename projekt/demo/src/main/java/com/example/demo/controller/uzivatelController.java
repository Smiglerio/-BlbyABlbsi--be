package com.example.demo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.uzivatelService;
import com.example.demo.service.uzivatelDTO;

// pridane
import java.util.ArrayList;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;



@RestController
@RequestMapping("fitness")
public class uzivatelController {
    @Autowired
    private uzivatelService uzivatelService;

    @GetMapping("/api/uzivatel/{id}")
    public uzivatelDTO getUzivatel(@PathVariable Long id) {
        uzivatelDTO test = new uzivatelDTO();
        test.setMeno("test1M");
        test.setPriezvisko("test1Pr");
        test.setUsername("testuser");
        test.setHeslo("testheslo");
        test.setVaha("75");
        test.setVek(25);
        test.setVyska("180");
        test.setPohlavie("muz");
        Long testUserId = uzivatelService.createUzivatel(test);
        return uzivatelService.getUzivatel(testUserId);
    }

    @GetMapping("/api/uzivatel/{id}")
    public ResponseEntity<uzivatelDTO> getUzivatelById(@PathVariable Long id) {
        uzivatelDTO uzivatel = uzivatelService.getUzivatel(id);
        if (uzivatel != null) {
            return new ResponseEntity<>(uzivatel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
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


}
