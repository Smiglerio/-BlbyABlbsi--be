package com.example.demo.controller;
import com.example.demo.persistence.typCviceniaEntity;
import com.example.demo.service.cvicenieDTO;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.typCviceniaService;
import com.example.demo.service.typCviceniaDTO;
// pridane
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("fitness")
public class typCviceniaController {
    @Autowired
    private typCviceniaService typCviceniaService;

    @PostMapping("/api/typCvicenia")
    public Long createTypCvicenia(@RequestBody typCviceniaDTO cvicenieTyp){
        return typCviceniaService.createTypCvicenia(cvicenieTyp);
    }

    @GetMapping("/api/typCvicenia/{id}")
    public typCviceniaDTO getTypCvicenia(@PathVariable Long id){
        return typCviceniaService.getTypCvicenia(id);
    }

    @GetMapping("/api/typCvicenia/list")
    public ArrayList<typCviceniaDTO> getAllTypCvicenia(){
        return typCviceniaService.getAllTypCvicenia();
    }

    /*@PutMapping("/api/typCvicenia/{id}")
    public typCviceniaDTO updatetypCvicenia(@PathVariable Long id, @RequestBody typCviceniaDTO typCviceniaDTO){
        return typCviceniaService.updatetypCvicenia(id,typCviceniaDTO);
    }*/
}
