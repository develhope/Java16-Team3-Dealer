package com.develhope.spring.features.acquirente;

import com.develhope.spring.features.noleggio.Noleggio;
import com.develhope.spring.features.noleggio.NoleggioRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoService;
import com.develhope.spring.features.ordiniAcquisti.OrdineOAcquisto;
import com.develhope.spring.features.ordiniAcquisti.StatoOrdine;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AcquirenteService {
    @Autowired
    private AcquirenteRepository acquirenteRepository;
    @Autowired
    private VeicoloRepository veicoloRepository;
    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    @Autowired
    private OrdineAcquistoService ordineAcquistoService;
    @Autowired
    private NoleggioRepository noleggioRepository;

    public ResponseEntity creaOrdine(Long id, BigDecimal anticipo, boolean pagato) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (ordineAcquistoService.checkVeicolo(id).equals(ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile"))) {
            OrdineOAcquisto nuovoOrdine = new OrdineOAcquisto();
            nuovoOrdine.setVeicolo(veicoloCheck.get());
//            nuovoOrdine.setAcquirente();
//            nuovoOrdine.setVenditore();
            nuovoOrdine.setAnticipo(anticipo);
            nuovoOrdine.setPagato(pagato);
            nuovoOrdine.setStato(StatoOrdine.IN_LAVORAZIONE);
            ordineAcquistoRepository.saveAndFlush(nuovoOrdine);
            return ResponseEntity.status(HttpStatus.OK).body(nuovoOrdine);
        } else{
            return ordineAcquistoService.checkVeicolo(id);
        }
    }
    public List<OrdineOAcquisto> findAllOrders() {
        return ordineAcquistoRepository.findAll();
    }

    public void deleteOrderById(long id) {
        ordineAcquistoRepository.deleteById(id);
    }

    public ResponseEntity creaAcquisto(Long id, BigDecimal anticipo, boolean pagato){
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (ordineAcquistoService.checkVeicolo(id).equals(ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile"))) {
            OrdineOAcquisto nuovoAcquisto = new OrdineOAcquisto();
            nuovoAcquisto.setVeicolo(veicoloCheck.get());
//            nuovoOrdine.setAcquirente();
//            nuovoOrdine.setVenditore();
            nuovoAcquisto.setAnticipo(anticipo);
            nuovoAcquisto.setPagato(pagato);
            nuovoAcquisto.setStato(StatoOrdine.IN_LAVORAZIONE);
            ordineAcquistoRepository.saveAndFlush(nuovoAcquisto);
            return ResponseEntity.status(HttpStatus.OK).body(nuovoAcquisto);
        } else{
            return ordineAcquistoService.checkVeicolo(id);
        }
    }
    public ResponseEntity creaNoleggio(Long id, boolean pagato) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (ordineAcquistoService.checkVeicolo(id).equals(ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile"))) {
            Noleggio nuovoNoleggio = new Noleggio();
            nuovoNoleggio.setVeicolo(veicoloCheck.get());
//            nuovoOrdine.setAcquirente();
//            nuovoOrdine.setVenditore();
            nuovoNoleggio.setPagato(pagato);
            veicoloCheck.get().setStato(StatoVeicolo.NON_DISPONIBILE);
            noleggioRepository.saveAndFlush(nuovoNoleggio);
            return ResponseEntity.status(HttpStatus.OK).body(nuovoNoleggio);
        } else{
            return ordineAcquistoService.checkVeicolo(id);
        }
    }
    public List<Noleggio> findAllRentals() {
        return noleggioRepository.findAll();
    }
    public Optional<Veicolo> findById(Long id){
        return veicoloRepository.findById(id);
    }
}
