package com.develhope.spring.features.veicolo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo,Long> {
}
