package com.ordineServiceTest;


import com.develhope.spring.features.acquirente.AcquirenteRepository;
import com.develhope.spring.features.acquirente.AcquirenteService;
import com.develhope.spring.features.noleggio.Noleggio;
import com.develhope.spring.features.noleggio.NoleggioRepository;
import com.develhope.spring.features.noleggio.NoleggioRichiesta;
import com.develhope.spring.features.noleggio.NoleggioService;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquisto;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRepository;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoRichiesta;
import com.develhope.spring.features.ordiniAcquisti.OrdineAcquistoService;
import com.develhope.spring.features.shared.Error;
import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.veicolo.VeicoloService;
import com.develhope.spring.features.venditore.VenditoreRepository;
import com.develhope.spring.features.venditore.VenditoreService;
import io.vavr.control.Either;
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

@SpringBootTest(classes = OrdineServiceTest.class)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(value = {"classpath:application.yml"})
public class OrdineServiceTest {
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
    private OrdineAcquistoRepository ordineAcquistoRepository;
    @InjectMocks
    private OrdineAcquistoService ordineAcquistoService;




    @Test
    void testCreazioneOrdineOK(){
        OrdineAcquistoRichiesta richiesta = Fixtures.creazioneOrdineRichiestaVeicoloOrdinabile();
        when(veicoloRepository.findById(richiesta.getVeicoloId())).thenReturn(Optional.of(Fixtures.creazioneVeicoloOrdinabileWid()));
        when(acquirenteRepository.findById(richiesta.getAcquirenteId())).thenReturn(Optional.of(Fixtures.creazioneAcquirenteWid()));
        when(venditoreRepository.findById(richiesta.getVenditoreId())).thenReturn(Optional.of(Fixtures.creazioneVenditoreWid()));
        Either<Error,OrdineAcquisto> ordineAcquisto = ordineAcquistoService.creaOrdine(richiesta);

        OrdineAcquisto nuovoOrdineAcquisto = new OrdineAcquisto();
        nuovoOrdineAcquisto.setVeicolo(Fixtures.creazioneVeicoloOrdinabileWid());
        nuovoOrdineAcquisto.setVenditore(Fixtures.creazioneVenditoreWid());
        nuovoOrdineAcquisto.setAcquirente(Fixtures.creazioneAcquirenteWid());
        nuovoOrdineAcquisto.setPagato(richiesta.isPagato());

        assertThat(richiesta.getVeicoloId()).isEqualTo(Fixtures.creazioneVeicoloOrdinabileWid().getVeicolo_id());
        assertThat(richiesta.getVenditoreId()).isEqualTo(Fixtures.creazioneVenditoreWid().getVenditore_id());
        assertThat(richiesta.getAcquirenteId()).isEqualTo(Fixtures.creazioneAcquirenteWid().getAcquirente_id());

        assertThat(ordineAcquisto.get().getVeicolo().getVeicolo_id()).isEqualTo(nuovoOrdineAcquisto.getVeicolo().getVeicolo_id());
        assertThat(ordineAcquisto.get().getVenditore()).isEqualTo(nuovoOrdineAcquisto.getVenditore());
        assertThat(ordineAcquisto.get().getAcquirente()).isEqualTo(nuovoOrdineAcquisto.getAcquirente());
        assertThat(ordineAcquisto.get().isPagato()).isEqualTo(nuovoOrdineAcquisto.isPagato());

        assertThat(ordineAcquisto.get().getVeicolo().getStato()).isEqualTo(StatoVeicolo.NON_DISPONIBILE);

        System.out.println("RICHIESTA:" + richiesta);
        System.out.println("ORDINE:" + ordineAcquisto);
        System.out.println("NUOVO ORDINE:" + nuovoOrdineAcquisto);
    }
    @Test
    void testCreazioneOrdineVeicoloNonPresente(){
        OrdineAcquistoRichiesta richiesta = Fixtures.creazioneOrdineRichiestaVeicoloNonPresente();
        when(veicoloRepository.findById(richiesta.getVeicoloId())).thenReturn(Optional.empty());
        Either<Error,OrdineAcquisto> ordineAcquisto = ordineAcquistoService.creaOrdine(richiesta);
        assertThat(ordineAcquisto).isEqualTo(Either.left(new Error(510, "veicolo non presente")));
    }
}
