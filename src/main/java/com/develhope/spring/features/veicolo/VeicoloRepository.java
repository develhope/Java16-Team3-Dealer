package com.develhope.spring.features.veicolo;

import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
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

    @Query(value = "SELECT v.*, COUNT(oa.veicolo_id) AS numeroVendite " +
            "FROM ordine_acquisto oa " +
            "JOIN veicolo v ON v.veicolo_id = oa.veicolo_id " +
            "GROUP BY v.veicolo_id " +
            "ORDER BY numeroVendite DESC " +
            "LIMIT 1", nativeQuery = true)
    Veicolo veicoloPiuVenduto();

    @Query(value = "SELECT v.* " +
            "FROM ordine_acquisto oa " +
            "JOIN veicolo v " +
            "ON v.veicolo_id = oa.veicolo_id " +
            "GROUP BY v.modello " +
            "HAVING MAX(v.prezzo) " +
            "ORDER BY v.prezzo DESC " +
            "LIMIT 1",nativeQuery = true)
    Veicolo veicoloPiùCostosoVenduto();
}
