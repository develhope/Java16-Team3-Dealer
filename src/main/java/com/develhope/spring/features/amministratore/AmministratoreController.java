package com.develhope.spring.features.amministratore;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.amministratore.statisticheIncassi.IncassiService;
import com.develhope.spring.features.noleggio.Noleggio;
import com.develhope.spring.features.noleggio.NoleggioRichiesta;
import com.develhope.spring.features.noleggio.NoleggioService;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRichiesta;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoService;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.signUpSignIn.SignUpService;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloService;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.sql.Date;
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
    @Autowired
    private NoleggioService noleggioService;
    @Autowired
    private SignUpService signUpService;

    @Autowired
    private VenditoreService venditoreService;
    @Autowired
    private IncassiService incassiService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "510", description = "VEICOLO NON PRESENTE"),
            @ApiResponse(responseCode = "511", description = "VEICOLO NON DISPONIBILE"),
            @ApiResponse(responseCode = "512", description = "ACQUIRENTE NON PRESENTE"),
            @ApiResponse(responseCode = "513", description = "VENDITORE NON PRESENTE")
    })
    @Operation(summary = "Questo metodo permette di effettuare un acquisto di un veicolo disponibile")
    @PostMapping("/acquisto/creazione")
    public ResponseEntity<?> creaAcquisto(@RequestBody OrdineAcquistoRichiesta ordineAcquistoRichiesta) {
        Either<Error, OrdineAcquisto> result = ordineAcquistoService.creaAcquisto(ordineAcquistoRichiesta);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "510", description = "VEICOLO NON PRESENTE"),
            @ApiResponse(responseCode = "511", description = "VEICOLO NON DISPONIBILE"),
            @ApiResponse(responseCode = "512", description = "ACQUIRENTE NON PRESENTE"),
            @ApiResponse(responseCode = "513", description = "VENDITORE NON PRESENTE")
    })
    @Operation(summary = "Questo metodo permette di effettuare un ordine di un veicolo disponibile")
    @PostMapping("/ordine/creazione")
    public ResponseEntity<?> creaOrdine(@RequestBody OrdineAcquistoRichiesta ordineAcquistoRichiesta) {
        Either<Error, OrdineAcquisto> result = ordineAcquistoService.creaOrdine(ordineAcquistoRichiesta);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "510", description = "VEICOLO NON PRESENTE"),
            @ApiResponse(responseCode = "511", description = "VEICOLO NON DISPONIBILE"),
            @ApiResponse(responseCode = "512", description = "ACQUIRENTE NON PRESENTE"),
            @ApiResponse(responseCode = "513", description = "VENDITORE NON PRESENTE")
    })
    @Operation(summary = "Questo metodo permette di effettuare un noleggio")
    @PostMapping("/noleggio/creazione")
    public ResponseEntity<?> creaNoleggio(@RequestBody NoleggioRichiesta noleggioRichiesta) {
        Either<Error, Noleggio> result = noleggioService.creaNoleggio(noleggioRichiesta);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }

    @PostMapping("/veicolo/creazione")
    public Veicolo creaVeicolo(@RequestBody Veicolo veicolo) {
        return veicoloService.saveVeicolo(veicolo);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "513", description = "VENDITORE NON PRESENTE")
    })
    @Operation(summary = "Questo metodo permette di verificare le vendite registrate in un certo lasso di tempo")
    @GetMapping("/statistiche/venditore/verificaVendite/{venditoreId}")
    public List<OrdineAcquisto> verificaVenditeRangeTempo(@PathVariable Long venditoreId, @RequestParam Date data1, @RequestParam Date data2){
        return ordineAcquistoService.verificaVenditeRangeTempo(venditoreId, data1, data2);
    }
    @Operation(summary = "Questo metodo permette di verificare il veicolo più acquistato/ordinato")
    @GetMapping("/statistiche/veicolo/venditeMaggiori")
    public Veicolo veicoloPiuVenduto(){
        return veicoloService.veicoloPiuVenduto();
    }
    @Operation(summary = "Questo metodo permette di verificare il veicolo col valore più alto acquistato/ordinato")
    @GetMapping("/statistiche/veicolo/valoreMaggioreVenduto")
    public Veicolo veicoloPiuCostosoVenduto(){
        return veicoloService.veicoloPiuCostosoVenduto();
    }

    @Operation(summary = "Questo metodo permette di verificare il veicolo più venduto in un determinato range di tempo")
    @GetMapping("/statistiche/veicolo/venditeMaggiori/rangeTempo")
    public Veicolo veicoloPiuCostosoVendutoRangeTempo(@RequestParam Date data1, @RequestParam Date data2){
        return veicoloService.veicoloPiuVendutoRangeTempo(data1, data2);
    }

    @Operation(summary = "Questo metodo permette di verificare gli incassi totali dell'autosalone in un determinato range di tempo")
    @GetMapping("/statistiche/incassi/incassiRangeTempo")
    public BigDecimal incassoTotaleRangeTempo(@RequestParam Date data1, @RequestParam Date data2){
        return incassiService.incassoTotaleRangeTempo(data1, data2);
    }
    @Operation(summary = "Questo metodo permette di verificare gli incassi totali di un venditore in un determinato range di tempo")
    @GetMapping("/statistiche/incassi/venditoreRangeTempo/{venditoreId}")
    public BigDecimal venditoreIncassiTotaliRangeTempo(@PathVariable Long venditoreId, @RequestParam Date data1, @RequestParam Date data2){
        return incassiService.venditoreIncassiTotaliRangeTempo(venditoreId, data1, data2);
    }

    @GetMapping("/veicolo/ricercaStato")
    public List<Veicolo> findByStato(@RequestParam StatoVeicolo stato) {
        return veicoloService.findByStato(stato);
    }

    @GetMapping("/veicolo/ricercaNuovo")
    public List<Veicolo> findByNuovo(@RequestParam boolean nuovo) {
        return veicoloService.findByNuovo(nuovo);
    }

    @PatchMapping("/veicolo/modificaStatus/{id}")
    public Optional<Veicolo> modificaStatusID(@PathVariable Long id, @RequestParam StatoVeicolo stato) {
        return veicoloService.modificaStatoVeicolo(id, stato);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "510", description = "VEICOLO NON PRESENTE"),
            @ApiResponse(responseCode = "514", description = "VEICOLO NON MODIFICABILE")
    })
    @Operation(summary = "Questo metodo permette di modificare un veicolo, eccetto se presenta lo stato: NON_DISPONIBILE")
    @PatchMapping("/veicolo/modificaVeicolo/{id}")
    public ResponseEntity<?> modificaVeicolo(@PathVariable Long id, @RequestBody Veicolo veicolo) {
        Either<Error, Veicolo> result = veicoloService.modificaVeicolo(id, veicolo);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "610", description = "ORDINE NON TROVATO")
    })
    @Operation(summary = "Questo metodo permette di modificare un ordine")
    @PatchMapping("/ordine/modifica/{id}")
    public ResponseEntity<?> modificaOrdine(@PathVariable Long id, @RequestBody OrdineAcquistoRichiesta ordineAcquistoRichiesta) {
        Either<Error, OrdineAcquisto> result = ordineAcquistoService.modificaOrdine(id, ordineAcquistoRichiesta);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "611", description = "NOLEGGIO NON TROVATO"),
            @ApiResponse(responseCode = "613", description = "DATA INIZIO NOLEGGIO O QUANTITà GIORNI NON REGISTRATI")
    })
    @Operation(summary = "Questo metodo permette di modificare un noleggio")
    @PatchMapping("/noleggio/modifica/{id}")
    public ResponseEntity<?> modificaNoleggio(@PathVariable Long id, @RequestBody NoleggioRichiesta noleggioRichiesta) {
        Either<Error, Object> result = noleggioService.modificaNoleggio(id, noleggioRichiesta);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "612", description = "ACQUISTO NON TROVATO")
    })
    @Operation(summary = "Questo metodo permette di modificare un acquisto")
    @PatchMapping("/acquisto/modifica/{id}")
    public ResponseEntity<?> modificaAcquisto(@PathVariable Long id, @RequestBody OrdineAcquistoRichiesta ordineAcquistoRichiesta) {
        Either<Error, OrdineAcquisto> result = ordineAcquistoService.modificaAcquisto(id, ordineAcquistoRichiesta);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }

    @DeleteMapping("/veicolo/elimina/{id}")
    public ResponseEntity cancellaVeicoloId(@PathVariable Long id) {
        return veicoloService.cancellaVeicoloId(id);
    }

    @DeleteMapping("/ordine/elimina/{id}")
    public void eliminaOrdine(@PathVariable Long id) {
        ordineAcquistoService.deleteOrderById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "515", description = "NOLEGGIO NON PRESENTE"),
            @ApiResponse(responseCode = "204", description = "RISORSA ELIMINATA CORRETTAMENTE")
    })
    @Operation(summary = "Questo metodo permette di cancellare un noleggio tramite id")
    @DeleteMapping("/noleggio/elimina/{id}")
    public ResponseEntity<?> eliminaNoleggio(@PathVariable Long id) {
        Either<Error, Noleggio> result = noleggioService.eliminaNoleggioId(id);
        return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
    }

    @DeleteMapping("/acquisto/elimina/{id}")
    public void eliminaAcquisto(@PathVariable Long id) {
        ordineAcquistoService.deleteOrderById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "600", description = "OK"),
            @ApiResponse(responseCode = "602", description = "ACQUIRENTE NON TROVATO"),
            @ApiResponse(responseCode = "603", description = "NOLEGGI/ORDINI/ACQUISTI IN CORSO, MOMENTANEAMENTE IMPOSSIBILE ELIMINARE ACCOUNT")
    })
    @Operation(summary = "Questo metodo permette di cancellare l' account di un Acquirente, eccetto se ha ancora noleggi/ordini/acquisti in corso")
    @DeleteMapping("/account/elimina/acquirente/{id}")
    public ResponseEntity<String> eliminaAcquirente(@PathVariable Long id) {
        return signUpService.adminEliminaAcquirente(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "600", description = "OK"),
            @ApiResponse(responseCode = "602", description = "VENDITORE NON TROVATO"),
            @ApiResponse(responseCode = "603", description = "NOLEGGI/ORDINI/ACQUISTI IN CORSO, MOMENTANEAMENTE IMPOSSIBILE ELIMINARE ACCOUNT")
    })
    @Operation(summary = "Questo metodo permette di cancellare l' account di un Venditore, eccetto se ha ancora noleggi/ordini/acquisti in corso")
    @DeleteMapping("/account/elimina/venditore/{id}")
    public ResponseEntity<String> eliminaVenditore(@PathVariable Long id) {
        return signUpService.adminEliminaVenditore(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo permette di modificare l'utente")
    @PatchMapping("/account/modifica/acquirente")
    public ResponseEntity modificaDatiUtente(@RequestBody Acquirente acquirente, @RequestParam Long id) {
        return amministratoreService.modificaDatiAcquirente(id, acquirente);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo permette di modificare un venditore")
    @PatchMapping("/account/modifica/venditore")
    public ResponseEntity modificaVenditore(@RequestBody Venditore venditore, @RequestParam Long id) {
        return amministratoreService.modificaDatiVenditore(id, venditore);
    }


}
