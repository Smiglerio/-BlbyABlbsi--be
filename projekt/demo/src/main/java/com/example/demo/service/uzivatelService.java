package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.uzivatelRepository;
import com.example.demo.service.uzivatelDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.uzivatelEntity;

import java.util.Optional;

@Service
public class uzivatelService {
    @Autowired
    private uzivatelRepository uzivatelRepository;

    public uzivatelDTO getUzivatel(Long id) {
        Optional<uzivatelEntity> opt = uzivatelRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        uzivatelEntity entity = opt.get();
        uzivatelDTO dto = new uzivatelDTO();
        dto.setMeno(entity.getMeno());
        dto.setPriezvisko(entity.getPriezvisko());
        dto.setUserId(entity.getUserId());
        dto.setUsername(entity.getUsername());
        dto.setHeslo(entity.getMeno());
        dto.setVaha(entity.getVaha());
        dto.setVek(entity.getVek());
        dto.setVyska(entity.getVyska());
        dto.setPohlavie(entity.getPohlavie());
        return dto;
    }

    public Long createUzivatel(uzivatelDTO dto) {
        uzivatelEntity uzivatelEntity = new uzivatelEntity();
        uzivatelEntity.setMeno(dto.getMeno());
        uzivatelEntity.setPriezvisko(dto.getPriezvisko());
        uzivatelEntity.setVaha(dto.getVaha());
        uzivatelEntity.setUsername(dto.getUsername());
        uzivatelEntity.setHeslo(dto.getHeslo());
        uzivatelEntity.setVyska(dto.getVyska());
        uzivatelEntity.setPohlavie(dto.getPohlavie());
        uzivatelEntity.setUserId(dto.getUserId());
        uzivatelEntity.setVek(dto.getVek());
        uzivatelRepository.save(uzivatelEntity);
        return uzivatelEntity.getUserId();
    }
}
