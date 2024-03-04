package com.develhope.spring.features.ordiniAcquisti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto,Long> {
    List<OrdineAcquisto> findByStato(StatoOrdine stato);
    @Query(value = "SELECT * FROM ordine_acquisto WHERE acquirente_id = :id AND stato <> 'COMPLETATO'",nativeQuery = true)
    List<OrdineAcquisto> checkOrdiniAcquistiAcquirenteAttivi(@Param("id") Long id);
    @Query(value = "SELECT * FROM ordine_acquisto WHERE venditore_id = :id AND stato <> 'COMPLETATO'", nativeQuery = true)
    List<OrdineAcquisto> checkOrdiniAcquistiVenditoreAttivi(@Param("id") Long id);

    @Query(value = "SELECT oa.* FROM ordine_acquisto oa " +
            "JOIN venditore v " +
            "ON oa.venditore_id  = v.venditore_id " +
            "WHERE v.venditore_id = :venditoreId " +
            "AND oa.data_ordine_acquisto " +
            "BETWEEN  :data1  AND  :data2  "
            ,nativeQuery = true)
    List<OrdineAcquisto> verificaVenditeRangeTempo(@Param("venditoreId") Long venditoreId, @Param("data1") Date data1, @Param("data2") Date data2);

}
