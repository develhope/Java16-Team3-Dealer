package com.develhope.spring.features.noleggio;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.veicolo.VeicoloService;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NoleggioService {
    @Autowired
    private NoleggioRepository noleggioRepository;
    @Autowired
    private VeicoloService veicoloService;

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private VenditoreRepository venditoreRepository;


    public Either<Error, Noleggio> creaNoleggio(Long veicoloId, Long utenteId, Long venditoreId, boolean pagato, int giorni) {
        Optional<Veicolo> veicoloCheck = veicoloService.findById(veicoloId);
        if (veicoloCheck.isEmpty()) {
            return Either.left(new Error(510, "veicolo non presente"));

        } else if (!veicoloCheck.get().getStato().equals(StatoVeicolo.DISPONIBILE)) {
            return Either.left(new Error(511, "veicolo non disponibile"));
        }

        Optional<Acquirente> acquirenteCheck = acquirenteRepository.findById(utenteId);
        if (acquirenteCheck.isEmpty()) {
            return Either.left(new Error(512, "acquirente non presente"));
        }
        Optional<Venditore> venditoreCheck = venditoreRepository.findById(venditoreId);
        if (venditoreCheck.isEmpty()) {
            return Either.left(new Error(513, "venditore non presente"));
        }
        Noleggio nuovoNoleggio = new Noleggio();
        nuovoNoleggio.setVeicolo(veicoloCheck.get());
        nuovoNoleggio.setAcquirente(acquirenteCheck.get());
        nuovoNoleggio.setVenditore(venditoreCheck.get());
        nuovoNoleggio.setPagato(pagato);
        veicoloCheck.get().setStato(StatoVeicolo.NON_DISPONIBILE);
        nuovoNoleggio.setInizioNoleggio(Date.valueOf(LocalDate.now()));
        nuovoNoleggio.setFineNoleggio(Date.valueOf(LocalDate.now().plusDays(giorni)));
        nuovoNoleggio.setPrezzoTotale(prezzoTotale(giorni));
        noleggioRepository.saveAndFlush(nuovoNoleggio);
        return Either.right(nuovoNoleggio);
    }

    public List<Noleggio> findAllRentals() {
        return noleggioRepository.findAll();
    }

    public Either<Error,Noleggio> eliminaNoleggioId(long id) {

       Optional<Noleggio> checkNoleggio = noleggioRepository.findById(id);
       if(checkNoleggio.isEmpty()){
           return Either.left(new Error(515,"noleggio non presente"));
       }else {
           noleggioRepository.deleteById(id);
           return Either.left(new Error(204,"risorsa eliminata correttamente"));
       }
    }

    public BigDecimal prezzoTotale(int giorni) {
        Noleggio noleggio = new Noleggio();
        BigDecimal prezzoTotale = noleggio.getPrezzoGiornaliero().multiply(BigDecimal.valueOf(giorni));
        return prezzoTotale;
    }
}
