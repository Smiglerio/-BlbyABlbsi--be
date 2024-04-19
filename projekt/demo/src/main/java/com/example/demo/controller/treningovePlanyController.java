package com.example.demo.controller;
import com.example.demo.service.cvicenieDTO;
import com.example.demo.service.cvicenieService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.treningovyPlanService;
import com.example.demo.service.treningovyPlanDTO;

// pridane
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
@RestController
@RequestMapping("fitness")
public class treningovePlanyController {
    @Autowired
    private treningovyPlanService treningovyPlanService;

    @PostMapping("/api/treningovyPlan")
    public Long createPlan(@RequestBody treningovyPlanDTO treningovyPlanDTO){
        return treningovyPlanService.createTreningovyPlan(treningovyPlanDTO);
    }

    @GetMapping("/api/treningovyPlan/{id}")
    public treningovyPlanDTO getTreningovyPlan(@PathVariable Long id){
        return treningovyPlanService.getTreningovyPlan(id);
    }

    @GetMapping("api/treningovyPlan/list")
    public ArrayList<treningovyPlanDTO> getAllTreningovePlany(){
        return treningovyPlanService.getAllTreningovePlany();
    }

    @GetMapping("/api/treningovyPlan/cvicenia/{id}")
    public List<cvicenieDTO> getCviceniaByPlan(@PathVariable Long id) {
        return treningovyPlanService.getCviceniaByPlan(id);
    }
}
