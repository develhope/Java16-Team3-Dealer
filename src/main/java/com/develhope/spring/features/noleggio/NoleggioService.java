package com.develhope.spring.features.noleggio;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRichiesta;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.veicolo.VeicoloService;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class NoleggioService {
    @Autowired
    private NoleggioRepository noleggioRepository;
    @Autowired
    private VeicoloRepository veicoloRepository;

    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private VenditoreRepository venditoreRepository;


    public Either<Error, Noleggio> creaNoleggio(NoleggioRichiesta noleggioRichiesta) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(noleggioRichiesta.getVeicoloId());
        if (veicoloCheck.isEmpty()) {
            return Either.left(new Error(510, "veicolo non presente"));

        } else if (!veicoloCheck.get().getStato().equals(StatoVeicolo.DISPONIBILE)) {
            return Either.left(new Error(511, "veicolo non disponibile"));
        }

        Optional<Acquirente> acquirenteCheck = acquirenteRepository.findById(noleggioRichiesta.getAcquirenteId());
        if (acquirenteCheck.isEmpty()) {
            return Either.left(new Error(512, "acquirente non presente"));
        }
        Optional<Venditore> venditoreCheck = venditoreRepository.findById(noleggioRichiesta.getVenditoreId());
        if (venditoreCheck.isEmpty()) {
            return Either.left(new Error(513, "venditore non presente"));
        }
        Noleggio nuovoNoleggio = new Noleggio();
        nuovoNoleggio.setVeicolo(veicoloCheck.get());
        nuovoNoleggio.setAcquirente(acquirenteCheck.get());
        nuovoNoleggio.setVenditore(venditoreCheck.get());
        nuovoNoleggio.setPagato(noleggioRichiesta.isPagato());
        veicoloCheck.get().setStato(StatoVeicolo.NON_DISPONIBILE);
        nuovoNoleggio.setInizioNoleggio(Date.valueOf(LocalDate.now()));
        LocalDate nuovaFineNoleggio = noleggioRichiesta.getInizioNoleggio().toLocalDate().plusDays(noleggioRichiesta.getGiorni());
        nuovoNoleggio.setFineNoleggio(Date.valueOf(nuovaFineNoleggio));
        nuovoNoleggio.setPrezzoTotale(prezzoTotale(noleggioRichiesta.getGiorni()));
        nuovoNoleggio.setGiorni(noleggioRichiesta.getGiorni());
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
   public List<Noleggio> findByAcquirenteId(Long id){
        return noleggioRepository.checkNoleggiAcquirenteAttivi(id);
   }
    public List<Noleggio> findByVenditoreId(Long id){
        return noleggioRepository.checkNoleggiVenditoreAttivi(id);
    }
    public Either<Error,Object> modificaNoleggio(Long id, NoleggioRichiesta noleggioRichiesta){
        Optional<Noleggio> checkNoleggio = noleggioRepository.findById(id);
        if(checkNoleggio.isEmpty()){
            return Either.left(new Error(611,"Noleggio non trovato"));
        }
        return Either.right(checkNoleggio.map(noleggio -> {
            Venditore venditore = venditoreRepository.findById(noleggioRichiesta.getVenditoreId()).orElse(null);
            Veicolo veicolo = veicoloRepository.findById(noleggioRichiesta.getVeicoloId()).orElse(null);
            // Aggiorna solo i campi non nulli
            noleggio.setVenditore(Objects.nonNull(noleggioRichiesta.getVenditoreId()) ? venditore : noleggio.getVenditore());
            noleggio.setVeicolo(Objects.nonNull(noleggioRichiesta.getVeicoloId()) ? veicolo : noleggio.getVeicolo());
            noleggio.setInizioNoleggio(Objects.nonNull(noleggioRichiesta.getInizioNoleggio()) ? noleggioRichiesta.getInizioNoleggio() : noleggio.getInizioNoleggio());
            noleggio.setPagato(Objects.nonNull(noleggioRichiesta.isPagato()) ? noleggioRichiesta.isPagato() : noleggio.isPagato());

            if(noleggioRichiesta.getInizioNoleggio() != null){
                if (Objects.nonNull(noleggioRichiesta.getGiorni())) {
                    // Calcola la nuova data di fineNoleggio
                    LocalDate nuovaFineNoleggio = LocalDate.now().plusDays(noleggioRichiesta.getGiorni());
                    noleggio.setFineNoleggio(Date.valueOf(nuovaFineNoleggio));
                    noleggio.setPrezzoTotale(prezzoTotale(noleggioRichiesta.getGiorni()));
                    noleggio.setGiorni(noleggioRichiesta.getGiorni());
                }
            }else {
                return Either.left(new Error(613, "Inizio noleggio o quantità giorni non registrati"));
            }
            return noleggioRepository.saveAndFlush(noleggio);
        }).orElse(null));
    }
}
