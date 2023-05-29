package com.orca.proba;


import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

@Service
public class ProbaService {

    // Retourne une liste de dés à relancer (aleatoire pour l'instant)
    public ArrayList<Integer> aide(){
        System.out.println("---------Joueur m'a demandé le meilleur coup à jouer---------");
        ArrayList<Integer> liste = new ArrayList<>(6);
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            liste.add(random.nextInt(2));
        }
        return liste;
    }
}
