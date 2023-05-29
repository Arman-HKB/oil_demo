package com.orca.hebergeur;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class HebergeurApplicationTests {

    @Autowired
    private HebergeurService hebergeur;
    @BeforeEach
    public void setUp() {
        // Initialisation du HebergeurService avant chaque test
        hebergeur = new HebergeurService();
        hebergeur.initializeParties();
    }

    /*@Test
    public void testDispoReturnsIdWhenPartieAvailable() {
        String partieId = hebergeur.dispo();
        Assertions.assertNotNull(partieId);
    }*/

    /*@Test
    public void testDispoReturnsNullWhenNoPartieAvailable() {
        // Remplir toutes les parties disponibles pour simuler qu'elles sont complètes
        for (Partie partie : hebergeur.getParties()) {
            partie.setNombreJoueurs(partie.getNombreMaxJoueurs());
        }

        String partieId = hebergeur.dispo();
        Assertions.assertNull(partieId);
    }*/


    /*@Test
    public void testRejoindrePartieIncrementsNumberOfPlayers() {
        Partie partie = new Partie("TEST PARTIE", 0, 5);
        partie.rejoindrePartie();
        int nombreJoueurs = partie.getNombreJoueurs();
        Assertions.assertEquals(1, nombreJoueurs);
    }*/

    /*@Test
    public void testRejoindrePartieThrowsExceptionWhenPartieIsFull() {
        Partie partie = new Partie("TEST PARTIE", 5, 5);
        Assertions.assertThrows(IllegalStateException.class, partie::rejoindrePartie);
    }*/

}


