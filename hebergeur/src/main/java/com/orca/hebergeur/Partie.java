package com.orca.hebergeur;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Partie {
    private final String id;
    private int nombreJoueurs;
    private final int nombreMaxJoueurs;

    public Partie(String id, int nombreJoueurs, int nombreMaxJoueurs) {
        this.id = id;
        this.nombreJoueurs = nombreJoueurs;
        this.nombreMaxJoueurs = nombreMaxJoueurs;

        // New
        joueursURL = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public int getNombreJoueurs() {
        return nombreJoueurs;
    }

    public void setNombreJoueurs(int nombreJoueurs) {
        this.nombreJoueurs = nombreJoueurs;
    }

    public int getNombreMaxJoueurs() {
        return nombreMaxJoueurs;
    }

    public boolean estDisponible() {
        return nombreJoueurs < nombreMaxJoueurs;
    }

    // New
    public ArrayList<String> joueursURL;

    public void rejoindrePartie(String url) {
        if (nombreJoueurs < nombreMaxJoueurs) {
            nombreJoueurs++;
            System.out.println(id + " : Un joueur vient de rejoindre cette partie (" + nombreJoueurs + "/" + nombreMaxJoueurs+")");

            // New
            this.joueursURL.add(url);
            System.out.println(this.joueursURL);

            if (nombreJoueurs == nombreMaxJoueurs) {
                lancerPartie();
            }
        } else {
            throw new IllegalStateException("La partie est pleine. Impossible de rejoindre.");
        }
    }

    private void lancerPartie() {
        System.out.println(id + " : Le nombre maximum de joueurs est atteint ! Lancement de la partie.");
        jouerPartie();
    }

    private void jouerPartie() {
        System.out.println("Début de la partie : " + id);
        System.out.println("---");

        //WebClient webClientJ;

        Mono<String> actionsDuJoueur;
        ArrayList<String> actionsIntoArray;

        for (int tour = 0; tour < 13; tour++) {
            for (int joueur = 0; joueur < joueursURL.size(); joueur++) {
                WebClient webClientJ = WebClient.create(joueursURL.get(joueur));
                actionsDuJoueur = webClientJ.get()
                        .uri("/jouerSonTour")
                        .retrieve()
                        .bodyToMono(String.class);
                actionsIntoArray = new ArrayList<>(Arrays.asList(Objects.requireNonNull(actionsDuJoueur.block()).split("\\|")));

                for (String item : actionsIntoArray) {
                    System.out.println(item);
                }
                System.out.println("---");
            }
        }
        terminerPartie();
    }

    private void terminerPartie() {
        System.out.println(id + " : La partie est terminé.");
        int score;
        for (int joueur = 0; joueur < joueursURL.size(); joueur++) {
            WebClient webClientJ = WebClient.create(joueursURL.get(joueur));
            score = webClientJ.get()
                    .uri("/obtenirScore")
                    .retrieve()
                    .bodyToMono(Integer.class).block();
            System.out.println("Score du joueur : " + score);
        }
        System.out.println("---");
    }

}

