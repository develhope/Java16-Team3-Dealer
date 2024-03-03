package com.develhope.spring.features.ordiniAcquisti;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.venditore.Venditore;
import jakarta.persistence.*;
import lombok.*;


import java.math.BigDecimal;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrdineAcquisto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long ordine_id;
    @Column(nullable = false)
    private BigDecimal anticipo;
    @Column(nullable = false)
    private boolean pagato;
    @Column(nullable = false)
    private BigDecimal totale;
    @Enumerated(value = EnumType.STRING)
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
