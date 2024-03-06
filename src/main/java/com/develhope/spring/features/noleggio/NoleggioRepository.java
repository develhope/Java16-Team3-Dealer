package com.develhope.spring.features.noleggio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {
    @Query(value = "SELECT * FROM noleggio WHERE acquirente_id = :id AND fine_noleggio < CURRENT_DATE()",nativeQuery = true)
    List<Noleggio> checkNoleggiAcquirenteAttivi(@Param("id") Long id);
    @Query(value = "SELECT * FROM noleggio WHERE venditore_id = :id AND fine_noleggio < CURRENT_DATE()", nativeQuery = true)
    List<Noleggio> checkNoleggiVenditoreAttivi(@Param("id") Long id);
    @Query(value = "SELECT (SUM(n.prezzo_totale)) AS guadagnoTotale " +
            "FROM  noleggio n " +
            "WHERE n.fine_noleggio " +
            "BETWEEN :data1 AND :data2 ",nativeQuery = true)
    BigDecimal incassoTotaleNoleggi(@Param("data1") Date data1, @Param("data2") Date data2);
}
