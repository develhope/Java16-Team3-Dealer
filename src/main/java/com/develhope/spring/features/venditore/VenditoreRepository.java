package com.develhope.spring.features.venditore;

import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface VenditoreRepository extends JpaRepository<Venditore,Long> {
    Venditore findByEmail(String email);

    Venditore findByTelefono(long telefono);

}
