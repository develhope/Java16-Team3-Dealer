package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AmministratoreService {
    @Autowired
    private AmministratoreRepository repository;
    @Autowired
    private VeicoloRepository veicoloRepository;

    public Veicolo saveVeicolo(Veicolo veicolo) {
        return repository.saveAndFlush(veicolo);
    }

    public Optional<Veicolo> modificaStatoVeicolo(Long id, StatoVeicolo stato) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (veicoloCheck.isPresent()) {
            veicoloCheck.get().setStato(stato);
        } else {
            return Optional.empty();
        }
        return veicoloCheck;
    }

    public ResponseEntity cancellaVeicoloId(Long id) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (veicoloCheck.isPresent() && !(veicoloCheck.get().getStato().equals(StatoVeicolo.NON_DISPONIBILE))){
            veicoloRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Veicolo Eliminato");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Veicolo Noleggiato o Acquistato");
        }
    }
}
