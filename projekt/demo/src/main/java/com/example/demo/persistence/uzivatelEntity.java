package com.example.demo.persistence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data // automaticky doplni gettre, setre , toString , ...
@NoArgsConstructor // automaticky doplni bezparametricky konstrucktor
@AllArgsConstructor // automaticky doplni parametricky konstruktor pre vsetky atributy
@Table(name = "uzivatel")
public class uzivatelEntity {
    @Column(name = "username")
    private String username;
    @Column(name = "heslo")
    private String heslo;
    @Column(name = "vek")
    private int vek;
    @Column(name = "vaha")
    private int vaha;
    @Column(name = "vyska")
    private int vyska;
    @Column(name = "pohlavie")
    private String pohlavie;
    @Column(name = "userid")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @ManyToMany
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "usertp",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "planid")
    )
    private List<treningovePlanyEntity> usertp;

}
