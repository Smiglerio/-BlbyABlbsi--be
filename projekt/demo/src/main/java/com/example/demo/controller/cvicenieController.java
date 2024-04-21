package com.example.demo.controller;
import com.example.demo.persistence.cvicenieEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.cvicenieService;
import com.example.demo.service.cvicenieDTO;
// pridane
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("fitness")
public class cvicenieController {
    @Autowired
    private cvicenieService cvicenieService;
    @PostMapping("/api/cvicenie")
    public Long createCvicenie(@RequestBody cvicenieDTO cvicenie){
        return cvicenieService.createCvicenie(cvicenie);
    }

    @GetMapping("/api/cvicenie/{id}")
    public cvicenieDTO getCvicenie(@PathVariable Long id){ return cvicenieService.getCvicenie(id); }

    @GetMapping("api/cvicenie/list")
    public ArrayList<cvicenieDTO> getAllCvicenia(){ return cvicenieService.getAllCvicenie(); }
}