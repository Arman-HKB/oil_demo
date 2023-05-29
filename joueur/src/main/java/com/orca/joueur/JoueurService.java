package com.orca.joueur;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class JoueurService {

    private final String APPARIEMENT_URL = "http://localhost:8080/appariement";

    WebClient webClient = WebClient.create(APPARIEMENT_URL);

    // J'en ai plus rien à foutre
    private final String HEBERGEUR_URL = "http://localhost:8083/hebergeur";
    WebClient webClientH = WebClient.create(HEBERGEUR_URL);

    // New
    //private final String HEBERGEUR_URL = "http://localhost:";
    private final Joueur him;
    public JoueurService() {
        him = new Joueur();
    }


    //Effectue un GET sur l'url APPARIEMENT_URL/rechercher
    public Mono<String> rejoindrePartie(int port) {
        System.out.println("---------Joueur demande à rejoindre une partie---------");

        // New
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/rechercher")
                        .queryParam("port", port)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<ArrayList<Integer>> demanderProba() {
        System.out.println("---------Joueur demande à Proba le meilleur coup à jouer---------");
        return webClient.get()
                .uri("/aide")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

    public String jouerSonTour() {
        System.out.println("---------Joueur joue son tour---------");

        StringBuilder actions = new StringBuilder();
        int[][] figures;
        ArrayList<Integer> finguresIndex = new ArrayList<>();
        ArrayList<Integer> videIndex = new ArrayList<>();

        int[] rIndex;
        int r;

        for (int lancer = 0; lancer < 3; lancer++) {
            if(lancer > 0) {
                r = (int) (Math.random() * 2);
                if(r == 1) {
                    him.setDices(him.rerollDices());
                    actions.append("Il lance ses 5 dés et obtient : ").append(Arrays.toString(him.getDices())).append("|");
                } else {
                    rIndex = him.findRandomDices();
                    him.setDices(him.rerollDices(him.getDices(), rIndex));
                    actions.append("Il lance certains dés et obtient : ").append(Arrays.toString(him.getDices())).append("|");
                }
            } else {
                him.setDices(him.rerollDices());
                actions.append("Il lance ses 5 dés et obtient : ").append(Arrays.toString(him.getDices())).append("|");
            }
            //actions.append("Il lance ses 5 dés et obtient : ").append(Arrays.toString(him.getDices())).append("|");

            figures = him.availableFigures(him.getDices());
            System.out.println(Arrays.toString(figures));
            for (int f = 0; f < figures.length; f++) {
                if(Integer.parseInt(String.valueOf(figures[f][0])) > 0 && Integer.parseInt(String.valueOf(figures[f][1])) != 1) {
                    finguresIndex.add(f);
                }
                if(figures[f][0] == 0 && figures[f][1] != 1) {
                    videIndex.add(f);
                }
            }
            actions.append("Il trouve : ").append(finguresIndex.size()).append(" figure(s) |");

            // Choix simple
            if(finguresIndex.size() > 0) {
                r = (int) (Math.random() * 2);  // 0 ou 1
                if(r == 1 || lancer == 2) {
                    r = (int) (Math.random() * finguresIndex.size());
                    actions.append("Il décide d'inscrire un score pour une figure, ").append(figures[finguresIndex.get(r)][0]).append(" pts |");
                    him.inscrireScore(finguresIndex.get(r), Integer.parseInt(String.valueOf(figures[finguresIndex.get(r)][0])));
                    break;
                }
            }
            if(finguresIndex.size() == 0 && lancer == 2) {
                //if(videIndex.size() != 12) {
                    r = (int) (Math.random() * videIndex.size());
                    actions.append("Il décide de barrer une figure |");
                    him.barrerFigure(videIndex.get(r));
                    break;
                //}
            }

            //him.showFiguresTables();
            finguresIndex.clear();
            videIndex.clear();
        }

        return actions.toString();
    }

    public int obtenirScore() {
        return him.getScore("all");
    }

}
