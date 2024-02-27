package com.develhope.spring.features.ordiniAcquisti;


import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloService;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrdineAcquistoService {
    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;
    @Autowired
    private VeicoloService veicoloService;
    @Autowired
    private AcquirenteRepository acquirenteRepository;
    @Autowired
    private VenditoreRepository venditoreRepository;

    public Either<Error,OrdineAcquisto> creaOrdine(Long id, Long acquirenteId, Long venditoreId, BigDecimal anticipo, boolean pagato) {
        Optional<Veicolo> veicoloCheck = veicoloService.findById(id);
        if (veicoloCheck.isEmpty()) {
            return Either.left(new Error(510, "veicolo non presente"));

        } else if (!veicoloCheck.get().getStato().equals(StatoVeicolo.ORDINABILE)) {
            return Either.left(new Error(511, "veicolo non disponibile"));
        }
        Optional<Acquirente> acquirenteCheck = acquirenteRepository.findById(acquirenteId);
        if (acquirenteCheck.isEmpty()) {
            return Either.left(new Error(512, "acquirente non presente"));
        }
        Optional<Venditore> venditoreCheck = venditoreRepository.findById(venditoreId);
        if (venditoreCheck.isEmpty()) {
            return Either.left(new Error(513, "venditore non presente"));
        }
            OrdineAcquisto nuovoOrdine = new OrdineAcquisto();
            nuovoOrdine.setVeicolo(veicoloCheck.get());
            nuovoOrdine.setAcquirente(acquirenteCheck.get());
            nuovoOrdine.setVenditore(venditoreCheck.get());
            nuovoOrdine.setAnticipo(anticipo);
            nuovoOrdine.setPagato(pagato);
            nuovoOrdine.setStato(StatoOrdine.IN_LAVORAZIONE);
            ordineAcquistoRepository.saveAndFlush(nuovoOrdine);
            return Either.right(nuovoOrdine);

    }


    public ResponseEntity creaAcquisto(Long id, Long acquirenteId, Long venditoreId, BigDecimal anticipo, boolean pagato){
        Optional<Veicolo> veicoloCheck = veicoloService.findById(id);
        Optional<Acquirente> acquirenteCheck = acquirenteRepository.findById(acquirenteId);
        Optional<Venditore> venditoreCheck = venditoreRepository.findById(venditoreId);
        if (veicoloService.checkVeicolo(id).equals(ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile"))) {
            OrdineAcquisto nuovoAcquisto = new OrdineAcquisto();
            nuovoAcquisto.setVeicolo(veicoloCheck.get());
            nuovoAcquisto.setAcquirente(acquirenteCheck.get());
            nuovoAcquisto.setVenditore(venditoreCheck.get());
            nuovoAcquisto.setAnticipo(anticipo);
            nuovoAcquisto.setPagato(pagato);
            nuovoAcquisto.setStato(StatoOrdine.IN_LAVORAZIONE);
            ordineAcquistoRepository.saveAndFlush(nuovoAcquisto);
            return ResponseEntity.status(HttpStatus.OK).body(nuovoAcquisto);
        } else{
            return veicoloService.checkVeicolo(id);
        }
    }
    public List<OrdineAcquisto> findAllOrders() {
        return ordineAcquistoRepository.findAll();
    }
    public void deleteOrderById(long id) {
        ordineAcquistoRepository.deleteById(id);
    }
}
