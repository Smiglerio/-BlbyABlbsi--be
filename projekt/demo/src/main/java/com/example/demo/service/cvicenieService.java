package com.example.demo.service;

import com.example.demo.persistence.cvicenieEntity;
import com.example.demo.persistence.cvicenieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class cvicenieService {

    @Autowired
    private cvicenieRepository cvicenieRepository;

    // vytvorenie new cvičenia
    public Long createCvicenie(cvicenieDTO cvicenieDto) {
        cvicenieEntity entity = mapDtoToEntity(cvicenieDto);
        cvicenieEntity savedEntity = cvicenieRepository.save(entity);
        return savedEntity.getCvidenieid();
    }

    // GET ID
    public cvicenieDTO getCvicenie(Long id) {
        Optional<cvicenieEntity> optionalCvicenie = cvicenieRepository.findById(id);
        if (optionalCvicenie.isPresent()) {
            return mapEntityToDto(optionalCvicenie.get());
        } else {
            throw new EntityNotFoundException("Cvičenie s ID " + id + " nebolo nájdené.");
        }
    }

    // get all cvičenia
    public List<cvicenieDTO> getAllCvicenia() {
        List<cvicenieDTO> cviceniaDto = new ArrayList<>();
        Iterable<cvicenieEntity> cvicenia = cvicenieRepository.findAll();
        for (cvicenieEntity entity : cvicenia) {
            cviceniaDto.add(mapEntityToDto(entity));
        }
        return cviceniaDto;
    }

    // aktualizáciaa  cvičenia
    public cvicenieDTO updateCvicenie(Long id, cvicenieDTO cvicenieDto) {
        Optional<cvicenieEntity> optionalCvicenie = cvicenieRepository.findById(id);
        if (optionalCvicenie.isPresent()) {
            cvicenieEntity entity = optionalCvicenie.get();
            entity.setNazovCviku(cvicenieDto.getNazovCviku());
            entity.setPlanid(cvicenieDto.getPlanid());
            entity.setPopisCviku(cvicenieDto.getPopisCviku());
            entity.setNarocnostCviku(cvicenieDto.getNarocnostCviku());
            cvicenieEntity updatedEntity = cvicenieRepository.save(entity);
            return mapEntityToDto(updatedEntity);
        } else {
            throw new EntityNotFoundException("Cvičenie s ID " + id + " nebolo nájdené.");
        }
    }

    // vymazanie cvičenia
    public boolean deleteCvicenie(Long id) {
        if (cvicenieRepository.existsById(id)) {
            cvicenieRepository.deleteById(id);
            return true;
        } else {
            throw new EntityNotFoundException("Cvičenie s ID " + id + " nebolo nájdené.");
        }
    }


    //----------------------------------------------------------------------------------------------------------------------------//
    // Pomocná metóda na mapovanie z DTO na entitu
    private cvicenieEntity mapDtoToEntity(cvicenieDTO cvicenieDto) {
        cvicenieEntity entity = new cvicenieEntity();
        entity.setCvidenieid(cvicenieDto.getCvidenieid());
        entity.setNazovCviku(cvicenieDto.getNazovCviku());
        entity.setPlanid(cvicenieDto.getPlanid());
        entity.setPopisCviku(cvicenieDto.getPopisCviku());
        entity.setNarocnostCviku(cvicenieDto.getNarocnostCviku());
        return entity;
    }

    // Pomocná metóda na mapovanie z entity na DTO
    private cvicenieDTO mapEntityToDto(cvicenieEntity entity) {
        cvicenieDTO dto = new cvicenieDTO();
        dto.setCvidenieid(entity.getCvidenieid());
        dto.setNazovCviku(entity.getNazovCviku());
        dto.setPlanid(entity.getPlanid());
        dto.setPopisCviku(entity.getPopisCviku());
        dto.setNarocnostCviku(entity.getNarocnostCviku());
        return dto;
    }
}
