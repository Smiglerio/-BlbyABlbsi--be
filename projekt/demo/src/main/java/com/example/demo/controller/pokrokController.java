package com.example.demo.controller;

import com.example.demo.service.pokrokDTO;
import com.example.demo.service.pokrokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pokrok")
public class pokrokController {

    @Autowired
    private pokrokService pokrokService;

    // CREATE
    @PostMapping("/create")
    public Long createPokrok(@RequestBody pokrokDTO pokrokDto) {
        return pokrokService.createPokrok(pokrokDto);
    }
    // gGET BY ID
    @GetMapping("/{id}")
    public pokrokDTO getPokrok(@PathVariable Long id) {
        return pokrokService.getPokrok(id);
    }
    // GET ALL
    @GetMapping("/all")
    public List<pokrokDTO> getAllPokroky() {
        return pokrokService.getAllPokroky();
    }
    // UPDATE
    @PutMapping("/{id}")
    public pokrokDTO updatePokrok(@PathVariable Long id, @RequestBody pokrokDTO pokrokDto) {
        return pokrokService.updatePokrok(id, pokrokDto);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public boolean deletePokrok(@PathVariable Long id) {
        return pokrokService.deletePokrok(id);
    }
}
