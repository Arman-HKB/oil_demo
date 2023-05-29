package com.orca.hebergeur;


import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class HebergeurService {

    // New
    private final String APPARIEMENT_URL = "http://localhost:8080/appariement";
    WebClient webClient = WebClient.create(APPARIEMENT_URL);

    private final List<Partie> parties;

    public HebergeurService() {
        parties = initializeParties();
    }

    public List<Partie> initializeParties() {
        List<Partie> parties = new ArrayList<>();

        Partie partie1 = new Partie("ID_PARTIE_1", 0, 1); // 5
        Partie partie2 = new Partie("ID_PARTIE_2", 0, 1); // 3
        Partie partie3 = new Partie("ID_PARTIE_3", 0, 1); // 2
        Partie partie4 = new Partie("ID_PARTIE_4", 0, 1); // 3
        Partie partie5 = new Partie("ID_PARTIE_5", 0, 1);

        parties.add(partie1);
        parties.add(partie2);
        parties.add(partie3);
        parties.add(partie4);
        parties.add(partie5);

        return parties;
    }

    public List<Partie> getParties() {
        return parties;
    }

    // Retourne l'url d'une partie disponible
    public String dispo(String port) {
        List<Partie> partiesDisponibles = parties.stream()
                .filter(Partie::estDisponible)
                .toList();

        if (partiesDisponibles.isEmpty()) {
            return null; // Aucune partie disponible
        }

        // Pour rejoindre une partie disponible aléatoire et pas toujours la meme
        int randomIndex = new Random().nextInt(partiesDisponibles.size());
        Partie partieSelectionnee = partiesDisponibles.get(randomIndex);


        // Pour augmenter le nombre de Joueur de la partie
        partieSelectionnee.rejoindrePartie(port);

        // L'id de la partie
        return partieSelectionnee.getId();
    }

    // New
    /*public Mono<String> demanderDeJouer(String url) {
        System.out.println("---------Hébergeur demande à Joueur de jouer son tour---------");

        WebClient webClientJ = WebClient.create(url);

        // New
        return webClientJ.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/demanderDeJouer")
                        .queryParam("url", url)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }*/

}
