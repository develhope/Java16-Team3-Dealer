package com.develhope.spring.features.acquirente;

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

    public ResponseEntity creaOrdine(Long id, BigDecimal anticipo, boolean pagato) {// andrebbe in ordineService
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
}
