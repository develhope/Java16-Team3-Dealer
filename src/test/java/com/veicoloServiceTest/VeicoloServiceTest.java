package com.veicoloServiceTest;

import com.develhope.spring.features.veicolo.StatoVeicolo;
import com.develhope.spring.features.veicolo.Veicolo;
import com.develhope.spring.features.veicolo.VeicoloRepository;
import com.develhope.spring.features.veicolo.VeicoloService;
import jdk.swing.interop.SwingInterOpUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = VeicoloServiceTest.class)
@ExtendWith(MockitoExtension.class)
@TestPropertySource(value = {"classpath:application.yml"})
public class VeicoloServiceTest {
    @Mock
    private VeicoloRepository veicoloRepository;
    @InjectMocks
    private VeicoloService veicoloService;

    @Test
    void testCreaVeicoloOK() {
        Veicolo veicoloWOid = Fixtures.createVeicoloOrdinabileWOid();
        Veicolo veicoloWid = Fixtures.createVeicoloOrdinabileWid();

        when(veicoloRepository.saveAndFlush(veicoloWOid)).thenReturn(veicoloWid);
        Veicolo veicolo = veicoloService.saveVeicolo(veicoloWOid);
        assertThat(veicolo).isNotNull();
        assertThat(veicolo.getAlimentazione()).isEqualTo(veicoloWid.getAlimentazione());
        assertThat(veicolo.getCilindrata()).isEqualTo(veicoloWid.getCilindrata());
        assertThat(veicolo.getStato()).isEqualTo(veicoloWid.getStato());
        assertThat(veicolo.getColore()).isEqualTo(veicoloWid.getColore());
        assertThat(veicolo.isNuovo()).isEqualTo(veicoloWid.isNuovo());
        assertThat(veicolo.getModello()).isEqualTo(veicoloWid.getModello());
        assertThat(veicolo.getOptional()).isEqualTo(veicoloWid.getOptional());
        assertThat(veicolo.getPercentualeSconto()).isEqualTo(veicoloWid.getPercentualeSconto());
        assertThat(veicolo.getTipoCambio()).isEqualTo(veicoloWid.getTipoCambio());
        assertThat(veicolo.getPotenza()).isEqualTo(veicoloWid.getPotenza());
        assertThat(veicolo.getTipo()).isEqualTo(veicoloWid.getTipo());
        assertThat(veicolo.getPrezzo()).isEqualTo(veicoloWid.getPrezzo());
        System.out.println("VEICOLO DA SALVARE:" + veicoloWOid);
        System.out.println("VEICOLO SALVATO:" + veicoloWid);
    }

    @Test
    void testModificaStatoVeicoloOK() {
        Veicolo veicolo = Fixtures.createVeicoloOrdinabileWid();
        System.out.println("VEICOLO:" + veicolo);
        Veicolo veicoloUpdate = Fixtures.creazioneVeicoloModificaStato();
        when(veicoloRepository.findById(veicolo.getVeicolo_id())).thenReturn(Optional.of(veicolo));
        Optional<Veicolo> veicoloResponse = veicoloService.modificaStatoVeicolo(veicolo.getVeicolo_id(), veicoloUpdate.getStato());
        assertThat(veicoloResponse.get().getStato()).isEqualTo(veicoloUpdate.getStato());
        System.out.println("EXPECTED:" + veicoloUpdate);
        System.out.println("RESPONSE:" + veicoloResponse);
    }
    @Test
    void testModificaStatoVeicoloEmpty(){
        Long notPresentId = 55L;
        Veicolo veicoloUpdate = Fixtures.creazioneVeicoloModificaStato();
        when(veicoloRepository.findById(notPresentId)).thenReturn(Optional.empty());
        Optional<Veicolo> veicoloResponse = veicoloService.modificaStatoVeicolo(notPresentId, veicoloUpdate.getStato());
        assertThat(veicoloResponse).isEmpty();
    }
}
