package com.example.demo.persistence;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "vaha")
public class vahaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="vahaId")
    private Long vahaId;

    @ManyToOne
    @JoinColumn(name="userId", nullable = false)
    private uzivatelEntity userId;

    @Column(name="vaha")
    private String vaha;

    @Column(name = "datum")
    private String datum;
}
