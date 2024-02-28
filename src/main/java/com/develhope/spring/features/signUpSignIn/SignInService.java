package com.develhope.spring.features.signUpSignIn;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.amministratore.Amministratore;
import com.develhope.spring.features.amministratore.AmministratoreRepository;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignInService {

    @Autowired
    private IDLogin idLogin;

    @Autowired
    private AcquirenteRepository acquirenteRepository;
    @Autowired
    private AmministratoreRepository amministratoreRepository;
    @Autowired
    private VenditoreRepository venditoreRepository;



    public Either<Error,Acquirente> signInAcquirente(LoginCredenziali loginCredenziali){
        Acquirente ok = null;
        for(Acquirente u : acquirenteRepository.findAll()){
            if(u.getEmail().equalsIgnoreCase(loginCredenziali.getEmail())){
                if(u.getPassword().equals(loginCredenziali.getPassword())){
                    ok = u;
                    idLogin.setId(ok.getAcquirente_id());
                    idLogin.setTipoUtente("ACQUIRENTE");
                    return Either.right(u);
                }
            }
        }
        if(ok == null){
            return Either.left(new Error(404,"Utente non trovato - email o password errati"));
        }
        return null;
    }

    public Either<Error,Amministratore> signInAmministratore(LoginCredenziali loginCredenziali){
        Amministratore ok = null;
        for(Amministratore u : amministratoreRepository.findAll()){
            if(u.getEmail().equalsIgnoreCase(loginCredenziali.getEmail())){
                if(u.getPassword().equals(loginCredenziali.getPassword())){
                    ok = u;
                    idLogin.setId(ok.getAmministratore_id());
                    idLogin.setTipoUtente("AMMINISTRATORE");
                    return Either.right(u);
                }
            }
        }
        if(ok == null){
            return Either.left(new Error(404,"Utente non trovato - email o password errati"));
        }
        return null;
    }

    public Either<Error,Venditore> signInVenditore(LoginCredenziali loginCredenziali){
        Venditore ok = null;
        for(Venditore u : venditoreRepository.findAll()){
            if(u.getEmail().equalsIgnoreCase(loginCredenziali.getEmail())){
                if(u.getPassword().equals(loginCredenziali.getPassword())){
                    ok = u;
                    idLogin.setId(ok.getVenditore_id());
                    idLogin.setTipoUtente("VENDITORE");
                    return Either.right(u);
                }
            }
        }
        if(ok == null){
            return Either.left(new Error(404,"Utente non trovato - email o password errati"));
        }
        return null;
    }

}
