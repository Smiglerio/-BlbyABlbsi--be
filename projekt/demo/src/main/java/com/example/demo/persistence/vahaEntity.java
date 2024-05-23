package com.example.demo.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;

@Entity
@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
@Table(name = "vaha")

public class vahaEntity {
    @Column(name="vahaId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vahaId;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private uzivatelEntity userId;

    @Column(name="vaha")
    private String vaha;
    @Column(name = "datum")
    private String datum;
}