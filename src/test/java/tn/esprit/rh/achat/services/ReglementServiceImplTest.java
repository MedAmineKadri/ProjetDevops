package tn.esprit.rh.achat.services;



import tn.esprit.rh.achat.entities.Reglement;
import tn.esprit.rh.achat.repositories.ReglementRepository;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ReglementServiceImplTest {
    @Mock
    ReglementRepository reglementRepository;
    @InjectMocks
    ReglementServiceImpl reglementService;

    Reglement r = new Reglement( 500, 50,  true,  new Date());
    List<Reglement> ReglementList = new ArrayList<Reglement>(){
        {
            add(new Reglement( 500, 50,  true,  new Date()));
            add(new Reglement( 500, 50,  true,  new Date()));
        }
    };
    float chifreAffaire = 3456;

    @Test
    void testRetrieveAllReglements() {
        Mockito.when(reglementRepository.findAll()).thenReturn(ReglementList);
        List<Reglement> lf = reglementService.retrieveAllReglements();
        assertEquals(2, lf.size());
    }

    @Test
    void testAddReglement() {
        Mockito.when(reglementRepository.save(r)).thenReturn(r);
        Reglement f = reglementService.addReglement(r);

        assertNotNull(f);
    }

    @Test
    void testRetrieveReglement() {
        Mockito.when(reglementRepository.findById(Mockito.anyLong()))
                .thenReturn(Optional.of(r));
        Reglement r = reglementService.retrieveReglement(2L);
        assertNotNull(r);
    }

    @Test
    void testRetrieveReglementByFacture() {
        Mockito.when(reglementRepository.retrieveReglementByFacture(Mockito.anyLong())).thenReturn(ReglementList);
        List<Reglement> r = reglementService.retrieveReglementByFacture(9L);
        assertNotNull(r);
    }

    @Test
    void testGetChiffreAffaireEntreDeuxDate() {
        Date startDate = new Date();
        Date endDate = new Date();
        Mockito.when(reglementRepository.getChiffreAffaireEntreDeuxDate(new Date(),new Date())).thenReturn(chifreAffaire);
        float ch = reglementService.getChiffreAffaireEntreDeuxDate(startDate, endDate);
        assertNotEquals(0 , chifreAffaire);
    }
}
