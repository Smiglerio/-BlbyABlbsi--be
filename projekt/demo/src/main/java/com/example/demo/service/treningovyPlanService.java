package com.example.demo.service;
import com.example.demo.persistence.cvicenieEntity;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.treningovePlanyRepository;
import com.example.demo.service.treningovyPlanDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.treningovePlanyEntity;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class treningovyPlanService {
    @Autowired
    private treningovePlanyRepository treningovePlanyRepository;

    public treningovyPlanDTO getTreningovyPlan(Long id){
        Optional<treningovePlanyEntity> opt = treningovePlanyRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        treningovePlanyEntity entity = opt.get();
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
        entity.setNazov(dto.getNazov());
        return entity.getPlanId();
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

}
