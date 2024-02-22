package com.develhope.spring.features.venditore;

import com.develhope.spring.features.noleggio.NoleggioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autosalone/venditore")
public class VenditoreController {
    @Autowired
    private VenditoreService service;

    @Autowired
    private NoleggioService noleggioService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "406", description = "VEICOLO NON DISPONIBILE"),
            @ApiResponse(responseCode = "404", description = "VEICOLO NON PRESENTE")
    })
    @Operation(summary = "Questo metodo permette di effettuare un noleggio")
    @PostMapping("/creaNoleggio/{veicoloId}")
    public ResponseEntity creaNoleggio(@PathVariable Long veicoloId, @RequestParam Long utenteId, @RequestParam Long venditoreId, @RequestParam boolean pagato, @RequestParam int giorni) {
        return noleggioService.creaNoleggio(veicoloId, utenteId, venditoreId, pagato, giorni);
    }
}
