package com.example.demo.controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.uzivatelService;
import com.example.demo.service.uzivatelDTO;
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

    @PostMapping("/api/uzivatel")
    public Long createUzivatel(@RequestBody uzivatelDTO uzivatel) {
        return uzivatelService.createUzivatel(uzivatel);
    }
}
