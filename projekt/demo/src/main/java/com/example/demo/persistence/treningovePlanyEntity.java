package com.example.demo.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
@Table(name = "treningoveplany")
public class treningovePlanyEntity {
    @Column(name="planid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long planId;
    @Column(name="nazov")
    private String nazov;
    @Column(name="popis")
    private String popis;


    @ManyToMany
    @JoinTable(
            name = "cvicenie_treningoveplany",
            joinColumns = @JoinColumn(name = "planid"),
            inverseJoinColumns = @JoinColumn(name = "cvicenieid")
    )
    private List<cvicenieEntity> cvicenia;
}
