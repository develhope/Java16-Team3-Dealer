package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/autosalone/admin")
public class AmministratoreController {
    @Autowired
    private AmministratoreService amministratoreService;
    @Autowired
    private VeicoloService veicoloService;

    @PostMapping("/veicolo/creazione")
    public Veicolo creaVeicolo(@RequestBody Veicolo veicolo){
       return veicoloService.saveVeicolo(veicolo);
    }
    @GetMapping("/veicolo/ricercaStato")
    public List<Veicolo> findByStato(@RequestParam StatoVeicolo stato){
        return veicoloService.findByStato(stato);
    }
    @GetMapping("/veicolo/ricercaNuovo")
    public List<Veicolo> findByNuovo(@RequestParam boolean nuovo){
        return veicoloService.findByNuovo(nuovo);
    }

    @PatchMapping("/veicolo/modificaStatus/{id}")
    public Optional<Veicolo> modificaStatusID(@PathVariable Long id, @RequestParam StatoVeicolo stato){
        return veicoloService.modificaStatoVeicolo(id, stato);

    }
    @DeleteMapping("/veicolo/delete/{id}")
    public ResponseEntity cancellaVeicoloId(@PathVariable Long id){
        return veicoloService.cancellaVeicoloId(id);
    }


}
