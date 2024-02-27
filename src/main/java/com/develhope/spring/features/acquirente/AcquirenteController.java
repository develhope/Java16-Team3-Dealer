package com.develhope.spring.features.acquirente;

import com.develhope.spring.features.noleggio.Noleggio;
import com.develhope.spring.features.noleggio.NoleggioService;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoService;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Tipo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/autosalone/cliente")
public class AcquirenteController {
    @Autowired
    private AcquirenteService acquirenteService;
    @Autowired
    private NoleggioService noleggioService;
    @Autowired
    private OrdineAcquistoService ordineAcquistoService;
    @Autowired
    private VeicoloService veicoloService;


    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base al modello")
    @GetMapping("/ricercaModello")
    public List<Veicolo> findByModello(@RequestParam String modello) {
        return veicoloService.findByModello(modello);
    }

    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base alla cilindrata")
    @GetMapping("/ricercaCilindrata")
    public List<Veicolo> findByCilindrata(@RequestParam long cilindrata) {
        return veicoloService.findByCilindrata(cilindrata);
    }

    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base al colore")
    @GetMapping("/ricercaColore")
    public List<Veicolo> findByColore(@RequestParam String colore) {
        return veicoloService.findByColore(colore);
    }

    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base alla potenza")
    @GetMapping("/ricercaPotenza")
    public List<Veicolo> findByPotenza(int potenza) {
        return veicoloService.findByPotenza(potenza);
    }
    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base al tipo di cambio")
    @GetMapping("/ricercaTipoCambio")
    public List<Veicolo> findByCambio(String tipoCambio){
        return veicoloService.findByTipoCambio(tipoCambio);
    }
    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base all'alimentazione'")
    @GetMapping("/ricercaAlimentazione")
    public List<Veicolo> findByAlimentazione(String alimentazione){
        return veicoloService.findByAlimentazione(alimentazione);
    }
    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base al prezzo")
    @GetMapping("/ricercaPrezzo")
    public List<Veicolo> findByPrezzo(BigDecimal prezzo){
        return veicoloService.findByPrezzo(prezzo);
    }
    @Operation(summary = "Questo metodo restituisce una lista di veicoli nuovi oppure usati,a seconda della richiesta")
    @GetMapping("/ricercaNuovo")
    public List<Veicolo> findByNuovo(boolean nuovo){
        return veicoloService.findByNuovo(nuovo);
    }
    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base al TIPO")

    @GetMapping("/ricercaTipo")
    public List<Veicolo> findByTipo(Tipo tipo){
        return veicoloService.findByTipo(tipo);
    }
    @Operation(summary = "Questo metodo restituisce una lista di veicoli in base allo STATO")
    @GetMapping("/ricercaStato")
    public List<Veicolo> findByStato(StatoVeicolo stato){
        return veicoloService.findByStato(stato);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo restituisce gli ordini fatti dall'utente")
    @GetMapping("/getOrdini")
    public List<OrdineAcquisto> getOrders() {
        return ordineAcquistoService.findAllOrders();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo restituisce i noleggi fatti dall'utente")
    @GetMapping("/getNoleggi")
    public List<Noleggio> getNoleggi() {
        return noleggioService.findAllRentals();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST")
    })
    @Operation(summary = "Questo metodo restituisce gli acquisti fatti dall'utente")
    @GetMapping("/getAcquisti")
    public List<OrdineAcquisto> getAcquisti() {
        return ordineAcquistoService.findAllOrders();
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "VEICOLO NON TROVATO CON QUELL'ID")
    })
    @Operation(summary = "Questo metodo restituisce i dettagli di un veicolo specifico per id")
    @GetMapping("/getVeicolo/{id}")
    public Optional<Veicolo> getVeicoloId(@PathVariable Long id) {
        return veicoloService.findById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "510", description = "VEICOLO NON PRESENTE"),
            @ApiResponse(responseCode = "511", description = "VEICOLO NON DISPONIBILE"),
            @ApiResponse(responseCode = "512", description = "ACQUIRENTE NON PRESENTE"),
            @ApiResponse(responseCode = "513", description = "VENDITORE NON PRESENTE")
    })
    @Operation(summary = "Questo metodo permette di effettuare un noleggio")
    @PostMapping("/veicolo/creaNoleggio/{veicoloId}")
    public ResponseEntity<?> creaNoleggio(@PathVariable Long veicoloId,@RequestParam Long utenteId, @RequestParam Long venditoreId, @RequestParam boolean pagato, @RequestParam int giorni) {
        Either<Error,Noleggio> result = noleggioService.creaNoleggio(veicoloId, utenteId, venditoreId, pagato, giorni);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }else{
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
    @PostMapping("/veicolo/creaOrdine/{veicoloId}")
    public ResponseEntity<?> creaOrdine(@PathVariable Long veicoloId,@RequestParam Long acquirenteId,@RequestParam Long venditoreId, @RequestParam BigDecimal anticipo, @RequestParam boolean pagato) {
        Either<Error,OrdineAcquisto> result = ordineAcquistoService.creaOrdine(veicoloId,acquirenteId,venditoreId, anticipo, pagato);
        if(result.isLeft()){
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        }else{
            return ResponseEntity.ok(result.right());
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "406", description = "VEICOLO NON DISPONIBILE"),
            @ApiResponse(responseCode = "404", description = "VEICOLO NON PRESENTE")
    })
    @Operation(summary = "Questo metodo permette di effettuare un acquisto di un veicolo disponibile")
    @PostMapping("/veicolo/creaAcquisto/{veicoloId}")
    public ResponseEntity creaAcquisto(@PathVariable Long veicoloId,@RequestParam Long acquirenteId,@RequestParam Long venditoreId, @RequestParam BigDecimal anticipo, @RequestParam boolean pagato) {
        return ordineAcquistoService.creaAcquisto(veicoloId,acquirenteId,venditoreId, anticipo, pagato);
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
    public void cancellaOrdineId(@PathVariable long id) {
        ordineAcquistoService.deleteOrderById(id);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "NOLEGGIO NON TROVATO CON QUELL'ID")
    })
    @Operation(summary = "Questo metodo permette di cancellare un noleggio tramite id")
    @DeleteMapping("/cancellanoleggio/{id}")
    public void cancellaNoleggioId(@PathVariable long id) {
        noleggioService.deleteRentalById(id);
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
