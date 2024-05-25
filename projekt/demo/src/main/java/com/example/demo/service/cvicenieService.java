package com.example.demo.service;
import com.example.demo.persistence.*;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private pokrokRepository pokrokRepository;
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
        /*  dto.setNarocnostCviku(entity.getNarocnostCviku());*/
        dto.setPopisCviku(entity.getPopisCviku());
        dto.setCvicenieid(entity.getCvicenieid());
        return dto;
    }

    //    @PreAuthorize()
    public Long createCvicenie(cvicenieDTO dto){
        cvicenieEntity entity = new cvicenieEntity();
        entity.setCvicenieid(dto.getCvicenieid());
        entity.setNazovCviku(dto.getNazovCviku());
        entity.setPopisCviku(dto.getPopisCviku());
        entity.setIdTypCvicenia(typCviceniaRepository.findById(dto.getIdTypCvicenia()).get());
        this.cvicenieRepository.save(entity);
        return entity.getCvicenieid();
    }

    public ArrayList<cvicenieDTO> getAllCvicenie(){
        Iterable<cvicenieEntity> cviceniaIterable = cvicenieRepository.findAll();
        ArrayList<cvicenieDTO> cviceniaList = new ArrayList<>();
        for(cvicenieEntity entity : cviceniaIterable){
            typCviceniaEntity temp = typCviceniaRepository.findById(entity.getIdTypCvicenia().getIdTypCvicenia()).get();
            cvicenieDTO dto = new cvicenieDTO();
            dto.setCvicenieid(entity.getCvicenieid());
            dto.setNarocnost(temp.getNarocnost());
            dto.setPopisCviku(entity.getPopisCviku());
            dto.setNazovCviku(entity.getNazovCviku());
            cviceniaList.add(dto);
        }
        return cviceniaList;
    }

    public Long deleteCvicenie(String cvicenieId){
        Long cvicenieIdLong = Long.valueOf(cvicenieId);
        Optional<cvicenieEntity> cvicenieOptional = cvicenieRepository.findById(cvicenieIdLong);
        if (cvicenieOptional.isPresent()) {
            cvicenieEntity cvicenie = cvicenieOptional.get();
            pokrokRepository.deleteAll(pokrokRepository.findByCvicenieEntity(cvicenie));
            for (treningovePlanyEntity plan : cvicenie.getCvicenietp()) {
                plan.getCvicenia().remove(cvicenie);
                treningovePlanyRepository.save(plan);
            }
            cvicenieRepository.delete(cvicenie);
        }
        return null;
    }


}
