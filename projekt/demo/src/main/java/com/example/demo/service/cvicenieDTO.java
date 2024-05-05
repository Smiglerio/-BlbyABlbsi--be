package com.example.demo.service;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
public class cvicenieDTO {
    private Long cvicenieid;
    private String nazovCviku;
    private String popisCviku;
    private Long idTypCvicenia;
    private String narocnost;
    private int pocetOpakovani;
}
