package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ProduitServiceImplTest {


    @Mock
    StockRepository stockrepo;

    @Autowired
    IProduitService produitService;


    @InjectMocks
    private StockServiceImpl stockService;




    @Test
    public void testAddProduit() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateCreation = dateFormat.parse("12/09/2020");
        Date dateDerniereModification = dateFormat.parse("26/08/2021");
        Produit p = new Produit("azerty","a1aa1",120,dateCreation,dateDerniereModification);
        Produit produit = produitService.addProduit(p);
        System.out.print("produit "+produit);
        assertNotNull(produit.getIdProduit());
        assertNotNull(produit.getCodeProduit());
        assertTrue(produit.getLibelleProduit().length() > 0);
        produitService.deleteProduit(produit.getIdProduit());

    }
    @Test
    public void testRetrieveAllProduits() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateCreation = dateFormat.parse("12/09/2020");
        Date dateDerniereModification = dateFormat.parse("26/08/2021");
        List<Produit> Products = produitService.retrieveAllProduits();
        int expected = Products.size();
        Produit p = new Produit("azerty","a1aa1",120,dateCreation,dateDerniereModification);
        Produit produit = produitService.addProduit(p);
        assertEquals(expected + 1, produitService.retrieveAllProduits().size());
        produitService.deleteProduit(produit.getIdProduit());

    }

    @Test
    public void testDeleteProduit() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateCreation = dateFormat.parse("12/09/2020");
        Date dateDerniereModification = dateFormat.parse("26/08/2021");
        Produit p = new Produit("azerty","a1aa1",120,dateCreation,dateDerniereModification);
        Produit produit = produitService.addProduit(p);
        produitService.deleteProduit(produit.getIdProduit());
        assertNull(produitService.retrieveProduit(produit.getIdProduit()));
    }

    @Test
    public void testassignProduitToStock()  throws ParseException {
        Stock s = new Stock((long)2,"ASN8",20,50);
        Mockito.when(stockrepo.findById((long)2)).thenReturn(Optional.of(s));
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateCreation = dateFormat.parse("12/09/2020");
        Date dateDerniereModification = dateFormat.parse("26/08/2021");
        Produit p = new Produit("azerty","a1aa1",120,dateCreation,dateDerniereModification);
        Produit produit = produitService.addProduit(p);
        produitService.assignProduitToStock(produit.getIdProduit(),stockService.retrieveStock((long)2).getIdStock());
        assertNotNull(produit.getIdProduit());
        produitService.deleteProduit(produit.getIdProduit());

    }

}