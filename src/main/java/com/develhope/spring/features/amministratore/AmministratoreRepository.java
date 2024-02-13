package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.veicolo.Veicolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmministratoreRepository extends JpaRepository<Veicolo,Long> {
}
