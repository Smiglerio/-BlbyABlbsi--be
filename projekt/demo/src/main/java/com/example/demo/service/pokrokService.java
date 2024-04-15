package com.example.demo.service;

import com.example.demo.persistence.pokrokEntity;
import com.example.demo.persistence.pokrokRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class pokrokService {

    @Autowired
    private pokrokRepository pokrokRepository;

    // vytvorenie nového pokroku
    public Long createPokrok(pokrokDTO pokrokDto) {
        pokrokEntity entity = mapDtoToEntity(pokrokDto);
        pokrokEntity savedEntity = pokrokRepository.save(entity);
        return savedEntity.getCvicenieid();
    }

    // get by ID
    public pokrokDTO getPokrok(Long id) {
        Optional<pokrokEntity> optionalPokrok = pokrokRepository.findById(id);
        if (optionalPokrok.isPresent()) {
            return mapEntityToDto(optionalPokrok.get());
        } else {
            throw new EntityNotFoundException("Pokrok s ID " + id + " nebol nájdený.");
        }
    }

    // get all
    public List<pokrokDTO> getAllPokroky() {
        List<pokrokDTO> pokrokyDto = new ArrayList<>();
        Iterable<pokrokEntity> pokroky = pokrokRepository.findAll();
        for (pokrokEntity entity : pokroky) {
            pokrokyDto.add(mapEntityToDto(entity));
        }
        return pokrokyDto;
    }

    // UPDATE
    public pokrokDTO updatePokrok(Long id, pokrokDTO pokrokDto) {
        Optional<pokrokEntity> optionalPokrok = pokrokRepository.findById(id);
        if (optionalPokrok.isPresent()) {
            pokrokEntity entity = optionalPokrok.get();
            entity.setDatum(pokrokDto.getDatum());
            entity.setUserid(pokrokDto.getUserid());
            pokrokEntity updatedEntity = pokrokRepository.save(entity);
            return mapEntityToDto(updatedEntity);
        } else {
            throw new EntityNotFoundException("Pokrok s ID " + id + " nebol nájdený.");
        }
    }

    // DELETE
    public boolean deletePokrok(Long id) {
        if (pokrokRepository.existsById(id)) {
            pokrokRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Pokrok s ID " + id + " nebol nájdený.");
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------
    // pomocná metóda na mapovanie z DTO na entitu
    private pokrokEntity mapDtoToEntity(pokrokDTO pokrokDto) {
        pokrokEntity entity = new pokrokEntity();
        entity.setCvicenieid(pokrokDto.getCvicenieid());
        entity.setDatum(pokrokDto.getDatum());
        entity.setUserid(pokrokDto.getUserid());
        return entity;
    }

    // pomocná metóda na mapovanie z entity na DTO
    private pokrokDTO mapEntityToDto(pokrokEntity entity) {
        pokrokDTO dto = new pokrokDTO();
        dto.setCvicenieid(entity.getCvicenieid());
        dto.setDatum(entity.getDatum().toString()); // Prevedie dátum na reťazec
        dto.setUserid(entity.getUserid());
        return dto;
    }



}
