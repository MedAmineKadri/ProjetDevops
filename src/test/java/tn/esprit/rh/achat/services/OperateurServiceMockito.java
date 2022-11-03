package tn.esprit.rh.achat.services;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
 class OperateurServiceMockito {

    @Mock
    OperateurRepository operateurRepositoryMock;
    @InjectMocks
    OperateurServiceImpl operateurService;


    Operateur op = Operateur.builder().nom("Nakbi").prenom("Yassine").password("root").build();
    List<Operateur> listOperateurs = new ArrayList<Operateur>(){
        {
            add(Operateur.builder().nom("Nakbi").prenom("foulen").password("root").build());
            add(Operateur.builder().nom("Nakbi").prenom("foulena").password("root").build());
            add(Operateur.builder().nom("Nakbi").prenom("zayd").password("root").build());
            add(Operateur.builder().nom("Nakbi").prenom("amr").password("root").build());

        }

    };

    @Test
    void testretrieveOperateur(){
        Mockito.when(operateurRepositoryMock.findById(Mockito.anyLong())).thenReturn(Optional.of(op)); //find all
        Operateur op1 = operateurService.retrieveOperateur(2L);
        Assertions.assertNotNull(op1);

    }
    @Test
    void testaddOperateur() {
        Mockito.when(operateurRepositoryMock.save(op)).thenReturn(op);
        Operateur op1 = operateurService.addOperateur(op);
        Assertions.assertNotNull(op1);

    }

    @Test
    void testretrieveAllOperateurs() {
        Mockito.when(operateurRepositoryMock.findAll()).thenReturn(listOperateurs);
        List<Operateur> listOp = operateurService.retrieveAllOperateurs();
        Assertions.assertNotNull(listOp);
    }



    @Test
    void tesupdateOperateur() {
        op.setPrenom("Hamdi");
        Mockito.when(operateurRepositoryMock.save(op)).thenReturn(op);
        Operateur op1 = operateurService.updateOperateur(op);
        Assertions.assertEquals(op.getPrenom(),op1.getPrenom());

    }

    @Test
    void testdeleteOperateur() {
        Operateur op2 = Operateur.builder().nom("Miral").prenom("Trifi").password("root").build();
        operateurService.deleteOperateur(op2.getIdOperateur());
        Mockito.verify(operateurRepositoryMock).deleteById(op2.getIdOperateur());

    }


}
