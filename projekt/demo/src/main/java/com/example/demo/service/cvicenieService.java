package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.cvicenieRepository;
import com.example.demo.service.cvicenieDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.cvicenieEntity;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class cvicenieService {
    @Autowired
    private cvicenieRepository cvicenieRepository;

    public cvicenieDTO getCvicenie(Long id) {
        Optional<cvicenieEntity> opt = cvicenieRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        cvicenieEntity entity = opt.get();
        cvicenieDTO dto = new cvicenieDTO();
        dto.setNazovCviku(entity.getNazovCviku());
        dto.setNarocnostCviku(entity.getNarocnostCviku());
        dto.setPopisCviku(entity.getPopisCviku());
        dto.setCvidenieid(entity.getCvidenieid()); // co s tymto ?
        return dto;
    }

    public Long createCvicenie(cvicenieDTO dto){
        cvicenieEntity entity = new cvicenieEntity();
        entity.setCvidenieid(dto.getCvidenieid());
        entity.setNarocnostCviku(dto.getNarocnostCviku());
        entity.setNazovCviku(dto.getNazovCviku());
        entity.setPopisCviku(dto.getPopisCviku());
        return entity.getCvidenieid();
    }

    public ArrayList<cvicenieDTO> getAllCvicenie(){
        Iterable<cvicenieEntity> cviceniaIterable = cvicenieRepository.findAll();
        ArrayList<cvicenieDTO> cviceniaList = new ArrayList<>();
        for(cvicenieEntity entity : cviceniaIterable){
            cvicenieDTO dto = new cvicenieDTO();
            dto.setCvidenieid(entity.getCvidenieid());
            dto.setNarocnostCviku(entity.getNarocnostCviku());
            dto.setPopisCviku(entity.getPopisCviku());
            dto.setNazovCviku(entity.getNazovCviku());
            cviceniaList.add(dto);
        }
        return cviceniaList;
    }

}
