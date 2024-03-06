package com.develhope.spring.features.venditore;

import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore,Long> {
    Venditore findByEmail(String email);

    Venditore findByTelefono(long telefono);

    @Query(value = "SELECT (SUM(n.prezzo_totale)) AS guadagnoNoleggi " +
            "FROM noleggio n " +
            "JOIN venditore v " +
            "ON n.venditore_id  = v.venditore_id " +
            "WHERE v.venditore_id = :venditoreId " +
            "AND n.fine_noleggio " +
            "BETWEEN :data1 AND :data2 ",nativeQuery = true)
    BigDecimal incassiNoleggi(@Param("venditoreId") Long venditoreId, @Param("data1") Date data1, @Param("data2") Date data2);

    @Query(value = "SELECT (SUM(v.prezzo) - SUM(v.prezzo / 100 * COALESCE(v.percentuale_sconto, 0))) AS guadagnoNoleggi\n" +
            "FROM veicolo v " +
            "JOIN ordine_acquisto oa " +
            "ON oa.veicolo_id = v.veicolo_id " +
            "JOIN venditore ven " +
            "ON ven.venditore_id = oa.venditore_id " +
            "WHERE oa.stato = 'COMPLETATO' " +
            "AND ven.venditore_id = :venditoreId " +
            "AND oa.data_ordine_acquisto " +
            "BETWEEN :data1 AND :data2 ", nativeQuery = true)
    BigDecimal incassiOrdiniAcquisti(@Param("venditoreId") Long venditoreId, @Param("data1") Date data1, @Param("data2") Date data2);
}
