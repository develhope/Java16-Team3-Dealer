package com.develhope.spring.features.noleggio;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.veicolo.Veicolo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noleggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noleggio_id;
//    private LocalDateTime inizioNoleggio;
//    private LocalDateTime fineNoleggio;
    private boolean pagato;

    @ManyToOne
    @JoinColumn(name = "acquirente_id") //, referencedColumnName = "id"
    private Acquirente acquirente;

    @ManyToOne
    @JoinColumn(name = "veicolo_id")
    private Veicolo veicolo;

}
