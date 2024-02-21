package com.develhope.spring.features.venditore;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore,Long> {
    Venditore findByEmail(String email);

    Venditore findByTelefono(long telefono);

}
