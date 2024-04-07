package com.example.demo.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
@Table(name = "pokrok")
public class pokrokEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="cvicenieid", nullable = false)
    private Long cvicenieid;
    @Column(name="datum")
    private Date datum;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name="userid", nullable = false)
    private Long userid;
}
