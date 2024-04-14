package com.example.demo.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
@Table(name = "cvicenie")
public class cvicenieEntity {
    @Column(name="cvicenieid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cvidenieid;
    @Column(name="nazovcviku")
    private String nazovCviku;
    @Column(name="popiscviku")
    private String popisCviku;
    @Column(name="narocnostcviku")
    private String narocnostCviku;

    @ManyToMany
    @JoinTable(
            name = "cvicenie_treningoveplany",
            joinColumns = @JoinColumn(name = "cvicenieid"),
            inverseJoinColumns = @JoinColumn(name = "planid")
    )
    private List<treningovePlanyEntity> cvicenietp;
}
