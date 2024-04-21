package com.example.demo.service;

import com.example.demo.persistence.cvicenieEntity;
import com.example.demo.persistence.pokrokEntity;
import com.example.demo.persistence.uzivatelEntity;
import com.example.demo.persistence.pokrokRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class pokrokService {

    @Autowired
    private pokrokRepository pokrokRepository;

    public pokrokDTO getPokrok(Long id) {
        Optional<pokrokEntity> opt = pokrokRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        pokrokEntity entity = opt.get();
        pokrokDTO dto = new pokrokDTO();
        dto.setPokrokid(entity.getPokrokid());
        dto.setDatum(entity.getDatum());
        dto.setUzivatelEntity(entity.getUzivatelEntity());
        dto.setCvicenieEntity(entity.getCvicenieEntity());
        return dto;
    }

    public Long createPokrok(pokrokDTO dto) {
        pokrokEntity entity = new pokrokEntity();
        entity.setDatum(dto.getDatum());
        entity.setUzivatelEntity(dto.getUzivatelEntity());
        entity.setCvicenieEntity(dto.getCvicenieEntity());
        pokrokRepository.save(entity);
        return entity.getPokrokid();
    }

    public ArrayList<pokrokDTO> getAllPokroky() {
        Iterable<pokrokEntity> pokrokyIterable = pokrokRepository.findAll();
        ArrayList<pokrokDTO> pokrokyList = new ArrayList<>();
        for (pokrokEntity entity : pokrokyIterable) {
            pokrokDTO dto = new pokrokDTO();
            dto.setPokrokid(entity.getPokrokid());
            dto.setDatum(entity.getDatum());
            dto.setUzivatelEntity(entity.getUzivatelEntity());
            dto.setCvicenieEntity(entity.getCvicenieEntity());
            pokrokyList.add(dto);
        }
        return pokrokyList;
    }

    public boolean deletePokrok(Long id) {
        Optional<pokrokEntity> opt = pokrokRepository.findById(id);
        if (opt.isPresent()) {
            pokrokRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public pokrokDTO updatePokrok(Long id, pokrokDTO updatedPokrok) {
        // Skontrolujem či existuje ID
        Optional<pokrokEntity> opt = pokrokRepository.findById(id);
        if (opt.isPresent()) {
            // Záznam s daným ID existuje
            pokrokEntity existingPokrok = opt.get();

            // Aktualizujeme hodnoty záznamu
            existingPokrok.setDatum(updatedPokrok.getDatum());
            // aktualizacia dalsich atributov ?

            // dáme naspäť do DB
            pokrokEntity updatedEntity = pokrokRepository.save(existingPokrok);

            // Vytvoríme DTO objekt z aktualizovaného záznamu a vrátime ho
            pokrokDTO updatedDTO = new pokrokDTO();
            updatedDTO.setPokrokid(updatedEntity.getPokrokid());
            updatedDTO.setDatum(updatedEntity.getDatum());
            // aktualizacia dalsich atributov ?

            return updatedDTO;
        } else {
            // Záznam s daným ID neexistuje, vrátime null
            return null;
        }
    }


}
