package com.example.demo.service;
import com.example.demo.persistence.treningovePlanyEntity;
import com.example.demo.persistence.treningovePlanyRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
public class treningovyPlanDTO {
    private Long planId;
    private String nazov;
    private String popis;
}
