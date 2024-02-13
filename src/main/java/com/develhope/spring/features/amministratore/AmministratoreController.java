package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.veicolo.Veicolo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autosalone/admin")
public class AmministratoreController {
    @Autowired
    private AmministratoreService service;

    @PostMapping("/veicolo/creazione")
    public Veicolo creaVeicolo(@RequestBody Veicolo veicolo){
       return service.saveVeicolo(veicolo);
    }
}
