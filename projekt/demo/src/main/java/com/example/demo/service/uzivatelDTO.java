package com.example.demo.service;


import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
public class uzivatelDTO {
    private String username;
    private String heslo;
    private int vek;
    private String vaha;
    private int vyska;
    private String pohlavie;
    private Long userId;
    private String token;
    private Set<String> roles = new HashSet<>();
    private List<Integer> vahaList;
}
