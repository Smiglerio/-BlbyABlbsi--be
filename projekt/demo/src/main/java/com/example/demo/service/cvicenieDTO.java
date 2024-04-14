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
    private Long cvidenieid;
    private String nazovCviku;
    private String popisCviku;
    private String narocnostCviku;
}
