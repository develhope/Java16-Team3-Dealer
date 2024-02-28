package com.develhope.spring.features.signUpSignIn;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.amministratore.Amministratore;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.venditore.Venditore;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/autosalone/account")
public class SignUpSignInController {
    @Autowired
    private SignInService signInService;
    @Autowired
    private SignUpService signUpService;

    @PostMapping("/creazione/acquirente")
    public ResponseEntity signUpAcquirente(@RequestBody Acquirente acquirente){
        return signUpService.signUpAcquirente(acquirente);
    }
    @PostMapping("/creazione/venditore")
    public ResponseEntity signUpVenditore(@RequestBody Venditore venditore){
        return signUpService.signUpVenditore(venditore);
    }
    @PostMapping("/creazione/amministratore")
    public ResponseEntity signUpAmministratore(@RequestBody Amministratore amministratore){
        return signUpService.signUpAmministratore(amministratore);
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "UTENTE NON TROVATO/EMAIL O PASSWORD ERRATI")
    })
    @Operation(summary = "Questo metodo permette di effettuare il login per Acquirente")
    @PostMapping("/signin/acquirente")
    public ResponseEntity<?> signinAcquirente(
            @RequestBody LoginCredenziali loginCredenziali
    ){
        Either<Error, Acquirente> result = signInService.signInAcquirente(loginCredenziali);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "UTENTE NON TROVATO/EMAIL O PASSWORD ERRATI")
    })
    @Operation(summary = "Questo metodo permette di effettuare il login per Venditore")
    @PostMapping("/signin/venditore")
    public ResponseEntity<?> signinVenditore(
            @RequestBody LoginCredenziali loginCredenziali
    ){
        Either<Error, Venditore> result = signInService.signInVenditore(loginCredenziali);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "UTENTE NON TROVATO/EMAIL O PASSWORD ERRATI")
    })
    @Operation(summary = "Questo metodo permette di effettuare il login per Amministratore")
    @PostMapping("/signin/amministratore")
    public ResponseEntity<?> signinAmministratore(
            @RequestBody LoginCredenziali loginCredenziali
    ){
        Either<Error, Amministratore> result = signInService.signInAmministratore(loginCredenziali);
        if (result.isLeft()) {
            return ResponseEntity.status(result.getLeft().getCode()).body(result.getLeft().getMessage());
        } else {
            return ResponseEntity.ok(result.right());
        }
    }

}
