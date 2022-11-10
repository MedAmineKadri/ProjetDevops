package tn.esprit.rh.achat.services;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.rh.achat.entities.Facture;

@SpringBootTest
public class FactureServiceImplTest {
    @Autowired
    IFactureService factureService;

    //testing Add method
    @Test
    public void testAddFacture(){
        List<Facture> factures = factureService.retrieveAllFactures();
        int expected = factures.size();
        Facture o = new Facture();
        o.setMontantFacture((float) 12.5);
        o.setMontantRemise((float) 12.3);
        Date m = new Date(2014,02,11);
        o.setDateCreationFacture(m);
        o.setDateDerniereModificationFacture(m);
        Facture savedOperateur= factureService.addFacture(o);
        assertEquals(expected+1, factureService.retrieveAllFactures().size());
        assertNotNull(savedOperateur.getMontantFacture());
        factureService.cancelFacture(savedOperateur.getIdFacture());}

    @Test
    public void testRetrieveFactures() {
        Facture o = new Facture();
        o.setMontantFacture((float) 12.5);
        o.setMontantRemise((float) 12.3);
        Date m = new Date(2014,02,11);
        o.setDateCreationFacture(m);
        o.setDateDerniereModificationFacture(m);
        Facture savedFacture= factureService.addFacture(o);
        Facture getFacture= factureService.retrieveFacture(savedFacture.getIdFacture());
        assertNotNull(savedFacture.getMontantFacture());
        assertNotNull(savedFacture.getMontantRemise());
        assertNotNull(savedFacture.getDateCreationFacture());
        assertNotNull(savedFacture.getDateDerniereModificationFacture());
        assertEquals(savedFacture.getIdFacture(),getFacture.getIdFacture());

        factureService.cancelFacture(savedFacture.getIdFacture());
    }




    //Testing deleteOperateur
    @Test
    public void testDeleteFacture() {
        Facture o = new Facture();
        o.setMontantFacture((float) 12.5);
        o.setMontantRemise((float) 12.3);
        Date m = new Date(2014,02,11);
        o.setDateCreationFacture(m);
        o.setDateDerniereModificationFacture(m);
        Facture savedFacture= factureService.addFacture(o);
        factureService.cancelFacture(savedFacture.getIdFacture());
        assertNotNull(savedFacture.getIdFacture());

    }
}
