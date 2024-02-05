package com.example.TeamProject3.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
@Entity
@Table
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Noleggio {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(nullable = false)
    private OffsetDateTime inizioNoleggio;
    @Column(nullable = false)
    private OffsetDateTime fineNoleggio;
    @Column(nullable = false)
    private boolean pagato;

}
