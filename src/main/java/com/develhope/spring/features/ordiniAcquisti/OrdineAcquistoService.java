package com.develhope.spring.features.ordiniAcquisti;


import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloService;
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

    public ResponseEntity creaOrdine(Long id, BigDecimal anticipo, boolean pagato) {
        Optional<Veicolo> veicoloCheck = veicoloService.findById(id);
        if (veicoloService.checkVeicolo(id).equals(ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile"))) {
            OrdineAcquisto nuovoOrdine = new OrdineAcquisto();
            nuovoOrdine.setVeicolo(veicoloCheck.get());
//            nuovoOrdine.setAcquirente();
//            nuovoOrdine.setVenditore();
            nuovoOrdine.setAnticipo(anticipo);
            nuovoOrdine.setPagato(pagato);
            nuovoOrdine.setStato(StatoOrdine.IN_LAVORAZIONE);
            ordineAcquistoRepository.saveAndFlush(nuovoOrdine);
            return ResponseEntity.status(HttpStatus.OK).body(nuovoOrdine);
        } else{
            return veicoloService.checkVeicolo(id);
        }
    }


    public ResponseEntity creaAcquisto(Long id, BigDecimal anticipo, boolean pagato){
        Optional<Veicolo> veicoloCheck = veicoloService.findById(id);
        if (veicoloService.checkVeicolo(id).equals(ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile"))) {
            OrdineAcquisto nuovoAcquisto = new OrdineAcquisto();
            nuovoAcquisto.setVeicolo(veicoloCheck.get());
//            nuovoOrdine.setAcquirente();
//            nuovoOrdine.setVenditore();
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
