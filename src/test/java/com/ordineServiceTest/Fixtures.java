package com.ordineServiceTest;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRichiesta;
import com.develhope.spring.features.ordiniAcquisti.StatoOrdine;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Tipo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.venditore.Venditore;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Fixtures {
    public static Veicolo creazioneVeicoloOrdinabileWid() {
        Veicolo veicolo = new Veicolo();
        veicolo.setVeicolo_id(1L);
        veicolo.setAlimentazione("NO-alimentazioneTest");
        veicolo.setCilindrata(10L);
        veicolo.setStato(StatoVeicolo.ORDINABILE);
        veicolo.setColore("NO-coloreTest");
        veicolo.setAnnoImmatricolazione(null);
        veicolo.setNuovo(true);
        veicolo.setModello("NO-modelloTest");
        veicolo.setOptional("NO-optionalTest");
        veicolo.setPercentualeSconto(0);
        veicolo.setTipoCambio("NO-cambioTest");
        veicolo.setPotenza(10);
        veicolo.setTipo(Tipo.AUTO);
        veicolo.setPrezzo(BigDecimal.valueOf(10000));
        return veicolo;
    }

    public static Veicolo creazioneVeicoloAcquistabileWid() {
        Veicolo veicoloOrdinabile = new Veicolo();
        veicoloOrdinabile.setVeicolo_id(1L);
        veicoloOrdinabile.setAlimentazione("OR-alimentazioneTest");
        veicoloOrdinabile.setCilindrata(10L);
        veicoloOrdinabile.setStato(StatoVeicolo.ACQUISTABILE);
        veicoloOrdinabile.setColore("OR-coloreTest");
        veicoloOrdinabile.setAnnoImmatricolazione(Date.valueOf(LocalDate.of(2024, 01, 01)));
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
    public static Veicolo creazioneVeicoloNonDisponibileWid() {
        Veicolo veicoloOrdinabile = new Veicolo();
        veicoloOrdinabile.setVeicolo_id(1L);
        veicoloOrdinabile.setAlimentazione("OR-alimentazioneTest");
        veicoloOrdinabile.setCilindrata(10L);
        veicoloOrdinabile.setStato(StatoVeicolo.NON_DISPONIBILE);
        veicoloOrdinabile.setColore("OR-coloreTest");
        veicoloOrdinabile.setAnnoImmatricolazione(Date.valueOf(LocalDate.of(2024, 01, 01)));
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
    public static Veicolo creazioneVeicoloDisponibileWid() {
        Veicolo veicoloOrdinabile = new Veicolo();
        veicoloOrdinabile.setVeicolo_id(1L);
        veicoloOrdinabile.setAlimentazione("OR-alimentazioneTest");
        veicoloOrdinabile.setCilindrata(10L);
        veicoloOrdinabile.setStato(StatoVeicolo.DISPONIBILE);
        veicoloOrdinabile.setColore("OR-coloreTest");
        veicoloOrdinabile.setAnnoImmatricolazione(Date.valueOf(LocalDate.of(2024, 01, 01)));
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
    public static Acquirente creazioneAcquirenteWid() {
        Acquirente acquirente = new Acquirente();
        acquirente.setAcquirente_id(1L);
        acquirente.setNome("Mario");
        acquirente.setCognome("Rossi");
        acquirente.setTelefono(123456789);
        acquirente.setEmail("rossi@gmail.com");
        acquirente.setPassword("password");
        return acquirente;
    }

    public static Venditore creazioneVenditoreWid() {
        Venditore venditore = new Venditore();
        venditore.setVenditore_id(1L);
        venditore.setNome("Luigi");
        venditore.setCognome("Verdi");
        venditore.setTelefono(112345678);
        venditore.setEmail("verdi@gmail.com");
        venditore.setPassword("password");
        return venditore;
    }

    public static OrdineAcquistoRichiesta creazioneOrdineRichiestaVeicoloOrdinabile() {
        OrdineAcquistoRichiesta richiesta = new OrdineAcquistoRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloOrdinabileWid().getVeicolo_id());
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setAnticipo(BigDecimal.valueOf(1000));
        richiesta.setPagato(false);
        return richiesta;
    }
    public static OrdineAcquistoRichiesta creazioneOrdineRichiestaVeicoloAcquistabile() {
        OrdineAcquistoRichiesta richiesta = new OrdineAcquistoRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloAcquistabileWid().getVeicolo_id());
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setAnticipo(BigDecimal.valueOf(1000));
        richiesta.setPagato(false);
        return richiesta;
    }
    public static OrdineAcquistoRichiesta creazioneOrdineRichiestaVeicoloNonDisponibile() {
        OrdineAcquistoRichiesta richiesta = new OrdineAcquistoRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloNonDisponibileWid().getVeicolo_id());
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setAnticipo(BigDecimal.valueOf(1000));
        richiesta.setPagato(false);
        return richiesta;
    }
    public static OrdineAcquistoRichiesta creazioneOrdineRichiestaVeicoloDisponibile() {
        OrdineAcquistoRichiesta richiesta = new OrdineAcquistoRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloDisponibileWid().getVeicolo_id());
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setAnticipo(BigDecimal.valueOf(1000));
        richiesta.setPagato(false);
        return richiesta;
    }
    public static OrdineAcquistoRichiesta creazioneOrdineRichiestaAcquirenteNonPresente() {
        OrdineAcquistoRichiesta richiesta = new OrdineAcquistoRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloDisponibileWid().getVeicolo_id());
        richiesta.setAcquirenteId(2L);
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setAnticipo(BigDecimal.valueOf(1000));
        richiesta.setPagato(false);
        return richiesta;
    }
    public static OrdineAcquistoRichiesta creazioneOrdineRichiestaVenditoreNonPresente() {
        OrdineAcquistoRichiesta richiesta = new OrdineAcquistoRichiesta();
        richiesta.setVeicoloId(2L);
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setAnticipo(BigDecimal.valueOf(1000));
        richiesta.setPagato(false);
        return richiesta;
    }
}
