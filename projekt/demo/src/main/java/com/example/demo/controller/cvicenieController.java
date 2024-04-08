package com.example.demo.controller;

import com.example.demo.service.cvicenieDTO;
import com.example.demo.service.cvicenieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cvicenie")
public class cvicenieController {

    @Autowired
    private cvicenieService cvicenieService;

    // CREATE
    @PostMapping("/create")
    public Long createCvicenie(@RequestBody cvicenieDTO cvicenieDto) {
        return cvicenieService.createCvicenie(cvicenieDto);
    }
    // GET BY ID
    @GetMapping("/{id}")
    public cvicenieDTO getCvicenie(@PathVariable Long id) {
        return cvicenieService.getCvicenie(id);
    }
    // GET ALL
    @GetMapping("/all")
    public List<cvicenieDTO> getAllCvicenia() {
        return cvicenieService.getAllCvicenia();
    }
    // UPDATE
    @PutMapping("/{id}")
    public cvicenieDTO updateCvicenie(@PathVariable Long id, @RequestBody cvicenieDTO cvicenieDto) {
        return cvicenieService.updateCvicenie(id, cvicenieDto);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public boolean deleteCvicenie(@PathVariable Long id) {
        return cvicenieService.deleteCvicenie(id);
    }
}
