package com.example.demo.persistence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
@Table(name = "uzivatel")
public class uzivatelEntity {
    @Column(name = "meno")
    private String meno;
    @Column(name = "priezvisko")
    private String priezvisko;
    @Column(name = "username")
    private String username;
    @Column(name = "heslo")
    private String heslo;
    @Column(name = "vek")
    private int vek;
    @Column(name = "vaha")
    private String vaha;
    @Column(name = "vyska")
    private String vyska;
    @Column(name = "pohlavie")
    private String pohlavie;
    @Id
    @GeneratedValue
    private Long userId;
}
