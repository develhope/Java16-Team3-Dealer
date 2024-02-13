package com.develhope.spring.features.amministratore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Amministratore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long amministratore_id;
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
    private String cognome;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
}
