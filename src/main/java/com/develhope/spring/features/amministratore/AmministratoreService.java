package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.veicolo.Veicolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmministratoreService {
    @Autowired
    private AmministratoreRepository repository;

    public Veicolo saveVeicolo(Veicolo veicolo){
       return repository.saveAndFlush(veicolo);
    }
}
