package com.example.demo.persistence;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenEntity {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "token")
    private String token;

    @ManyToOne
    @JoinColumn(name = "userid")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private uzivatelEntity user;

    @Column(name = "valid_until", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdAt;

}
