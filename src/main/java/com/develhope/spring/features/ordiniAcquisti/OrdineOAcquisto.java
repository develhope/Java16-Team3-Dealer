package com.develhope.spring.features.ordiniAcquisti;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.venditore.Venditore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

enum StatoOrdine {
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ordine_id;
    @Column(nullable = false)
    private BigDecimal anticipo;
    @Column(nullable = false)
    private boolean pagato;
    @Column(nullable = false)
    private StatoOrdine stato;

    @ManyToOne
    @JoinColumn(name = "acquirente_id")//, referencedColumnName = "id"
    private Acquirente acquirente;

    @ManyToOne
    @JoinColumn(name = "veicolo_id")
    private Veicolo veicolo;

    @ManyToOne
    @JoinColumn(name = "venditore_id")
    private Venditore venditore;
}
