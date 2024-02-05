package com.example.TeamProject3.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

enum Tipo{
    AUTO,
    MOTO,
    SCOOTER,
    FURGONI
}
enum StatoVeicolo{
    ORDINABILE,
    ACQUISTABILE,
    NON_DISPONIBILE
}
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veicolo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private String modello;
    @Column(nullable = false)
    private long cilindrata;
    @Column(nullable = false)
    private String colore;
    @Column(nullable = false)
    private int potenza;
    @Column(nullable = false)
    private String tipoCambio;
    @Column(nullable = false)
    private OffsetDateTime annoMatricolazione;
    @Column(nullable = false)
    private String alimentazione; //idrogeno!
    @Column(nullable = false)
    private BigDecimal prezzo;
    @Column(nullable = true)
    private int percentualeSconto; //EVENTUALE
    @Column(nullable = true)
    private String optional; //EVENTUALI
    @Column(nullable = false)
    private boolean nuovo;
    @Column(nullable = false)
    private Tipo tipo;
    @Column(nullable = false)
    private StatoVeicolo stato;
}
