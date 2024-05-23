package com.example.demo.service;
import com.example.demo.persistence.*;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class uzivatelService {

    @Autowired
    private uzivatelRepository uzivatelRepository;
    @Autowired
    private treningovePlanyRepository treningovePlanyRepository;
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private vahaRepository vahaRepository;
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public uzivatelDTO getUzivatel(Long id) {
        Optional<uzivatelEntity> opt = uzivatelRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        uzivatelEntity entity = opt.get();
        uzivatelDTO dto = new uzivatelDTO();
        dto.setUserId(entity.getUserId());
        dto.setUsername(entity.getUsername());
        dto.setHeslo(entity.getHeslo());
        dto.setVaha(entity.getVaha());
        dto.setVek(entity.getVek());
        dto.setVyska(entity.getVyska());
        dto.setPohlavie(entity.getPohlavie());
        return dto;
    }

    public uzivatelDTO getUzivatelFromToken(String token_) {
        Optional<TokenEntity> tokenEntity = tokenRepository.findByToken(token_.split("\"")[3]);
        if (tokenEntity.isPresent()) {
            TokenEntity token = tokenEntity.get();
            uzivatelEntity entity = token.getUser();
            uzivatelDTO dto = new uzivatelDTO();
            dto.setUserId(entity.getUserId());
            dto.setUsername(entity.getUsername());
            dto.setHeslo(entity.getHeslo());
            dto.setVaha(entity.getVaha());
            dto.setVek(entity.getVek());
            dto.setVyska(entity.getVyska());
            dto.setPohlavie(entity.getPohlavie());
            for (RoleEntity role : entity.getRoles()) {
                dto.getRoles().add(role.getRoleName());
            }
            return dto;
        }
        throw new RuntimeException("Authentication failed!");
    }
    public Long createUzivatel(uzivatelDTO dto) {
        uzivatelEntity uzivatelEntity = new uzivatelEntity();
        uzivatelEntity.setVaha(dto.getVaha());
        uzivatelEntity.setUsername(dto.getUsername());
        uzivatelEntity.setHeslo(dto.getHeslo());
        uzivatelEntity.setVyska(dto.getVyska());
        uzivatelEntity.setPohlavie(dto.getPohlavie());
        uzivatelEntity.setUserId(dto.getUserId());
        uzivatelEntity.setVek(dto.getVek());
        this.passwordEncoder().encode(dto.getHeslo());
        uzivatelRepository.save(uzivatelEntity);
        return uzivatelEntity.getUserId();
    }

    public Long createUzivatelTreningPlan(Long userId, Long planId){
        Optional<uzivatelEntity> optUzivatelEntity = uzivatelRepository.findById(userId);
        Optional<treningovePlanyEntity> optTreningovePlany = treningovePlanyRepository.findById(planId);
        if(optTreningovePlany.isPresent() && optUzivatelEntity.isPresent()){
            uzivatelEntity uzivatel = optUzivatelEntity.get();
            treningovePlanyEntity treningPlan = optTreningovePlany.get();
            uzivatel.getUsertp().add(treningPlan);
            uzivatelRepository.save(uzivatel);
            return uzivatel.getUserId();
        }  return null;
    }

    public ArrayList<treningovyPlanDTO> getTreningovePlanyByUserId(Long id) {
        Optional<uzivatelEntity> opt = uzivatelRepository.findById(id);
        ArrayList<treningovyPlanDTO> treningovePlanyList = new ArrayList<>();
        if (opt.isPresent()) {
            List<treningovePlanyEntity> treningy = opt.get().getUsertp();
            for (treningovePlanyEntity trening : treningy) {
                treningovyPlanDTO dto = new treningovyPlanDTO();
                dto.setNazov(trening.getNazov());
                dto.setPopis(trening.getPopis());
                dto.setPlanId(trening.getPlanId());
                treningovePlanyList.add(dto);
            }
        }
        return treningovePlanyList;
    }

    // get všetkých uzívateľov
    public ArrayList<uzivatelDTO> getAllUzivatelia() {
        Iterable<uzivatelEntity> uzivateliaIterable = uzivatelRepository.findAll();
        ArrayList<uzivatelDTO> uzivateliaList = new ArrayList<>();
        for (uzivatelEntity entity : uzivateliaIterable) {
            uzivatelDTO dto = new uzivatelDTO();
            dto.setUserId(entity.getUserId());
            dto.setUsername(entity.getUsername());
            dto.setHeslo(entity.getHeslo());
            dto.setVaha(entity.getVaha());
            dto.setVek(entity.getVek());
            dto.setVyska(entity.getVyska());
            dto.setPohlavie(entity.getPohlavie());
            uzivateliaList.add(dto);
        }
        return uzivateliaList;
    }

    // update existujúceho uzívateľa
/*
    public uzivatelDTO updateUzivatel(Long id, uzivatelDTO updatedUzivatel) {
        Optional<uzivatelEntity> opt = uzivatelRepository.findById(id);
        if (opt.isPresent()) {
            uzivatelEntity existingUzivatel = opt.get();
            // Update other fields as needed
            uzivatelRepository.save(existingUzivatel);
            return updatedUzivatel;
        } else {
            return null;
        }
    }
*/
    public void updateVaha(String token_){
        String token = token_.split("/")[0];
        String novaVaha = token_.split("/")[1];

        System.out.println("nova vaha " + novaVaha);
        uzivatelDTO dto = getUzivatelFromToken(token);
        uzivatelEntity opt = uzivatelRepository.findById(dto.getUserId()).get();
        vahaEntity entity = new vahaEntity();
        entity.setUserId(opt);
        entity.setVaha(dto.getVaha());
        opt.setVaha(novaVaha);
        uzivatelRepository.save(opt);
        vahaRepository.save(entity);
    }
    // delete existujúceho uzívateľa
    @PreAuthorize("ROLE_ADMIN")
    public boolean deleteUzivatel(Long id) {
        Optional<uzivatelEntity> opt = uzivatelRepository.findById(id);
        if (opt.isPresent()) {
            uzivatelRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public String authenticate(String username, String password) {
        uzivatelEntity user = uzivatelRepository.findByUsername(username);
        if (user != null && user.getHeslo().equals(password)) {
            TokenEntity token = new TokenEntity();
            String randomString = UUID.randomUUID().toString();
            token.setToken(randomString);
            token.setUser(user);
            token.setCreatedAt(LocalDateTime.now());
            tokenRepository.save(token);
            return token.getToken();
         } else {
            return null;
        }
    }
}
