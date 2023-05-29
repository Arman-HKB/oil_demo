package com.orca.joueur;

import java.util.*;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class Joueur {

    // Constructeur du joueur
    public Joueur() {
        this.dices = new int[]{1, 1, 1, 1, 1};
        this.figures = createFiguresTable();

        // New
        this.port = 8081;
    }
    // ----------------------

    // Variables privées
    private int[] dices;
    private ArrayList<Object[]> figures;
    private Boolean minorBonus;
    private Boolean yamsBonus;

    // New
    private int port;
    // -----------------

    // Getter & Setter
    public int[] getDices() {
        return this.dices;
    }
    public void setDices(int[] d) {
        this.dices = d;
    }
    public int getDiceValue(int index) {
        return this.dices[index];
    }
    public ArrayList<Object[]> getFigures() {
        return this.figures;
    }
    public Boolean getMinorBonus() { return this.minorBonus; }
    public void setMinorBonus(Boolean b) { this.minorBonus=b; }
    public Boolean getYamsBonus() { return this.yamsBonus; }
    public void setYamsBonus(Boolean b) { this.yamsBonus=b; }

    // New
    public int getPort() { return this.port; }
    // ---------------

    // Afficher les dés du joueurs
    public void showDices() {
        System.out.println("Dés: "+ Arrays.toString(getDices()));
    }
    // ---------------------------

    // Trouver les dés à relancer aléatoirement
    public int[] findRandomDices() {
        int size = (int) (Math.random() * 5) + 1;
        int[] reroll = new int[size];
        Set<Integer> usedValues = new HashSet<>();
        for (int i = 0; i < size; i++) {
            int value;
            do {
                value = (int) (Math.random() * 5);
            } while (usedValues.contains(value));
            reroll[i] = value;
            usedValues.add(value);
        }
        Arrays.sort(reroll);
        return reroll;
    }
    // ---------------------------

    // Relancer tous les dés
        public int[] rerollDices() {
        System.out.println("Le joueur relance tous ses dés.");
        int[] newRoll = new int[5];
        for (int i = 0; i < 5; i++) {
            if (newRoll[i] < 3) {
                newRoll[i] = (int) (Math.random() * 6) + 1;
            }
        }
        return newRoll;
    }
    // ---------------------

    // Relancer des dés spécifiques
    public int[] rerollDices(int[] dices, int[] indexes) {
        System.out.println("Le joueur relance les dés aux indexes "+ Arrays.toString(indexes));
        for (int i = 0; i < indexes.length; i++) {
            int index = indexes[i];
            dices[index] = (int) (Math.random() * 6) + 1;
        }
        return dices;
    }
    // ---------------------------

    // Créer le tableau de figure du joueur lorsqu'il est instancié
    public ArrayList<Object[]> createFiguresTable() {
        ArrayList<Object[]> figures = new ArrayList<Object[]>();

        String[] available_figures = {"somme1", "somme2", "somme3", "somme4", "somme5", "somme6", "brelan", "carre", "full", "pSuite", "gSuite", "yams", "chance"};
        Object[] element;
        for (int i = 0; i < available_figures.length; i++) {
            element = new Object[]{available_figures[i], 0, false};
            figures.add(element);
        }

        return figures;
    }
    // -----------------------------------------------------------

    // Afficher le tableau de figure du joueur
    public void showFiguresTables() {
        System.out.println("Figures:");
        for (int i = 0; i < getFigures().size(); i++) {
            System.out.println((String) getFigures().get(i)[0]+", "+(int) getFigures().get(i)[1]+", "+(boolean) getFigures().get(i)[2]);
        }
    }
    // --------------------------------------

    // Trouver une figure
    public int checkIfBrelan(int[] dices) {
        int points = 0;
        Arrays.sort(dices);
        for (int i = 0; i < dices.length - 2; i++) {
            if (dices[i] == dices[i+1] && dices[i] == dices[i+2]) {
                points += 3 * dices[i];
                break;
            }
        }
        return points;
    }
    public int checkIfCarre(int[] dices) {
        int points = 0;
        Arrays.sort(dices);
        for (int i = 0; i < dices.length - 3; i++) {
            if (dices[i] == dices[i+1] && dices[i] == dices[i+2] && dices[i] == dices[i+3]) {
                points += 4 * dices[i];
                break;
            }
        }
        return points;
    }
    public int checkIfFull(int[] dices) {
        Arrays.sort(dices);
        if ((dices[0] == dices[1] && dices[0] == dices[2] && dices[3] == dices[4]) || (dices[0] == dices[1] && dices[2] == dices[3] && dices[2] == dices[4])) {
            return 25;
        }
        return 0;
    }
    public int checkIfPetiteSuite(int[] dices) {
        Arrays.sort(dices);
        if ((dices[0] == 1 && dices[1] == 2 && dices[2] == 3 && dices[3] == 4) ||
                (dices[1] == 1 && dices[2] == 2 && dices[3] == 3 && dices[4] == 4) ||
                (dices[0] == 2 && dices[1] == 3 && dices[2] == 4 && dices[3] == 5) ||
                (dices[1] == 2 && dices[2] == 3 && dices[3] == 4 && dices[4] == 5) ||
                (dices[0] == 3 && dices[1] == 4 && dices[2] == 5 && dices[3] == 6) ||
                (dices[1] == 3 && dices[2] == 4 && dices[3] == 5 && dices[4] == 6)) {
            return 30;
        }
        return 0;
    }
    public int checkIfGrandeSuite(int[] dices) {
        Arrays.sort(dices);
        boolean gs = true;
        for (int i = 0; i < dices.length - 1; i++) {
            if (dices[i] != dices[i+1] - 1) {
                gs = false;
                break;
            }
        }
        if (gs) { return 40; }
        return 0;
    }
    public int checkIfYams(int[] dices) {
        boolean y = true;
        for (int i = 1; i < dices.length; i++) {
            if (dices[i] != dices[0]) {
                y = false;
                break;
            }
        }
        if (y) { return 50; }
        return 0;
    }
    public int chance(int[] dices) {
        int points = 0;
        for (int i = 0; i < dices.length; i++) {
            points += dices[i];
        }
        return points;
    }
    public int sommeDes1(int[] dices) {
        int points = 0;
        for (int i = 0; i < dices.length; i++) { if(dices[i]==1) {points++;} }
        return points;
    }
    public int sommeDes2(int[] dices) {
        int points = 0;
        for (int i = 0; i < dices.length; i++) { if(dices[i]==2) {points+=2;} }
        return points;
    }
    public int sommeDes3(int[] dices) {
        int points = 0;
        for (int i = 0; i < dices.length; i++) { if(dices[i]==3) {points+=3;} }
        return points;
    }
    public int sommeDes4(int[] dices) {
        int points = 0;
        for (int i = 0; i < dices.length; i++) { if(dices[i]==4) {points+=4;} }
        return points;
    }
    public int sommeDes5(int[] dices) {
        int points = 0;
        for (int i = 0; i < dices.length; i++) { if(dices[i]==5) {points+=5;} }
        return points;
    }
    public int sommeDes6(int[] dices) {
        int points = 0;
        for (int i = 0; i < dices.length; i++) { if(dices[i]==6) {points+=6;} }
        return points;
    }
    // ------------------

    // Trouver le nombre de figures disponibles
    public int availableFiguresCount() {
        int count = 0;
        for (int i = 0; i < getFigures().size(); i++) {
            if(!(boolean) getFigures().get(i)[2]) { // A UPDATE
                count++;
            }
        }
        return count;
    }
    // ----------------------------------------

    // Barrer une figure
    public void barrerFigure(int i) {
        getFigures().get(i)[1] = -1;
        //getFigures().get(i)[2] = TRUE;
    }
    // -----------------

    // Inscrire un score
    public void inscrireScore(int i, int s) {
        getFigures().get(i)[1] = s;
        //checkMinorBonus();
    }
    // -----------------

    // Total score
    public int getScore(String type) {
        int limit = getFigures().size();
        if(Objects.equals(type, "minor")) { limit = 5; }
        int score = 0;
        for (int i = 0; i < limit; i++) {
            if((int) getFigures().get(i)[1] != -1) {
                score += (int) getFigures().get(i)[1];
            }
        }
        return score;
    }
    // -----------

    // New, a refectoriser je suppose car c'est de la merde :')
    // Trouver les figures disponible
    public int[][] availableFigures(int[] dices) {
        int[][] figures = new int[13][2];

        if ((int) getFigures().get(0)[1] == 0) {figures[0][0]  = sommeDes1(dices);} else {figures[0][0] = (int) getFigures().get(0)[1]; figures[0][1] = 1;}
        if ((int) getFigures().get(1)[1] == 0) {figures[1][0] = sommeDes2(dices);} else {figures[1][0] = (int) getFigures().get(1)[1]; figures[1][1] = 1;}
        if ((int) getFigures().get(2)[1] == 0) {figures[2][0] = sommeDes3(dices);} else {figures[2][0] = (int) getFigures().get(2)[1]; figures[2][1] = 1;}
        if ((int) getFigures().get(3)[1] == 0) {figures[3][0] = sommeDes4(dices);} else {figures[3][0] = (int) getFigures().get(3)[1]; figures[3][1] = 1;}
        if ((int) getFigures().get(4)[1] == 0) {figures[4][0] = sommeDes5(dices);} else {figures[4][0] = (int) getFigures().get(4)[1]; figures[4][1] = 1;}
        if ((int) getFigures().get(5)[1] == 0) {figures[5][0] = sommeDes6(dices);} else {figures[5][0] = (int) getFigures().get(5)[1]; figures[5][1] = 1;}

        if ((int) getFigures().get(6)[1] == 0) {figures[6][0] = checkIfBrelan(dices);} else {figures[6][0] = (int) getFigures().get(6)[1]; figures[6][1] = 1;}
        if ((int) getFigures().get(7)[1] == 0) {figures[7][0] = checkIfCarre(dices);} else {figures[7][0] = (int) getFigures().get(7)[1]; figures[7][1] = 1;}
        if ((int) getFigures().get(8)[1] == 0) {figures[8][0] = checkIfFull(dices);} else {figures[8][0] = (int) getFigures().get(8)[1]; figures[8][1] = 1;}
        if ((int) getFigures().get(9)[1] == 0) {figures[9][0] = checkIfPetiteSuite(dices);} else {figures[9][0] = (int) getFigures().get(9)[1]; figures[9][1] = 1;}
        if ((int) getFigures().get(10)[1] == 0) {figures[10][0] = checkIfGrandeSuite(dices);} else {figures[10][0] = (int) getFigures().get(10)[1]; figures[10][1] = 1;}
        if ((int) getFigures().get(11)[1] == 0) {figures[11][0] = checkIfYams(dices);} else {figures[11][0] = (int) getFigures().get(11)[1]; figures[11][1] = 1;}
        if ((int) getFigures().get(12)[1] == 0) {figures[12][0] = chance(dices);} else {figures[12][0] = (int) getFigures().get(12)[1]; figures[12][1] = 1;}

        return figures;
    }
    // -----------------------------
}
