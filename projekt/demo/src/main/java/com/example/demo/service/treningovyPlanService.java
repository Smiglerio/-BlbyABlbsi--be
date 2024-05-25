package com.example.demo.service;
import com.example.demo.persistence.*;
import lombok.Data;
import org.hibernate.mapping.Any;
import org.springframework.stereotype.Service;
import com.example.demo.service.treningovyPlanDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class treningovyPlanService {
    @Autowired
    private treningovePlanyRepository treningovePlanyRepository;
    @Autowired
    private cvicenieRepository cvicenieRepository;
    @Autowired
    private uzivatelRepository uzivatelRepository;
    @Autowired
    private pokrokRepository pokrokRepository;

    @Autowired
    private TokenRepository tokenRepository;
    public treningovyPlanDTO getTreningovyPlan(Long id){
        treningovePlanyEntity entity = treningovePlanyRepository.findById(id).get();
        treningovyPlanDTO dto = new treningovyPlanDTO();
        dto.setPlanId(entity.getPlanId());
        dto.setPopis(entity.getPopis());
        dto.setNazov(entity.getNazov());
        return dto;
    }

    public Long createTreningovyPlan(treningovyPlanDTO dto){
        treningovePlanyEntity entity = new treningovePlanyEntity();
        entity.setNazov(dto.getNazov());
        entity.setPlanId(dto.getPlanId());
        entity.setPopis(dto.getPopis());
        this.treningovePlanyRepository.save(entity);
        pridajCviceniaDoPlanu(entity, dto.getCviceniaList());
        return entity.getPlanId();
    }


    public void pridajCviceniaDoPlanu(treningovePlanyEntity plan, List<Long> idCvicenie) {
        List<cvicenieEntity> cvicenia = new ArrayList<>();
        for (Long id : idCvicenie) {
            Optional<cvicenieEntity> cvicenieOpt = cvicenieRepository.findById(id);
            cvicenieOpt.ifPresent(cvicenia::add);
        }
        plan.setCvicenia(cvicenia);
        treningovePlanyRepository.save(plan);
    }

    public ArrayList<treningovyPlanDTO> getAllTreningovePlany(){
        Iterable<treningovePlanyEntity> treningovePlanyIterable = treningovePlanyRepository.findAll();
        ArrayList<treningovyPlanDTO> treningyList = new ArrayList<>();
        for(treningovePlanyEntity entity : treningovePlanyIterable){
            treningovyPlanDTO dto = new treningovyPlanDTO();
            dto.setPlanId(entity.getPlanId());
            dto.setPopis(entity.getPopis());
            dto.setNazov(entity.getNazov());
            treningyList.add(dto);
        }
        return treningyList;
    }

    public ArrayList<treningovyPlanDTO> getAllTreningovePlanyUsera(String token){
        TokenEntity tokenEntity = tokenRepository.findByToken(token).get();
        uzivatelEntity user = tokenEntity.getUser();
        List<treningovePlanyEntity> treningovePlany = user.getUsertp();
        ArrayList<treningovyPlanDTO> treningovePlanyDTO = new ArrayList<>();
        for (treningovePlanyEntity plan : treningovePlany) {
            treningovyPlanDTO dto = new treningovyPlanDTO();
            dto.setPlanId(plan.getPlanId());
            dto.setNazov(plan.getNazov());
            dto.setPopis(plan.getPopis());
            treningovePlanyDTO.add(dto);
        }
        return treningovePlanyDTO;
    }

    public ArrayList<cvicenieDTO> getCviceniaByPlan(Long id) {
        Optional<treningovePlanyEntity> opt = treningovePlanyRepository.findById(id);
        ArrayList<cvicenieDTO> cviceniaList = new ArrayList<>();
        if (opt.isPresent()) {
            List<cvicenieEntity> cvicenia = opt.get().getCvicenia();
            for (cvicenieEntity cvicenie : cvicenia) {
                cvicenieDTO dto = new cvicenieDTO();
                dto.setCvicenieid(cvicenie.getCvicenieid());
                dto.setNazovCviku(cvicenie.getNazovCviku());
                dto.setPopisCviku(cvicenie.getPopisCviku());
                dto.setIdTypCvicenia(cvicenie.getIdTypCvicenia().getIdTypCvicenia());
                dto.setNarocnost(cvicenie.getIdTypCvicenia().getNarocnost());
                dto.setPocetOpakovani(cvicenie.getIdTypCvicenia().getPocetOpakovani());
                cviceniaList.add(dto);
            }
        }
        return cviceniaList;
    }

    public ArrayList<cvicenieDTO> getCviceniaByPlanForUser(String token_PlanId) {
        String token = token_PlanId.split("/")[0];
        String planID = token_PlanId.split("/")[1];
        uzivatelEntity user = tokenRepository.findByToken(token).get().getUser();

        Optional<treningovePlanyEntity> opt = treningovePlanyRepository.findById(Long.valueOf(planID));
        ArrayList<cvicenieDTO> cviceniaList = new ArrayList<>();
        if (opt.isPresent()) {
            List<cvicenieEntity> cvicenia = opt.get().getCvicenia();
            for (cvicenieEntity cvicenie : cvicenia) {
                cvicenieDTO dto = new cvicenieDTO();
                dto.setCvicenieid(cvicenie.getCvicenieid());
                dto.setNazovCviku(cvicenie.getNazovCviku());
                dto.setPopisCviku(cvicenie.getPopisCviku());
                dto.setIdTypCvicenia(cvicenie.getIdTypCvicenia().getIdTypCvicenia());
                dto.setNarocnost(cvicenie.getIdTypCvicenia().getNarocnost());
                dto.setPocetOpakovani(cvicenie.getIdTypCvicenia().getPocetOpakovani());
                Optional<pokrokEntity> pke = pokrokRepository.findByUzivatelEntityAndCvicenieEntityAndTreningovePlanyEntity(user, cvicenie, opt.get());
                if (pke.isPresent()) {
                    dto.setOdcvicene(true);
                    dto.setDatumOdcvicenia(pke.get().getDatum());
                } else {
                    dto.setOdcvicene(false);
                }
                cviceniaList.add(dto);
            }
        }
        return cviceniaList;
    }

    public Long detelePlan(String planID ) {
        Long planIdLong = Long.valueOf(planID);
        Optional<treningovePlanyEntity> planOptional = treningovePlanyRepository.findById(planIdLong);
        if (planOptional.isPresent()) {
            treningovePlanyEntity plan = planOptional.get();
            pokrokRepository.deleteAll(pokrokRepository.findByTreningovePlanyEntity(plan));
            Iterable<uzivatelEntity> users = uzivatelRepository.findAll();
            for (uzivatelEntity user : users) {
                user.getUsertp().remove(plan);
                uzivatelRepository.save(user);
            }
            for (cvicenieEntity cvicenie : plan.getCvicenia()) {
                cvicenie.getCvicenietp().remove(plan);
                cvicenieRepository.save(cvicenie);
            }
            treningovePlanyRepository.delete(plan);
        }

        return null;
    }

    public Long deletePlanInUser(String token_PlanId ) {
        String token = token_PlanId.split("/")[0];
        String planID = token_PlanId.split("/")[1];
        uzivatelEntity user = tokenRepository.findByToken(token).get().getUser();
        treningovePlanyEntity plan = treningovePlanyRepository.findById(Long.parseLong(planID)).get();
        pokrokRepository.deleteAll(pokrokRepository.findByTreningovePlanyEntityAndUzivatelEntity(plan, user));
        user.getUsertp().remove(plan);
        uzivatelRepository.save(user);
        return null;
    }
}
