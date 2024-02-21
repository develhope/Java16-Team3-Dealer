package com.develhope.spring.features.noleggio;

import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.veicolo.VeicoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoleggioService {
    @Autowired
    private NoleggioRepository noleggioRepository;
    @Autowired
    private VeicoloService veicoloService;

    public ResponseEntity creaNoleggio(Long id, boolean pagato) {
        Optional<Veicolo> veicoloCheck = veicoloService.findById(id);
        if (veicoloService.checkVeicolo(id).equals(ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile"))) {
            Noleggio nuovoNoleggio = new Noleggio();
            nuovoNoleggio.setVeicolo(veicoloCheck.get());
//            nuovoOrdine.setAcquirente();
//            nuovoOrdine.setVenditore();
            nuovoNoleggio.setPagato(pagato);
            veicoloCheck.get().setStato(StatoVeicolo.NON_DISPONIBILE);
            noleggioRepository.saveAndFlush(nuovoNoleggio);
            return ResponseEntity.status(HttpStatus.OK).body(nuovoNoleggio);
        } else{
            return veicoloService.checkVeicolo(id);
        }
    }

    public List<Noleggio> findAllRentals() {
        return noleggioRepository.findAll();
    }
    public void deleteRentalById(long id) {
        noleggioRepository.deleteById(id);
    }
}
