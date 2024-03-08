package com.noleggioServiceTest;


import com.develhope.spring.features.acquirente.Acquirente;
import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.acquirente.AcquirenteService;
import com.develhope.spring.features.noleggio.Noleggio;
import com.develhope.spring.features.noleggio.NoleggioRepository;
import com.develhope.spring.features.noleggio.NoleggioRichiesta;
import com.develhope.spring.features.noleggio.NoleggioService;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.veicolo.VeicoloService;
import com.develhope.spring.features.venditore.Venditore;
import com.develhope.spring.features.venditore.VenditoreRepository;
import com.develhope.spring.features.venditore.VenditoreService;
import io.vavr.control.Either;
import io.vavr.control.Option;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = NoleggioServiceTest.class)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(value = {"classpath:application.yml"})
public class NoleggioServiceTest {
    @Mock
    private VeicoloRepository veicoloRepository;
    @InjectMocks
    private VeicoloService veicoloService;
    @Mock
    private AcquirenteRepository acquirenteRepository;
    @InjectMocks
    private AcquirenteService acquirenteService;
    @Mock
    private VenditoreRepository venditoreRepository;
    @InjectMocks
    private VenditoreService venditoreService;
    @Mock
    private NoleggioRepository noleggioRepository;
    @InjectMocks
    private NoleggioService noleggioService;




    @Test
    void testCreazioneNoleggioOK(){
        NoleggioRichiesta richiesta = Fixtures.creazioneNoleggioRichiesta();
        when(veicoloRepository.findById(richiesta.getVeicoloId())).thenReturn(Optional.of(Fixtures.creazioneVeicoloNoleggiabileWid()));
        when(acquirenteRepository.findById(richiesta.getAcquirenteId())).thenReturn(Optional.of(Fixtures.creazioneAcquirenteWid()));
        when(venditoreRepository.findById(richiesta.getVenditoreId())).thenReturn(Optional.of(Fixtures.creazioneVenditoreWid()));
        Either<Error,Noleggio> noleggio = noleggioService.creaNoleggio(richiesta);

        Noleggio nuovoNoleggio = new Noleggio();
        nuovoNoleggio.setVeicolo(Fixtures.creazioneVeicoloNoleggiabileWid());
        nuovoNoleggio.setVenditore(Fixtures.creazioneVenditoreWid());
        nuovoNoleggio.setAcquirente(Fixtures.creazioneAcquirenteWid());
        nuovoNoleggio.setPagato(richiesta.isPagato());
        nuovoNoleggio.setGiorni(richiesta.getGiorni());
        nuovoNoleggio.setInizioNoleggio(richiesta.getInizioNoleggio());
        LocalDate nuovaFineNoleggio = richiesta.getInizioNoleggio().toLocalDate().plusDays(richiesta.getGiorni());
        nuovoNoleggio.setFineNoleggio(Date.valueOf(nuovaFineNoleggio));
        nuovoNoleggio.setPrezzoTotale(noleggioService.prezzoTotale(richiesta.getGiorni()));

        assertThat(richiesta.getVeicoloId()).isEqualTo(Fixtures.creazioneVeicoloNoleggiabileWid().getVeicolo_id());
        assertThat(richiesta.getVenditoreId()).isEqualTo(Fixtures.creazioneVenditoreWid().getVenditore_id());
        assertThat(richiesta.getAcquirenteId()).isEqualTo(Fixtures.creazioneAcquirenteWid().getAcquirente_id());

        assertThat(noleggio.get().getVeicolo().getVeicolo_id()).isEqualTo(nuovoNoleggio.getVeicolo().getVeicolo_id());
        assertThat(noleggio.get().getVenditore()).isEqualTo(nuovoNoleggio.getVenditore());
        assertThat(noleggio.get().getAcquirente()).isEqualTo(nuovoNoleggio.getAcquirente());
        assertThat(noleggio.get().isPagato()).isEqualTo(nuovoNoleggio.isPagato());
        assertThat(noleggio.get().getGiorni()).isEqualTo(nuovoNoleggio.getGiorni());
        assertThat(noleggio.get().getInizioNoleggio()).isEqualTo(nuovoNoleggio.getInizioNoleggio());
        assertThat(noleggio.get().getFineNoleggio()).isEqualTo(nuovoNoleggio.getFineNoleggio());
        assertThat(noleggio.get().getPrezzoTotale()).isEqualTo(nuovoNoleggio.getPrezzoTotale());

        assertThat(noleggio.get().getVeicolo().getStato()).isEqualTo(StatoVeicolo.NON_DISPONIBILE);

        System.out.println("RICHIESTA:" + richiesta);
        System.out.println("NOLEGGIO:" + noleggio);
        System.out.println("NUOVO NOLEGGIO:" + nuovoNoleggio);
    }
    @Test
    void testCreazioneNoleggioVeicoloNonPresente(){
        NoleggioRichiesta richiesta = Fixtures.creazioneNoleggioRichiestaVeicoloNonPresente();
        when(veicoloRepository.findById(richiesta.getVeicoloId())).thenReturn(Optional.empty());
        Either<Error,Noleggio> noleggio = noleggioService.creaNoleggio(richiesta);
        assertThat(noleggio).isEqualTo(Either.left(new Error(510, "veicolo non presente")));
    }
    @Test
    void creazioneNoleggioVeicoloNonDisponibile(){
        NoleggioRichiesta richiesta = Fixtures.creazioneNoleggioRichiestaVeicoloNonDisponibile();
        when(veicoloRepository.findById(richiesta.getVeicoloId())).thenReturn(Optional.of(Fixtures.creazioneVeicoloOrdinabileWid()));
        Either<Error,Noleggio> noleggio = noleggioService.creaNoleggio(richiesta);
        assertThat(noleggio).isEqualTo(Either.left(new Error(511, "veicolo non disponibile")));
    }
    @Test
    void testCreazioneNoleggioAcquirenteNonPresente(){
        NoleggioRichiesta richiesta = Fixtures.creazioneNoleggioRichiestaAcquirenteNonPresente();
        when(veicoloRepository.findById(richiesta.getVeicoloId())).thenReturn(Optional.of(Fixtures.creazioneVeicoloNoleggiabileWid()));
        when(acquirenteRepository.findById(richiesta.getAcquirenteId())).thenReturn(Optional.empty());
        Either<Error,Noleggio> noleggio = noleggioService.creaNoleggio(richiesta);
        assertThat(noleggio).isEqualTo(Either.left(new Error(512, "acquirente non presente")));
    }
    @Test
    void testCreazioneNoleggioVenditoreNonPresente(){
        NoleggioRichiesta richiesta = Fixtures.creazioneNoleggioRichiestaVenditoreNonPresente();
        when(veicoloRepository.findById(richiesta.getVeicoloId())).thenReturn(Optional.of(Fixtures.creazioneVeicoloNoleggiabileWid()));
        when(acquirenteRepository.findById(richiesta.getAcquirenteId())).thenReturn(Optional.of(Fixtures.creazioneAcquirenteWid()));
        when(venditoreRepository.findById(richiesta.getVenditoreId())).thenReturn(Optional.empty());
        Either<Error,Noleggio> noleggio = noleggioService.creaNoleggio(richiesta);
        assertThat(noleggio).isEqualTo(Either.left(new Error(513, "venditore non presente")));
    }

}
