package com.veicoloServiceTest;

import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Tipo;
import com.develhope.spring.features.veicolo.Veicolo;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Fixtures {
    public static Veicolo createVeicoloOrdinabileWid(){
        Veicolo veicoloOrdinabile = new Veicolo();
        veicoloOrdinabile.setVeicolo_id(1L);
        veicoloOrdinabile.setAlimentazione("OR-alimentazioneTest");
        veicoloOrdinabile.setCilindrata(10L);
        veicoloOrdinabile.setStato(StatoVeicolo.ORDINABILE);
        veicoloOrdinabile.setColore("OR-coloreTest");
        veicoloOrdinabile.setAnnoImmatricolazione(Date.valueOf(LocalDate.of(2024,01,01)));
        veicoloOrdinabile.setNuovo(true);
        veicoloOrdinabile.setModello("OR-modelloTest");
        veicoloOrdinabile.setOptional("OR-optionalTest");
        veicoloOrdinabile.setPercentualeSconto(0);
        veicoloOrdinabile.setTipoCambio("OR-cambioTest");
        veicoloOrdinabile.setPotenza(10);
        veicoloOrdinabile.setTipo(Tipo.AUTO);
        veicoloOrdinabile.setPrezzo(BigDecimal.valueOf(10000));

        return veicoloOrdinabile;
    }
    public static Veicolo createVeicoloOrdinabileWOid(){
        Veicolo veicoloOrdinabile = new Veicolo();
        veicoloOrdinabile.setAlimentazione("OR-alimentazioneTest");
        veicoloOrdinabile.setCilindrata(10L);
        veicoloOrdinabile.setStato(StatoVeicolo.ORDINABILE);
        veicoloOrdinabile.setColore("OR-coloreTest");
        veicoloOrdinabile.setAnnoImmatricolazione(Date.valueOf(LocalDate.of(2024,01,01)));
        veicoloOrdinabile.setNuovo(true);
        veicoloOrdinabile.setModello("OR-modelloTest");
        veicoloOrdinabile.setOptional("OR-optionalTest");
        veicoloOrdinabile.setPercentualeSconto(0);
        veicoloOrdinabile.setTipoCambio("OR-cambioTest");
        veicoloOrdinabile.setPotenza(10);
        veicoloOrdinabile.setTipo(Tipo.AUTO);
        veicoloOrdinabile.setPrezzo(BigDecimal.valueOf(10000));

        return veicoloOrdinabile;
    }
    public static Veicolo creazioneVeicoloModificaStato(){
        Veicolo veicolo = new Veicolo();
        veicolo.setAlimentazione("OR-alimentazioneTest");
        veicolo.setCilindrata(10L);
        veicolo.setStato(StatoVeicolo.NON_DISPONIBILE);
        veicolo.setColore("OR-coloreTest");
        veicolo.setAnnoImmatricolazione(Date.valueOf(LocalDate.of(2024,01,01)));
        veicolo.setNuovo(true);
        veicolo.setModello("OR-modelloTest");
        veicolo.setOptional("OR-optionalTest");
        veicolo.setPercentualeSconto(0);
        veicolo.setTipoCambio("OR-cambioTest");
        veicolo.setPotenza(10);
        veicolo.setTipo(Tipo.AUTO);
        veicolo.setPrezzo(BigDecimal.valueOf(10000));
        return veicolo;
    }

}
