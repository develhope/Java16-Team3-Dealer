package com.develhope.spring.features.ordiniAcquisti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto,Long> {
    List<OrdineAcquisto> findByStato(StatoOrdine stato);
    @Query(value = "SELECT * FROM ordine_acquisto WHERE acquirente_id = :id AND stato <> 'COMPLETATO'",nativeQuery = true)
    List<OrdineAcquisto> checkOrdiniAcquistiAcquirenteAttivi(@Param("id") Long id);
}
