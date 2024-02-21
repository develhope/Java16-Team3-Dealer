package com.develhope.spring.features.ordiniAcquisti;

import com.develhope.spring.features.ordiniAcquisti.dto.OrdineAcquistoRequest;
import com.develhope.spring.features.ordiniAcquisti.dto.OrdineAcquistoResponse;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdineAcquistoService {
    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;
    @Autowired
    private VeicoloRepository veicoloRepository;

    public ResponseEntity checkVeicolo(Long id){
        Optional<Veicolo> veicoloCheck = veicoloRepository.findById(id);
        if (veicoloCheck.isPresent() && veicoloCheck.get().getStato().equals(StatoVeicolo.ORDINABILE)) {
            return ResponseEntity.status(HttpStatus.OK).body("Veicolo disponibile");
        }else if (veicoloCheck.isPresent() && !(veicoloCheck.get().getStato().equals(StatoVeicolo.ORDINABILE))) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Veicolo non disponibile");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Veicolo non presente");
        }
    }

//    public List<OrdineAcquistoResponse> findAllOrders(OrdineAcquistoRequest request) {
//        OrdineAcquisto ordine = OrdineAcquisto.convertRequest(request);
//
//
//    }
}
