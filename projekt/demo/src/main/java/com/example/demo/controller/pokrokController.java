package com.example.demo.controller;

import com.example.demo.service.pokrokDTO;
import com.example.demo.service.pokrokService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/fitness/api/pokrok")
public class pokrokController {

    @Autowired
    private pokrokService pokrokService;

    // CREATE
    @PostMapping("")
    public ResponseEntity<Long> createPokrok(@RequestBody pokrokDTO pokrok) {
        Long createdId = pokrokService.createPokrok(pokrok);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdId);
    }

    // GET ALL
    @GetMapping("/all")
    public ResponseEntity<List<pokrokDTO>> getAllPokroky() {
        List<pokrokDTO> pokroky = pokrokService.getAllPokroky();
        return ResponseEntity.ok(pokroky);
    }

    // GET pokroku
    @GetMapping("/{id}")
    public ResponseEntity<?> getPokrokById(@PathVariable Long id) {
        pokrokDTO pokrok = pokrokService.getPokrok(id);
        if (pokrok != null) {
            return ResponseEntity.ok(pokrok); // Pokrok bol nájdený a vrátený
        } else {
            return ResponseEntity.notFound().build(); // Pokrok s daným ID nebol nájdený
        }
    }


    // úprava pokroku podla ID
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePokrok(@PathVariable Long id, @RequestBody pokrokDTO updatedPokrok) {
        pokrokDTO updatedDTO = pokrokService.updatePokrok(id, updatedPokrok);
        if (updatedDTO != null) {
            return ResponseEntity.ok(updatedDTO); // Pokrok bol úspešne aktualizovaný
        } else {
            return ResponseEntity.notFound().build(); // Pokrok s daným ID nebol nájdený
        }
    }

    // Odstránenie pokroku
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePokrok(@PathVariable Long id) {
        boolean deleted = pokrokService.deletePokrok(id);
        if (deleted) {
            return ResponseEntity.noContent().build(); // Pokrok bol úspešne odstránený
        } else {
            return ResponseEntity.notFound().build(); // Pokrok s daným ID nebol nájdený
        }
    }
}
