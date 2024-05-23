package com.example.demo.service;
import com.example.demo.persistence.cvicenieEntity;
import com.example.demo.persistence.typCviceniaEntity;
import jakarta.annotation.PostConstruct;
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

    private static final typCviceniaEntity ZACIATOCNIK = new typCviceniaEntity(null,"Začiatočník", 5);
    private static final typCviceniaEntity POKROCILY = new typCviceniaEntity(null,"Pokročilý", 10);
    private static final typCviceniaEntity EXPERT = new typCviceniaEntity(null,"Expert", 15);
    private static final typCviceniaEntity GRECKYPOLOBOH = new typCviceniaEntity(null,"grécky poloboh", 20);
    private static final typCviceniaEntity MARTINBENDIK = new typCviceniaEntity(null,"Martin Bendík", 1000);

    @PostConstruct
    @Transactional
    public void createTypyCviceni() {
        kontrola(ZACIATOCNIK);
        kontrola(POKROCILY);
        kontrola(EXPERT);
        kontrola(GRECKYPOLOBOH);
        kontrola(MARTINBENDIK);
    }
    public void kontrola(typCviceniaEntity typCviceniaEntity){
        if(!typCviceniaRepository.existsByNarocnost(typCviceniaEntity.getNarocnost())){
            typCviceniaRepository.save(typCviceniaEntity);
        }
    }

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
}
