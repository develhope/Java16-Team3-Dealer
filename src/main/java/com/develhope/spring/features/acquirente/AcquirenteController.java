package com.develhope.spring.features.acquirente;

import com.develhope.spring.features.ordiniAcquisti.OrdineOAcquisto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("/autosalone/cliente")
public class AcquirenteController {
    @Autowired
    private AcquirenteService acquirenteService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo restituisce gli ordini fatti dall'utente")
    @GetMapping("/getordini")
    public List<OrdineOAcquisto> getOrders() {
        return acquirenteService.findAllOrders();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo restituisce i noleggi fatti dall'utente")
    @GetMapping("/getnoleggi")
    public void getNoleggi() {

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo restituisce gli acquisti fatti dall'utente")
    @GetMapping("/getacquisti")
    public void getAcquisti() {

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "VEICOLO NON TROVATO CON QUELL'ID")
    })
    @Operation(summary = "Questo metodo restituisce i dettagli di un veicolo specifico per id")
    @GetMapping("/getveicolo/{id}")
    public void getVeicoloId() {

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo permette di effettuare un noleggio")
    @PostMapping("/creazionenoleggio")
    public void creaNoleggio() {

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo permette di effettuare un ordine di un veicolo disponibile")
    @PostMapping("/veicolo/creaOrdine/{veicoloId}")
    public ResponseEntity creaOrdine(@PathVariable Long veicoloId, @RequestParam BigDecimal anticipo, @RequestParam boolean pagato) {
        return acquirenteService.creaOrdine(veicoloId, anticipo, pagato);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo permette di effettuare un acquisto di un veicolo disponibile")
    @PostMapping("/creazioneacquisto")
    public void creaAcquisto() {
        // veicolo deve essere disponibile
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo permette di modificare i dati dell'utente")
    @PatchMapping("/modificautente")
    public void modificaDatiUtente() {

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "ORDINE NON TROVATO CON QUELL'ID")
    })
    @Operation(summary = "Questo metodo permette di cancellare un ordine tramite id")
    @DeleteMapping("/cancellaordine/{id}")
    public void cancellaOrdineId() {

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOLEGGIO NON TROVATO CON QUELL'ID")
    })
    @Operation(summary = "Questo metodo permette di cancellare un noleggio tramite id")
    @DeleteMapping("/cancellanoleggio/{id}")
    public void cancellaNoleggioId() {

    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "PASSWORD SBAGLIATA")
    })
    @Operation(summary = "Questo metodo permette all'utente di cancellare il proprio account")
    @DeleteMapping("/cancellautente")
    public void cancellaUtente() {

    }
}
