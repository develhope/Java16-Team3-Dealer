package com.develhope.spring.features.amministratore.statisticheIncassi;

import com.develhope.spring.features.noleggio.NoleggioRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRepository;
import com.develhope.spring.features.venditore.VenditoreRepository;
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
    @Autowired
    private VenditoreRepository venditoreRepository;

    public BigDecimal incassoTotaleRangeTempo(Date data1, Date data2){
        BigDecimal incassiNoleggi = noleggioRepository.incassoTotaleNoleggi(data1, data2);
        BigDecimal incassiOrdiniAcquisti = ordineAcquistoRepository.incassoTotaleOrdiniAcquisti(data1, data2);
        return incassiNoleggi.add(incassiOrdiniAcquisti);
    }
    public BigDecimal venditoreIncassiTotaliRangeTempo(Long venditoreId,Date data1, Date data2){
        BigDecimal incassiNoleggi = venditoreRepository.incassiNoleggi(venditoreId, data1, data2);
        BigDecimal incassiOrdiniAcquisti = venditoreRepository.incassiOrdiniAcquisti(venditoreId, data1, data2);
        if(incassiNoleggi == null){
            return incassiOrdiniAcquisti;
        }else if(incassiOrdiniAcquisti == null){
            return incassiNoleggi;
        }else if (incassiNoleggi == null && incassiOrdiniAcquisti == null){
            return BigDecimal.valueOf(0.00);
        }else{
            return incassiNoleggi.add(incassiOrdiniAcquisti);
        }

    }
}
