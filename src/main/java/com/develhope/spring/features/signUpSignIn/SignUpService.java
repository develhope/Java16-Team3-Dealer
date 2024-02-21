package com.develhope.spring.features.signUpSignIn;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.amministratore.Amministratore;
import com.develhope.spring.features.amministratore.AmministratoreRepository;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignUpService {
    @Autowired
    private AcquirenteRepository acquirenteRepository;
    @Autowired
    private VenditoreRepository venditoreRepository;
    @Autowired
    private AmministratoreRepository amministratoreRepository;

    private ResponseEntity checkEsistenzaAcquirente(Acquirente acquirente){
        if (acquirenteRepository.findByEmail(acquirente.getEmail()) != null || acquirenteRepository.findByTelefono(acquirente.getTelefono()) != null){
            return ResponseEntity.badRequest().body("Utente già registrato come Acquirente!");
        }else{
            return ResponseEntity.ok().body("Registrazione effettuata con successo!");
        }
    }
    private ResponseEntity checkEsistenzaVenditore(Venditore venditore){
        if (venditoreRepository.findByEmail(venditore.getEmail()) != null || venditoreRepository.findByTelefono(venditore.getTelefono()) != null){
            return ResponseEntity.badRequest().body("Utente già registrato come Venditore!");
        }else{
            return ResponseEntity.ok().body("Registrazione effettuata con successo!");
        }
    }
    private ResponseEntity emailCheckAmministratore(Amministratore admin){
        if (amministratoreRepository.findByEmail(admin.getEmail()) != null){
            return ResponseEntity.badRequest().body("Utente già registrato come Amministratore!");
        }else{
            return ResponseEntity.ok().body("Registrazione effettuata con successo!");
        }
    }
    public ResponseEntity signUpAcquirente(Acquirente nuovoAcquirente) {
        if (checkEsistenzaAcquirente(nuovoAcquirente).equals(ResponseEntity.ok().body("Registrazione effettuata con successo!"))) {
             acquirenteRepository.saveAndFlush(nuovoAcquirente);
             return ResponseEntity.ok().body("Registrazione effettuata con successo!");
        }else {
            return checkEsistenzaAcquirente(nuovoAcquirente);
        }
    }
    public ResponseEntity signUpVenditore(Venditore nuovoVenditore) {
        if (checkEsistenzaVenditore(nuovoVenditore).equals(ResponseEntity.ok().body("Registrazione effettuata con successo!"))) {
            venditoreRepository.saveAndFlush(nuovoVenditore);
            return ResponseEntity.ok().body("Registrazione effettuata con successo!");
        }else {
            return checkEsistenzaVenditore(nuovoVenditore);
        }
    }
    public ResponseEntity signUpAmministratore(Amministratore nuovoAdmin) {
        if (emailCheckAmministratore(nuovoAdmin).equals(ResponseEntity.ok().body("Registrazione effettuata con successo!"))) {
            amministratoreRepository.saveAndFlush(nuovoAdmin);
            return ResponseEntity.ok().body("Registrazione effettuata con successo!");
        }else {
            return emailCheckAmministratore(nuovoAdmin);
        }
    }
}
