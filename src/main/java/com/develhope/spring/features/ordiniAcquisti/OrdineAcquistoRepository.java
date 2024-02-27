package com.develhope.spring.features.ordiniAcquisti;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineAcquistoRepository extends JpaRepository<OrdineAcquisto,Long> {
    List<OrdineAcquisto> findByStato(StatoOrdine stato);
}
