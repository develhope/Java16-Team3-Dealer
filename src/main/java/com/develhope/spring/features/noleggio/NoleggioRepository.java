package com.develhope.spring.features.noleggio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoleggioRepository extends JpaRepository<Noleggio, Long> {
    @Query(value = "SELECT * FROM noleggio WHERE acquirente_id = :id AND fine_noleggio < CURRENT_DATE()",nativeQuery = true)
    List<Noleggio> checkNoleggiAcquirenteAttivi(@Param("id") Long id);
    @Query(value = "SELECT * FROM noleggio WHERE venditore_id = :id AND fine_noleggio < CURRENT_DATE()", nativeQuery = true)
    List<Noleggio> checkNoleggiVenditoreAttivi(@Param("id") Long id);
}
