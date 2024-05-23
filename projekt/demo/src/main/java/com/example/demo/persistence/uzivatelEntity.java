package com.example.demo.persistence;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "uzivatel")
public class uzivatelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userId;

    @Column(name = "username")
    private String username;

    @Column(name = "heslo")
    private String heslo;

    @Column(name = "vek")
    private int vek;

    @Column(name = "vaha")
    private String vaha;

    @Column(name = "vyska")
    private int vyska;

    @Column(name = "pohlavie")
    private String pohlavie;

    @ManyToMany
    private Set<RoleEntity> roles = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "usertp",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "planid")
    )
    private List<treningovePlanyEntity> usertp;

    @OneToMany(mappedBy = "userId")
    private List<vahaEntity> uservaha;
}

