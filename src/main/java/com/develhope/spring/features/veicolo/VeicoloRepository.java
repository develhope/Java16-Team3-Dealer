package com.develhope.spring.features.veicolo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VeicoloRepository extends JpaRepository<Veicolo,Long> {
    List<Veicolo> findByModello(String modello);
    List<Veicolo> findByCilindrata(long cilindrata);
    List<Veicolo> findByColore(String colore);
    List<Veicolo> findByPotenza(int potenza);
    List<Veicolo> findByTipoCambio(String tipoCambio);
    List<Veicolo> findByAlimentazione(String alimentazione);
    List<Veicolo> findByPrezzo(BigDecimal prezzo);
    List<Veicolo> findByNuovo(boolean nuovo);
    List<Veicolo> findByTipo(Tipo tipo);
    List<Veicolo> findByStato(StatoVeicolo stato);
}
