package com.develhope.spring.features.amministratore.statisticheIncassi;

import com.develhope.spring.features.noleggio.NoleggioRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;

@Service
public class IncassiService {
    @Autowired
    private NoleggioRepository noleggioRepository;
    @Autowired
    private OrdineAcquistoRepository ordineAcquistoRepository;

    public BigDecimal incassoTotaleRangeTempo(Date data1, Date data2){
        BigDecimal incassiNoleggi = noleggioRepository.incassoTotaleNoleggi(data1, data2);
        BigDecimal incassiOrdiniAcquisti = ordineAcquistoRepository.incassoTotaleOrdiniAcquisti(data1, data2);
        return incassiNoleggi.add(incassiOrdiniAcquisti);
    }
}
