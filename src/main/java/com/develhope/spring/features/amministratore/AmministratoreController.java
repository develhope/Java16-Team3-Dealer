package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/autosalone/admin")
public class AmministratoreController {
    @Autowired
    private AmministratoreService service;

    @PostMapping("/veicolo/creazione")
    public Veicolo creaVeicolo(@RequestBody Veicolo veicolo){
       return service.saveVeicolo(veicolo);
    }

    @PatchMapping("/veicolo/modificaStatus/{id}")
    public Optional<Veicolo> modificaStatusID(@PathVariable Long id, @RequestParam StatoVeicolo stato){
        return service.modificaStatoVeicolo(id, stato);

    }
}
