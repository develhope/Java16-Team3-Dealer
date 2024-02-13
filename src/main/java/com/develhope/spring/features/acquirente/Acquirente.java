package com.develhope.spring.features.acquirente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Acquirente  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long acquirente_id;
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
