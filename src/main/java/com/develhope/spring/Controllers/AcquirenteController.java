package com.develhope.spring.Controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/autosalone?")
public class AcquirenteController {
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo restituisce gli ordini fatti dall'utente")
    @GetMapping("/getordini")
    public void getOrders() {

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
    @PostMapping("/creazioneordine")
    public void creaOrdine() {
        // veicolo deve essere disponibile
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
}
