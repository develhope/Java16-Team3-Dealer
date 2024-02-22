package com.develhope.spring.features.noleggio;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.veicolo.VeicoloService;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity creaNoleggio(Long veicoloId, Long utenteId, Long venditoreId, boolean pagato, int giorni) {
        Optional<Veicolo> veicoloCheck = veicoloService.findById(veicoloId);
        Optional<Acquirente> acquirenteCheck = acquirenteRepository.findById(utenteId);
        Optional<Venditore> venditoreCheck = venditoreRepository.findById(venditoreId);

        if (veicoloService.checkVeicolo(veicoloId).equals(ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile"))) {
            Noleggio nuovoNoleggio = new Noleggio();
            nuovoNoleggio.setVeicolo(veicoloCheck.get());
            nuovoNoleggio.setAcquirente(acquirenteCheck.get());
            nuovoNoleggio.setVenditore(venditoreCheck.get());
            nuovoNoleggio.setPagato(pagato);
            veicoloCheck.get().setStato(StatoVeicolo.NON_DISPONIBILE);
            nuovoNoleggio.setInizioNoleggio(Date.valueOf(LocalDate.now()));
            nuovoNoleggio.setFineNoleggio(Date.valueOf(LocalDate.now().plusDays(giorni)));
            noleggioRepository.saveAndFlush(nuovoNoleggio);
            return ResponseEntity.status(HttpStatus.OK).body(nuovoNoleggio);
        } else{
            return veicoloService.checkVeicolo(veicoloId);
        }
    }

    public List<Noleggio> findAllRentals() {
        return noleggioRepository.findAll();
    }
    public void deleteRentalById(long id) {
        noleggioRepository.deleteById(id);
    }
}
