package com.develhope.spring.features.noleggio;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.venditore.Venditore;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noleggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noleggio_id;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date inizioNoleggio;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date fineNoleggio;
    private boolean pagato;

    @ManyToOne
    @JoinColumn(name = "acquirente_id") //, referencedColumnName = "id"
    private Acquirente acquirente;

    @ManyToOne
    @JoinColumn(name = "veicolo_id")
    private Veicolo veicolo;

    @ManyToOne
    @JoinColumn(name = "venditore_id")
    private Venditore venditore;

}
