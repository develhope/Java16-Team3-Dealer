package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
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
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private VenditoreRepository venditoreRepository;

    public ResponseEntity modificaDatiAcquirente (Long id, Acquirente acquirente){
        Optional <Acquirente> checkAcquirente = acquirenteRepository.findById(id);
        if(checkAcquirente.isPresent()){
            acquirente.setNome(acquirente.getNome());
            acquirente.setCognome(acquirente.getCognome());
            acquirente.setEmail(acquirente.getEmail());
            acquirente.setPassword(acquirente.getPassword());
            acquirente.setTelefono(acquirente.getTelefono());
            return ResponseEntity.of(Optional.of(acquirente));
        }
        return ResponseEntity.status(404).body("Acquirente non trovato");
    }

    public ResponseEntity modificaDatiVenditore (Long id, Venditore venditore){
        Optional <Venditore> checkVenditore = venditoreRepository.findById(id);
        if(checkVenditore.isPresent()){
            venditore.setNome(venditore.getNome());
            venditore.setCognome(venditore.getCognome());
            venditore.setEmail(venditore.getEmail());
            venditore.setPassword(venditore.getPassword());
            venditore.setTelefono(venditore.getTelefono());
            return ResponseEntity.of(Optional.of(venditore));
        }
        return ResponseEntity.status(404).body("Venditore non trovato");
    }

}
