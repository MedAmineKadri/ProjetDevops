package tn.esprit.rh.achat;
import static org.junit.Assert.assertEquals;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.DetailFacture;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;
import tn.esprit.rh.achat.repositories.OperateurRepository;
import tn.esprit.rh.achat.services.FactureServiceImpl;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.Mockito.when;


@SpringBootTest(classes =FactureServiceImplMockTest.class)
@ExtendWith(MockitoExtension.class)
class FactureServiceImplMockTest {


    @InjectMocks
    FactureServiceImpl factureService;
    @Mock
    FactureRepository factureRepository;

    @Mock
    OperateurRepository operateurRepository;



    @Test
    void retrieveAllFactures() {
        when(factureRepository.findAll()).thenReturn(Stream
                .of(new Facture(1L, 10,1000,new Date(),null,null, null, null, null), new Facture(2L, 10,1000,new Date(),null,null, null, null, null))
                .collect(Collectors.toList())
        );
        assertEquals(2, factureService.retrieveAllFactures().size());
    }

    @Test
    void addFacture() {
        //mock detail facture
        DetailFacture detailFacture = new DetailFacture(77835L,65018,1650,33,15,null, null);
        //mock Fourrnisseur


        Facture facture = new Facture(1L, 10,1000,new Date(),null,null, null, null, null);
        when(factureRepository.save(facture)).thenReturn(facture);
        assertEquals(facture, factureService.addFacture(facture));
    }

    @Test
    void cancelFacture() {
        Facture facture = new Facture(1L, 10,1000,new Date(),null,null, null, null, null);
        factureService.cancelFacture(1L);
        assertEquals(null,facture.getArchivee() );
    }

    @Test
    void retrieveFacture() {
        Facture f = new Facture(1L, 10,1000,new Date(),null,null, null, null, null);
        when(factureRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(f));
        Facture f1 = factureService.retrieveFacture((long) 2);
        Assertions.assertNotNull(f1);



    }

}
