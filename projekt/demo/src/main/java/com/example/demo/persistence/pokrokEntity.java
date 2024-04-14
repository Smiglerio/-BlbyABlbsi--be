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
    @Column(name="pokrokid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pokrokid;
    @ManyToOne
    /*@Column(name="cvicenieid")*/
    @JoinColumn(name="cvicenieid", nullable = false)
    private cvicenieEntity cvicenieEntity;

    //TODO bohvie, čo to je ale mal by som to zmeniť
    @Column(name="datum")
    private Date datum;

    /*@Column(name="userid")*/
    @ManyToOne
    @JoinColumn(name= "userid", nullable = false)
    private uzivatelEntity uzivatelEntity;
}
