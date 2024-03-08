package com.noleggioServiceTest;

import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.noleggio.NoleggioRichiesta;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Tipo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.venditore.Venditore;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;

public class Fixtures {
    public static Veicolo creazioneVeicoloNoleggiabileWid() {
        Veicolo veicolo = new Veicolo();
        veicolo.setVeicolo_id(1L);
        veicolo.setAlimentazione("NO-alimentazioneTest");
        veicolo.setCilindrata(10L);
        veicolo.setStato(StatoVeicolo.DISPONIBILE);
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

    public static Veicolo creazioneVeicoloOrdinabileWid() {
        Veicolo veicoloOrdinabile = new Veicolo();
        veicoloOrdinabile.setVeicolo_id(1L);
        veicoloOrdinabile.setAlimentazione("OR-alimentazioneTest");
        veicoloOrdinabile.setCilindrata(10L);
        veicoloOrdinabile.setStato(StatoVeicolo.ORDINABILE);
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

    public static NoleggioRichiesta creazioneNoleggioRichiesta() {
        NoleggioRichiesta richiesta = new NoleggioRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloNoleggiabileWid().getVeicolo_id());
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setInizioNoleggio(Date.valueOf(LocalDate.now()));
        richiesta.setGiorni(3);
        richiesta.setPagato(true);
        return richiesta;
    }
    public static NoleggioRichiesta creazioneNoleggioRichiestaVeicoloNonPresente() {
        NoleggioRichiesta richiesta = new NoleggioRichiesta();
        richiesta.setVeicoloId(2L);
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setInizioNoleggio(Date.valueOf(LocalDate.now()));
        richiesta.setGiorni(3);
        richiesta.setPagato(true);
        return richiesta;
    }
    public static NoleggioRichiesta creazioneNoleggioRichiestaAcquirenteNonPresente() {
        NoleggioRichiesta richiesta = new NoleggioRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloNoleggiabileWid().getVeicolo_id());
        richiesta.setAcquirenteId(2L);
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setInizioNoleggio(Date.valueOf(LocalDate.now()));
        richiesta.setGiorni(3);
        richiesta.setPagato(true);
        return richiesta;
    }
    public static NoleggioRichiesta creazioneNoleggioRichiestaVenditoreNonPresente() {
        NoleggioRichiesta richiesta = new NoleggioRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloNoleggiabileWid().getVeicolo_id());
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(2L);
        richiesta.setInizioNoleggio(Date.valueOf(LocalDate.now()));
        richiesta.setGiorni(3);
        richiesta.setPagato(true);
        return richiesta;
    }
    public static NoleggioRichiesta creazioneNoleggioRichiestaVeicoloNonDisponibile() {
        NoleggioRichiesta richiesta = new NoleggioRichiesta();
        richiesta.setVeicoloId(creazioneVeicoloOrdinabileWid().getVeicolo_id());
        richiesta.setAcquirenteId(creazioneAcquirenteWid().getAcquirente_id());
        richiesta.setVenditoreId(creazioneVenditoreWid().getVenditore_id());
        richiesta.setInizioNoleggio(Date.valueOf(LocalDate.now()));
        richiesta.setGiorni(3);
        richiesta.setPagato(true);
        return richiesta;
    }

}
