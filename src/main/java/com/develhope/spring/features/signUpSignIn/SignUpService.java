package com.develhope.spring.features.signUpSignIn;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.amministratore.Amministratore;
import com.develhope.spring.features.amministratore.AmministratoreRepository;
import com.develhope.spring.features.noleggio.Noleggio;
import com.develhope.spring.features.noleggio.NoleggioRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRepository;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SignUpService {
    @Autowired
    private AcquirenteRepository acquirenteRepository;
    @Autowired
    private VenditoreRepository venditoreRepository;
    @Autowired
    private AmministratoreRepository amministratoreRepository;
    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;
    @Autowired
    private NoleggioRepository noleggioRepository;

    @Autowired
    private IDLogin idLogin;

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

    public ResponseEntity<String> deleteUserById (LoginCredenziali loginCredenziali) {
            Acquirente acquirente = acquirenteRepository.findById(idLogin.getId()).get();
            if(loginCredenziali.getPassword().equals(acquirente.getPassword())){
                acquirenteRepository.deleteById(idLogin.getId());
                return ResponseEntity.status(600).body("Acquirente Eliminato Correttamente");
            }
        return ResponseEntity.status(601).body("Password Errata! Reinserire Password");
    }

    public ResponseEntity<String> adminEliminaAcquirente(Long id){
        Optional<Acquirente> acquirente = acquirenteRepository.findById(id);
        List<OrdineAcquisto> ordini = ordineAcquistoRepository.checkOrdiniAcquistiAcquirenteAttivi(id);
        List<Noleggio> noleggi = noleggioRepository.checkNoleggiAcquirenteAttivi(id);
        if(noleggi.isEmpty() && ordini.isEmpty()) {
            if (acquirente.isPresent()) {
                acquirenteRepository.deleteById(id);
                return ResponseEntity.status(600).body("Acquirente Eliminato Correttamente");
            } else {
                return ResponseEntity.status(602).body("Acquirente non trovato");
            }
        }else {
            return ResponseEntity.status(603).body("Noleggi o ordini in corso, impossibile eliminare quest' acquirente al momento");
        }
    }
    public ResponseEntity<String> adminEliminaVenditore(Long id){
        Optional<Venditore> venditore = venditoreRepository.findById(id);
        List<OrdineAcquisto> ordini = ordineAcquistoRepository.checkOrdiniAcquistiVenditoreAttivi(id);
        List<Noleggio> noleggi = noleggioRepository.checkNoleggiVenditoreAttivi(id);
        if(noleggi.isEmpty() && ordini.isEmpty()) {
            if (venditore.isPresent()) {
                venditoreRepository.deleteById(id);
                return ResponseEntity.status(600).body("Venditore Eliminato Correttamente");
            } else {
                return ResponseEntity.status(602).body("Venditore non trovato");
            }
        }else {
            return ResponseEntity.status(603).body("Noleggi o ordini in corso, impossibile eliminare questo Venditore al momento");
        }
    }

}
