package com.example.demo.service;
import com.example.demo.persistence.typCviceniaEntity;
import com.example.demo.persistence.typCviceniaRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
public class typCviceniaDTO {
    private Long idTypCvicenia;
    private String narocnost;
    private int pocetOpakovani;
}
