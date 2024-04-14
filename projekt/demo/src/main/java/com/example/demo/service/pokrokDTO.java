package com.example.demo.service;
import com.example.demo.persistence.cvicenieEntity;
import com.example.demo.persistence.uzivatelEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
public class pokrokDTO {
    private Long pokrokid;
    private Date datum;
    private uzivatelEntity uzivatelEntity;
    private cvicenieEntity cvicenieEntity;
}
