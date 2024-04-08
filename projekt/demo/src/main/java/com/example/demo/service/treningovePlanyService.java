package com.example.demo.service;

import com.example.demo.persistence.treningovePlanyEntity;
import com.example.demo.persistence.treningovePlanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class treningovePlanyService {

    @Autowired
    private treningovePlanyRepository treningovePlanyRepository;

    // CREATE
    public Long createTreningovyPlan(treningovePlanyEntity treningovyPlanEntity) {
        treningovePlanyEntity savedEntity = treningovePlanyRepository.save(treningovyPlanEntity);
        return savedEntity.getPlanId();
    }

    // GET by ID
    public treningovePlanyEntity getTreningovyPlan(Long id) {
        Optional<treningovePlanyEntity> optionalTreningovyPlan = treningovePlanyRepository.findById(id);
        return optionalTreningovyPlan.orElseThrow(() -> new EntityNotFoundException("Tréningový plán s ID " + id + " nebol nájdený."));
    }

    // GET ALL
    public List<treningovePlanyEntity> getAllTreningovePlany() {
        List<treningovePlanyEntity> treningovePlany = new ArrayList<>();
        treningovePlanyRepository.findAll().forEach(treningovePlany::add);
        return treningovePlany;
    }

    // UPDATE
    public treningovePlanyEntity updateTreningovyPlan(Long id, treningovePlanyEntity treningovyPlanEntity) {
        if (treningovePlanyRepository.existsById(id)) {
            treningovyPlanEntity.setPlanId(id);
            return treningovePlanyRepository.save(treningovyPlanEntity);
        } else {
            throw new EntityNotFoundException("Tréningový plán s ID " + id + " nebol nájdený.");
        }
    }

    // DELETE
    public void deleteTreningovyPlan(Long id) {
        if (treningovePlanyRepository.existsById(id)) {
            treningovePlanyRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Tréningový plán s ID " + id + " nebol nájdený.");
        }
    }
}
