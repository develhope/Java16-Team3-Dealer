package com.develhope.spring.features.veicolo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VeicoloService {
    @Autowired
    private VeicoloRepository veicoloRepository;

    public Optional<Veicolo> findById(Long id){
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if(veicoloCheck.isPresent()) {
            return veicoloCheck;
        }else {
            return Optional.empty();
        }
    }

    public ResponseEntity checkVeicolo(Long id){
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (veicoloCheck.isPresent() && veicoloCheck.get().getStato().equals(StatoVeicolo.ORDINABILE)) {
            return ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile");
        }else if (veicoloCheck.isPresent() && !(veicoloCheck.get().getStato().equals(StatoVeicolo.ORDINABILE))) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Veicolo non disponibile");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veicolo non presente");
        }
    }

}
