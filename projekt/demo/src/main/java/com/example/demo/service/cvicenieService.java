package com.example.demo.service;
import com.example.demo.persistence.*;
import org.springframework.stereotype.Service;
import com.example.demo.service.cvicenieDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class cvicenieService {
    @Autowired
    private cvicenieRepository cvicenieRepository;
    @Autowired
    private treningovePlanyRepository treningovePlanyRepository;
    @Autowired
    private typCviceniaRepository typCviceniaRepository;
    public cvicenieDTO getCvicenie(Long id) {
        Optional<cvicenieEntity> opt = cvicenieRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        cvicenieEntity entity = opt.get();
        cvicenieDTO dto = new cvicenieDTO();
        dto.setNazovCviku(entity.getNazovCviku());
        dto.setPopisCviku(entity.getPopisCviku());
        dto.setCvicenieid(entity.getCvicenieid());
        dto.setIdTypCvicenia(entity.getIdTypCvicenia().getIdTypCvicenia());
        return dto;
    }

    public Long createCvicenie(cvicenieDTO dto){
        cvicenieEntity entity = new cvicenieEntity();
        if (dto.getIdTypCvicenia() != null) {
            Optional<typCviceniaEntity> typCviceniaOptional = typCviceniaRepository.findById(dto.getIdTypCvicenia());
            typCviceniaOptional.ifPresent(entity::setIdTypCvicenia);
        }
        entity.setNazovCviku(dto.getNazovCviku());
        entity.setPopisCviku(dto.getPopisCviku());
        cvicenieRepository.save(entity);
        return entity.getCvicenieid();

    }

    public ArrayList<cvicenieDTO> getAllCvicenie(){
        Iterable<cvicenieEntity> cviceniaIterable = cvicenieRepository.findAll();
        ArrayList<cvicenieDTO> cviceniaList = new ArrayList<>();
        for(cvicenieEntity entity : cviceniaIterable){
            cvicenieDTO dto = new cvicenieDTO();
            dto.setCvicenieid(entity.getCvicenieid());
            /*dto.setNarocnostCviku(entity.getNarocnostCviku());*/
            if (entity.getIdTypCvicenia() != null) {
                dto.setIdTypCvicenia(entity.getIdTypCvicenia().getIdTypCvicenia());
            }
            dto.setPopisCviku(entity.getPopisCviku());
            dto.setNazovCviku(entity.getNazovCviku());
            cviceniaList.add(dto);
        }
        return cviceniaList;
    }
}
