package com.develhope.spring.features.acquirente;

import com.develhope.spring.features.noleggio.Noleggio;
import com.develhope.spring.features.noleggio.NoleggioRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoService;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import com.develhope.spring.features.ordiniAcquisti.StatoOrdine;
import com.develhope.spring.features.signUpSignIn.IDLogin;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AcquirenteService {
    @Autowired
    private AcquirenteRepository acquirenteRepository;

    @Autowired
    private IDLogin idLogin;

    public Acquirente modificaDati (Acquirente acquirente){
        Acquirente acquirenteId = acquirenteRepository.findById(idLogin.getId()).get();
        if(acquirenteRepository.findById(idLogin.getId()).isPresent()){
            acquirenteId.setNome(acquirente.getNome());
            acquirenteId.setCognome(acquirente.getCognome());
            acquirenteId.setEmail(acquirente.getEmail());
            acquirenteId.setPassword(acquirente.getPassword());
            acquirenteId.setTelefono(acquirente.getTelefono());
            return acquirenteId;
        }
        return null;
    }




}
