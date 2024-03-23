package com.example.demo.service;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
public class uzivatelDTO {
    private String meno;
    private String priezvisko;
    private String username;
    private String heslo;
    private int vek;
    private String vaha;
    private String vyska;
    private String pohlavie;
    private Long userId;
}
