package com.develhope.spring.features.veicolo;

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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long veicolo_id;
    private String modello;
    private Long cilindrata;
    private String colore;
    private int potenza;
    private String tipoCambio;
   // private OffsetDateTime annoMatricolazione;
    private String alimentazione; //idrogeno!
    private BigDecimal prezzo;
    private int percentualeSconto; //EVENTUALE
    private String optional; //EVENTUALI
    private boolean nuovo;
    @Enumerated(value = EnumType.STRING)
    private Tipo tipo;
    @Enumerated(value = EnumType.STRING)
    private StatoVeicolo stato;
}
