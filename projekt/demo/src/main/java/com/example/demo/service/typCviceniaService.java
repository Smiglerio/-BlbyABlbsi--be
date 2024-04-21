package com.example.demo.service;
import com.example.demo.persistence.cvicenieEntity;
import com.example.demo.persistence.typCviceniaEntity;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.typCviceniaRepository;
import com.example.demo.service.typCviceniaDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.persistence.typCviceniaEntity;
import java.util.ArrayList;
import java.util.Optional;
@Service
public class typCviceniaService {
    @Autowired
    private typCviceniaRepository typCviceniaRepository;

    public typCviceniaDTO getTypCvicenia(Long id){
        Optional<typCviceniaEntity> opt = typCviceniaRepository.findById(id);
        if (opt.isEmpty()) {
            return null;
        }
        typCviceniaEntity entity = opt.get();
        typCviceniaDTO dto = new typCviceniaDTO();
        dto.setIdTypCvicenia(entity.getIdTypCvicenia());
        dto.setNarocnost(entity.getNarocnost());
        dto.setPocetOpakovani(entity.getPocetOpakovani());
        return dto;
    }

    public Long createTypCvicenia(typCviceniaDTO dto){
        typCviceniaEntity entity = new typCviceniaEntity();
        entity.setPocetOpakovani(dto.getPocetOpakovani());
        entity.setNarocnost(dto.getNarocnost());
        typCviceniaRepository.save(entity);
        return entity.getIdTypCvicenia();
    }
    public ArrayList<typCviceniaDTO> getAllTypCvicenia(){
        Iterable<typCviceniaEntity> cviceniaTypIterable = typCviceniaRepository.findAll();
        ArrayList<typCviceniaDTO> cviceniaTypList = new ArrayList<>();
        for(typCviceniaEntity entity : cviceniaTypIterable){
            typCviceniaDTO dto = new typCviceniaDTO();
            dto.setIdTypCvicenia(entity.getIdTypCvicenia());
            dto.setNarocnost(entity.getNarocnost());
            dto.setPocetOpakovani(entity.getPocetOpakovani());
            cviceniaTypList.add(dto);
        }
        return cviceniaTypList;
    }

    /*public typCviceniaDTO updatetypCvicenia(Long id, typCviceniaDTO updateTyp){
        Optional<typCviceniaEntity> opt = typCviceniaRepository.findById(id);
        if (opt.isPresent()) {
            typCviceniaEntity existujuciTyp = opt.get();
            existujuciTyp.setNarocnost(updateTyp.getNarocnost());
            existujuciTyp.setPocetOpakovani(updateTyp.getPocetOpakovani());
            typCviceniaEntity updatedTypCvicenia = typCviceniaRepository.save(existujuciTyp);
            typCviceniaDTO updatedTypCviceniaDTO = new typCviceniaDTO();
            updatedTypCviceniaDTO.setNarocnost(updatedTypCvicenia.getNarocnost());
            updatedTypCviceniaDTO.setPocetOpakovani(updatedTypCvicenia.getPocetOpakovani());
            return updatedTypCviceniaDTO;
        } else {
            return null;
        }
    }*/
}
