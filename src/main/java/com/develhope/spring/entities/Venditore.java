package com.example.TeamProject3.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Venditore extends com.example.TeamProject3.Entities.Utente{
    @Column(nullable = false)
    private long telefono;
    private String nomeAzienda;
}
