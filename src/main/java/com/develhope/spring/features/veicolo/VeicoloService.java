package com.develhope.spring.features.veicolo;

import com.develhope.spring.features.shared.Error;
import io.vavr.control.Either;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class VeicoloService {
    @Autowired
    private VeicoloRepository veicoloRepository;

    public Optional<Veicolo> findById(Long id) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (veicoloCheck.isPresent()) {
            return veicoloCheck;
        } else {
            return Optional.empty();
        }
    }

    public Veicolo saveVeicolo(Veicolo veicolo) {
        return veicoloRepository.saveAndFlush(veicolo);
    }

    public Optional<Veicolo> modificaStatoVeicolo(Long id, StatoVeicolo stato) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (veicoloCheck.isPresent()) {
            veicoloCheck.get().setStato(stato);
        } else {
            return Optional.empty();
        }
        return veicoloCheck;
    }
    public Either<Error,Veicolo> modificaVeicolo(Long id, Veicolo veicolo){
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if(veicoloCheck.isEmpty()){
            return Either.left(new Error(510,"veicolo non presente"));
        }else if(veicoloCheck.get().getStato().equals(StatoVeicolo.NON_DISPONIBILE)){
            return Either.left(new Error(514,"veicolo non modificabile"));
        }else{
            veicoloCheck.get().setAlimentazione(veicolo.getAlimentazione());
            veicoloCheck.get().setCilindrata(veicolo.getCilindrata());
            veicoloCheck.get().setStato(veicolo.getStato());
            veicoloCheck.get().setColore(veicolo.getColore());
            veicoloCheck.get().setAnnoImmatricolazione(veicolo.getAnnoImmatricolazione());
            veicoloCheck.get().setNuovo(veicolo.isNuovo());
            veicoloCheck.get().setModello(veicolo.getModello());
            veicoloCheck.get().setOptional(veicolo.getOptional());
            veicoloCheck.get().setPercentualeSconto(veicolo.getPercentualeSconto());
            veicoloCheck.get().setTipoCambio(veicolo.getTipoCambio());
            veicoloCheck.get().setPotenza(veicolo.getPotenza());
            veicoloCheck.get().setTipo(veicolo.getTipo());
            veicoloCheck.get().setPrezzo(veicolo.getPrezzo());
            veicoloRepository.saveAndFlush(veicoloCheck.get());
            return Either.right(veicoloCheck.get());
        }

    }

    public ResponseEntity cancellaVeicoloId(Long id) {
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (veicoloCheck.isPresent() && !(veicoloCheck.get().getStato().equals(StatoVeicolo.NON_DISPONIBILE))) {
            veicoloRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Veicolo Eliminato");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Veicolo Noleggiato o Acquistato");
        }
    }

    public List<Veicolo> findByModello(String modello) {
        return veicoloRepository.findByModello(modello);
    }
    public List<Veicolo> findByCilindrata(long cilindrata){
        return veicoloRepository.findByCilindrata(cilindrata);
    }
    public List<Veicolo> findByColore(String colore){
        return veicoloRepository.findByColore(colore);
    }
    public List<Veicolo> findByPotenza(int potenza){
        return veicoloRepository.findByPotenza(potenza);
    }
    public List<Veicolo> findByTipoCambio(String tipoCambio){
        return veicoloRepository.findByTipoCambio(tipoCambio);
    }
    public List<Veicolo> findByAlimentazione(String alimentazione){
        return veicoloRepository.findByAlimentazione(alimentazione);
    }
    public List<Veicolo> findByPrezzo(BigDecimal prezzo){
        return veicoloRepository.findByPrezzo(prezzo);
    }
    public List<Veicolo> findByNuovo(boolean nuovo){
        return veicoloRepository.findByNuovo(nuovo);
    }
    public List<Veicolo> findByTipo(Tipo tipo){
        return veicoloRepository.findByTipo(tipo);
    }
    public List<Veicolo> findByStato(StatoVeicolo stato){
        return veicoloRepository.findByStato(stato);
    }



}
