package com.develhope.spring.features.signUpSignIn;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.amministratore.Amministratore;
import com.develhope.spring.features.venditore.Venditore;
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

}
