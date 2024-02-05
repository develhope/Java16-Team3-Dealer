package com.example.TeamProject3.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
enum StatoOrdine{
    IN_ATTESA,
    IN_LAVORAZIONE,
    COMPLETATO
}
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdineOAcquisto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private BigDecimal anticipo;
    @Column(nullable = false)
    private boolean pagato;
    @Column(nullable = false)
    private StatoOrdine stato;




}
