package com.example.demo.controller;

import com.example.demo.persistence.treningovePlanyEntity;
import com.example.demo.service.treningovePlanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treningove-plany")
public class treningovePlanyController {

    @Autowired
    private treningovePlanyService treningovePlanyService;

    // CREATE
    @PostMapping("/create")
    public Long createTreningovyPlan(@RequestBody treningovePlanyEntity treningovyPlanEntity) {
        return treningovePlanyService.createTreningovyPlan(treningovyPlanEntity);
    }
    // GET BY ID
    @GetMapping("/{id}")
    public treningovePlanyEntity getTreningovyPlan(@PathVariable Long id) {
        return treningovePlanyService.getTreningovyPlan(id);
    }
    // GET ALL
    @GetMapping("/all")
    public List<treningovePlanyEntity> getAllTreningovePlany() {
        return treningovePlanyService.getAllTreningovePlany();
    }
    //UPDATE
    @PutMapping("/{id}")
    public treningovePlanyEntity updateTreningovyPlan(@PathVariable Long id, @RequestBody treningovePlanyEntity treningovyPlanEntity) {
        return treningovePlanyService.updateTreningovyPlan(id, treningovyPlanEntity);
    }
    // DELETE
    @DeleteMapping("/{id}")
    public void deleteTreningovyPlan(@PathVariable Long id) {
        treningovePlanyService.deleteTreningovyPlan(id);
    }
}

