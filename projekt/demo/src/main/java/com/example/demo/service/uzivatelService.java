package com.example.demo.service;
import ch.qos.logback.classic.encoder.JsonEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.uzivatelRepository;
import com.example.demo.service.uzivatelDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.uzivatelEntity;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class uzivatelService {

    @Autowired
    private uzivatelRepository uzivatelRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

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
        this.passwordEncoder.encode(dto.getHeslo());
        uzivatelRepository.save(uzivatelEntity);
        return uzivatelEntity.getUserId();
    }


    // get všetkých uzívateľov
    public ArrayList<uzivatelDTO> getAllUzivatelia() {
        Iterable<uzivatelEntity> uzivateliaIterable = uzivatelRepository.findAll();
        ArrayList<uzivatelDTO> uzivateliaList = new ArrayList<>();
        for (uzivatelEntity entity : uzivateliaIterable) {
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
            uzivateliaList.add(dto);
        }
        return uzivateliaList;
    }

    // update existujúceho uzívateľa
    public uzivatelDTO updateUzivatel(Long id, uzivatelDTO updatedUzivatel) {
        Optional<uzivatelEntity> opt = uzivatelRepository.findById(id);
        if (opt.isPresent()) {
            uzivatelEntity existingUzivatel = opt.get();
            existingUzivatel.setMeno(updatedUzivatel.getMeno());
            existingUzivatel.setPriezvisko(updatedUzivatel.getPriezvisko());
            // Update other fields as needed
            uzivatelRepository.save(existingUzivatel);
            return updatedUzivatel;
        } else {
            return null;
        }
    }

    // delete existujúceho uzívateľa
    public boolean deleteUzivatel(Long id) {
        Optional<uzivatelEntity> opt = uzivatelRepository.findById(id);
        if (opt.isPresent()) {
            uzivatelRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public boolean authenticate(String username, String password) {
        uzivatelEntity user = uzivatelRepository.findByUsername(username);
        if (user != null && user.getHeslo().equals(password)) {
            return true;
        } else {
            return false;
        }
    }
}
