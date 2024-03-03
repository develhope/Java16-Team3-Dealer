package com.develhope.spring.features.ordiniAcquisti;


import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.noleggio.Noleggio;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class OrdineAcquistoService {
    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;
    @Autowired
    private VeicoloRepository veicoloRepository;
    @Autowired
    private AcquirenteRepository acquirenteRepository;
    @Autowired
    private VenditoreRepository venditoreRepository;

    public Either<Error, OrdineAcquisto> creaOrdine(OrdineAcquistoRichiesta ordineAcquistoRichiesta) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(ordineAcquistoRichiesta.getVeicoloId());
        if (veicoloCheck.isEmpty()) {
            return Either.left(new Error(510, "veicolo non presente"));

        } else if (!veicoloCheck.get().getStato().equals(StatoVeicolo.ORDINABILE)) {
            return Either.left(new Error(511, "veicolo non disponibile"));
        }
        Optional<Acquirente> acquirenteCheck = acquirenteRepository.findById(ordineAcquistoRichiesta.getAcquirenteId());
        if (acquirenteCheck.isEmpty()) {
            return Either.left(new Error(512, "acquirente non presente"));
        }
        Optional<Venditore> venditoreCheck = venditoreRepository.findById(ordineAcquistoRichiesta.getVenditoreId());
        if (venditoreCheck.isEmpty()) {
            return Either.left(new Error(513, "venditore non presente"));
        }
        OrdineAcquisto nuovoOrdine = new OrdineAcquisto();
        nuovoOrdine.setVeicolo(veicoloCheck.get());
        nuovoOrdine.setAcquirente(acquirenteCheck.get());
        nuovoOrdine.setVenditore(venditoreCheck.get());
        nuovoOrdine.setAnticipo(ordineAcquistoRichiesta.getAnticipo());
        System.out.println(ordineAcquistoRichiesta.getAnticipo());
        System.out.println(veicoloCheck.get().getPrezzo());
        if (Objects.equals(ordineAcquistoRichiesta.getAnticipo(), veicoloCheck.get().getPrezzo())) {
            ordineAcquistoRichiesta.setPagato(true);
            nuovoOrdine.setStato(StatoOrdine.COMPLETATO);
        } else {
            nuovoOrdine.setStato(StatoOrdine.IN_LAVORAZIONE);
            ordineAcquistoRichiesta.setPagato(false);
        }
        nuovoOrdine.setTotale(calcoloTotale(veicoloCheck.get()).subtract(ordineAcquistoRichiesta.getAnticipo()));
        veicoloCheck.get().setStato(StatoVeicolo.NON_DISPONIBILE);
        ordineAcquistoRepository.saveAndFlush(nuovoOrdine);
        return Either.right(nuovoOrdine);

    }

    public Either<Error, OrdineAcquisto> creaAcquisto(OrdineAcquistoRichiesta ordineAcquistoRichiesta) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(ordineAcquistoRichiesta.getVeicoloId());
        if (veicoloCheck.isEmpty()) {
            return Either.left(new Error(510, "veicolo non presente"));

        } else if (!veicoloCheck.get().getStato().equals(StatoVeicolo.ACQUISTABILE)) {
            return Either.left(new Error(511, "veicolo non disponibile"));
        }
        Optional<Acquirente> acquirenteCheck = acquirenteRepository.findById(ordineAcquistoRichiesta.getAcquirenteId());
        if (acquirenteCheck.isEmpty()) {
            return Either.left(new Error(512, "acquirente non presente"));
        }
        Optional<Venditore> venditoreCheck = venditoreRepository.findById(ordineAcquistoRichiesta.getVenditoreId());
        if (venditoreCheck.isEmpty()) {
            return Either.left(new Error(513, "venditore non presente"));
        }
        OrdineAcquisto nuovoAcquisto = new OrdineAcquisto();
        nuovoAcquisto.setVeicolo(veicoloCheck.get());
        nuovoAcquisto.setAcquirente(acquirenteCheck.get());
        nuovoAcquisto.setVenditore(venditoreCheck.get());
        nuovoAcquisto.setAnticipo(ordineAcquistoRichiesta.getAnticipo());
        if (Objects.equals(ordineAcquistoRichiesta.getAnticipo(), veicoloCheck.get().getPrezzo())) {
            ordineAcquistoRichiesta.setPagato(true);
            nuovoAcquisto.setStato(StatoOrdine.COMPLETATO);
        } else {
            nuovoAcquisto.setStato(StatoOrdine.IN_LAVORAZIONE);
            ordineAcquistoRichiesta.setPagato(false);
        }
        nuovoAcquisto.setTotale(calcoloTotale(veicoloCheck.get()).subtract(ordineAcquistoRichiesta.getAnticipo()));
        veicoloCheck.get().setStato(StatoVeicolo.NON_DISPONIBILE);
        ordineAcquistoRepository.saveAndFlush(nuovoAcquisto);
        return Either.right(nuovoAcquisto);
    }

    public List<OrdineAcquisto> findAllOrders() {
        return ordineAcquistoRepository.findAll();
    }

    public void deleteOrderById(long id) {
        ordineAcquistoRepository.deleteById(id);
    }

    public List<OrdineAcquisto> findByStatoOrdine(StatoOrdine stato) {
        return ordineAcquistoRepository.findByStato(stato);
    }

    public Either<Error, OrdineAcquisto> verificaStatoOrdine(Long id) {
        Optional<OrdineAcquisto> checkOrdine = ordineAcquistoRepository.findById(id);
        if (checkOrdine.isEmpty()) {
            return Either.left(new Error(516, "ordine non presente"));
        } else {
            return Either.right(checkOrdine.get());
        }
    }

    public List<OrdineAcquisto> findByAcquirenteId(Long id) {
        return ordineAcquistoRepository.checkOrdiniAcquistiAcquirenteAttivi(id);
    }

    public BigDecimal calcoloTotale(Veicolo veicolo) {
        return veicolo.getPrezzo().subtract(veicolo.getPrezzo().divide(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(veicolo.getPercentualeSconto())));
    }

    public Either<Error, OrdineAcquisto> modificaOrdine(Long id, OrdineAcquistoRichiesta ordineAcquistoRichiesta) {
        Optional<OrdineAcquisto> checkOrdine = ordineAcquistoRepository.findById(id);
        if (checkOrdine.isEmpty()) {
            return Either.left(new Error(610, "Ordine non trovato"));
        }
        return Either.right(checkOrdine.map(ordineId -> {
            Venditore venditore = venditoreRepository.findById(ordineAcquistoRichiesta.getVenditoreId()).orElse(null);
            Veicolo veicolo = veicoloRepository.findById(ordineAcquistoRichiesta.getVeicoloId()).orElse(null);
            // Aggiorna solo i campi non nulli
            ordineId.setVenditore(Objects.nonNull(ordineAcquistoRichiesta.getVenditoreId()) ? venditore : ordineId.getVenditore());
            ordineId.setVeicolo(Objects.nonNull(ordineAcquistoRichiesta.getVeicoloId()) ? veicolo : ordineId.getVeicolo());
            ordineId.setAnticipo(Objects.nonNull(ordineAcquistoRichiesta.getAnticipo()) ? ordineAcquistoRichiesta.getAnticipo() : ordineId.getAnticipo());
            ordineId.setPagato(Objects.nonNull(ordineAcquistoRichiesta.isPagato()) ? ordineAcquistoRichiesta.isPagato() : ordineId.isPagato());
            if (ordineAcquistoRichiesta.isPagato()) {
                ordineId.setStato(StatoOrdine.COMPLETATO);
            }
            return ordineAcquistoRepository.saveAndFlush(ordineId);
        }).orElse(null));
    }
    public Either<Error, OrdineAcquisto> eliminaOrdine(Long ordineId) {
        Optional<OrdineAcquisto> checkOrdine = ordineAcquistoRepository.findById(ordineId);
        if(checkOrdine.isEmpty()){
            return Either.left(new Error(610,"Ordine non trovato"));
        }else {
            ordineAcquistoRepository.deleteById(ordineId);
            return Either.left(new Error(204,"Ordine eliminato correttamente"));
        }
    }
}
