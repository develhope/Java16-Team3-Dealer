package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoService;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
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
    @Autowired
    private OrdineAcquistoService ordineAcquistoService;

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
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "510", description = "VEICOLO NON PRESENTE"),
            @ApiResponse(responseCode = "514", description = "VEICOLO NON MODIFICABILE")
    })
    @Operation(summary = "Questo metodo permette di modificare un veicolo, eccetto se presenta lo stato: NON_DISPONIBILE")
    @PatchMapping("/veicolo/modificaVeicolo/{id}")
    public ResponseEntity<?> modificaVeicolo(@PathVariable Long id, @RequestBody Veicolo veicolo){
        Either<Error,Veicolo> result = veicoloService.modificaVeicolo(id,veicolo);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }else{
            return ResponseEntity.ok(result.right());
        }
    }
    @DeleteMapping("/veicolo/elimina/{id}")
    public ResponseEntity cancellaVeicoloId(@PathVariable Long id){
        return veicoloService.cancellaVeicoloId(id);
    }

    @DeleteMapping("/ordine/elimina/{id}")
    public void eliminaOrdine(@PathVariable Long id){
        ordineAcquistoService.deleteOrderById(id);
    }


}
