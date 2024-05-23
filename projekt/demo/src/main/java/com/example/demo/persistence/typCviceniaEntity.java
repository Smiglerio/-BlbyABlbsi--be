package com.example.demo.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
@Table(name = "typCvicenia")
public class typCviceniaEntity {
    @Column(name = "idTypCvicenia")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTypCvicenia;
    @Column(name = "narocnost")
    private String narocnost;
    @Column(name = "pocetOpakovani")
    private int pocetOpakovani;
}
