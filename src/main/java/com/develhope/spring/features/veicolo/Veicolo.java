package com.develhope.spring.features.veicolo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.OffsetDateTime;

enum Tipo{
    AUTO,
    MOTO,
    SCOOTER,
    FURGONI
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
    @JsonFormat(pattern="yyyy")
    private Date annoImmatricolazione;
    private String alimentazione; 
    private BigDecimal prezzo;
    private int percentualeSconto; //EVENTUALE
    private String optional; //EVENTUALI
    private boolean nuovo;
    @Enumerated(value = EnumType.STRING)
    private Tipo tipo;
    @Enumerated(value = EnumType.STRING)
    private StatoVeicolo stato;
}
