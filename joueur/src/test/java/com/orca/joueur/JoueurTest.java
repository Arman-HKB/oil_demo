package com.orca.joueur;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JoueurTest {
    @Test
    void testFindRandomDices() {
        Joueur j = new Joueur();
        int[] dices = j.findRandomDices();
        // La taille du tableau retourné par FindRandomDices() doit être entre 1 et 5
        assertTrue(dices.length >= 1 && dices.length <= 5);
        // Les élément du tableau retourné par FindRandomDices() doivent être dans l'ordre croissant
        for (int i = 0; i < dices.length - 1; i++) {
            assertTrue(dices[i] <= dices[i+1]);
        }
        // Le tableau retourné par FindRandomDices() ne doit contenir que des chiffres uniques
        Set<Integer> values = new HashSet<>();
        for (int i = 0; i < dices.length; i++) {
            assertTrue(values.add(dices[i]));
        }
        // Le tableau retourné par FindRandomDices() doit contenir des indexes donc entre 0 et 4 pour 5 dés
        for (int i = 0; i < dices.length; i++) {
            assertTrue(dices[i] >= 0 && dices[i] <= 4);
        }
    }

    @Test
    public void testRerollAllDices() {
        Joueur j = new Joueur();
        int[] roll = j.rerollDices();
        // Le tableau de dés n'est pas null
        assertNotNull(roll);
        // La taille du tableau de dés est de 5
        assertEquals(5, roll.length);
        // La valeur des dés dans le tableau est entre 1 et 6
        for (int i = 0; i < 5; i++) {
            assertTrue(roll[i] >= 1 && roll[i] <= 6);
        }
    }

    @Test
    public void testRerollSomeDices() {
        Joueur j = new Joueur();
        int[] dices = {3, 4, 2, 6, 1};
        int[] indexes = {1, 3};
        int[] newDices = j.rerollDices(dices, indexes);
        // Les dés relancé ont changés de valeur
        // Test impossible car on a 1 chance sur 6 d'obtenir la même valeur
        /*for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            assertNotEquals(dices[index], newDices[index]);
        }*/
        // La taille du tableau de dés est de 5
        assertEquals(5, newDices.length);
        // La valeur des dés dans le tableau est entre 1 et 6
        for (int i = 0; i < newDices.length; i++) {
            assertTrue(newDices[i] >= 1 && newDices[i] <= 6);
        }
    }

    // Test des figures
    @Test
    public void testCheckIfBrelan() {
        Joueur j = new Joueur();
        int[] dices1 = {1, 2, 3, 4, 5}; // Pas de brelan
        int[] dices2 = {2, 2, 2, 4, 5}; // Brelan de 2

        assertEquals(0, j.checkIfBrelan(dices1)); // Doit être 0
        assertEquals(6, j.checkIfBrelan(dices2)); // Dit être 6
    }

    @Test
    public void testCheckIfCarre() {
        Joueur j = new Joueur();
        int[] dices1 = {1, 2, 3, 4, 5}; // Pas de carre
        int[] dices2 = {2, 2, 2, 2, 5}; // Carre de 2

        assertEquals(0, j.checkIfCarre(dices1)); // Doit être 0
        assertEquals(8, j.checkIfCarre(dices2)); // Dit être 8
    }

    @Test
    public void testCheckIfFull() {
        Joueur j = new Joueur();
        int[] dices1 = {1, 2, 3, 4, 5}; // Pas de full
        int[] dices2 = {2, 2, 2, 5, 5}; // Brelan de 2 et double 5

        assertEquals(0, j.checkIfFull(dices1)); // Doit être 0
        assertEquals(25, j.checkIfFull(dices2)); // Dit être 25
    }

    @Test
    public void testCheckIfPetiteSuite() {
        Joueur j = new Joueur();
        int[] dices1 = {1, 2, 6, 5, 5}; // Pas de petite suite
        int[] dices2 = {1, 2, 3, 4, 6}; // Petite suite 1,2,3,4

        assertEquals(0, j.checkIfPetiteSuite(dices1)); // Doit être 0
        assertEquals(30, j.checkIfPetiteSuite(dices2)); // Dit être 30
    }

    @Test
    public void testCheckIfGrandeSuite() {
        Joueur j = new Joueur();
        int[] dices1 = {1, 2, 6, 5, 5}; // Pas de grande suite
        int[] dices2 = {1, 2, 3, 4, 5}; // Grande suite 1,2,3,4,5

        assertEquals(0, j.checkIfGrandeSuite(dices1)); // Doit être 0
        assertEquals(40, j.checkIfGrandeSuite(dices2)); // Dit être 40
    }

    @Test
    public void testCheckIfYams() {
        Joueur j = new Joueur();
        int[] dices1 = {1, 2, 6, 5, 5}; // Pas de Yams
        int[] dices2 = {5, 5, 5, 5, 5}; // Yams 5,5,5,5,5

        assertEquals(0, j.checkIfYams(dices1)); // Doit être 0
        assertEquals(50, j.checkIfYams(dices2)); // Dit être 50
    }

    @Test
    public void testChance() {
        Joueur j = new Joueur();
        int[] dices1 = {1, 2, 4, 2, 1};

        assertEquals(10, j.chance(dices1)); // Dit être 10
    }

    @Test
    public void testSommes() {
        Joueur j = new Joueur();
        int[] dices1 = {1, 1, 2, 2, 2};
        int[] dices2 = {3, 3, 3, 4, 4};
        int[] dices3 = {5, 5, 6, 6, 6};

        assertEquals(2, j.sommeDes1(dices1)); // Dit être 2
        assertEquals(6, j.sommeDes2(dices1)); // Dit être 6
        assertEquals(9, j.sommeDes3(dices2)); // Dit être 9
        assertEquals(8, j.sommeDes4(dices2)); // Dit être 8
        assertEquals(10, j.sommeDes5(dices3)); // Dit être 10
        assertEquals(18, j.sommeDes6(dices3)); // Dit être 18
    }

    @Test
    public void testAvailableFiguresCount() {
        Joueur j = new Joueur();
        assertEquals(13, j.availableFiguresCount()); // Dit être 13
    }

    @Test
    public void testBarrerFigure() {
        Joueur j = new Joueur();
        assertEquals(13, j.availableFiguresCount()); // Dit être 13 avant de barrer la figure somme de 5
        j.barrerFigure(4);
        assertEquals(12, j.availableFiguresCount()); // Dit être 12 après avoir barré la figure somme de 5

    }
    @Test
    public void testInscrireScore() {
        Joueur j = new Joueur();
        assertEquals(0, j.getFigures().get(11)[1]); // Dit être 0 avant d'inscrire le score
        j.inscrireScore(11, 50);
        assertEquals(50, j.getFigures().get(11)[1]); // Dit être 50 après avoir inscrit le score

    }

    @Test
    public void testGetScore() {
        Joueur j = new Joueur();
        assertEquals(0, j.getScore("total")); // Dit être 0 avant d'inscrire les scores
        j.inscrireScore(0, 5); // Mineur
        j.inscrireScore(1, 10);
        j.inscrireScore(2, 15);
        j.inscrireScore(3, 20);
        j.inscrireScore(4, 25);
        j.inscrireScore(11, 50); // Majeur
        j.inscrireScore(12, 30);
        assertEquals(155, j.getScore("total")); // Dit être 155 après avoir inscrit les scores

        assertEquals(75, j.getScore("minor")); // Dit être 75 après avoir inscrit les scores

    }
}