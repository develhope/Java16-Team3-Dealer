package com.develhope.spring.features.venditore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venditore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long venditore_id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private long telefono;
}
