package com.orca.appariement;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@Service
public class AppariementService {

    private static final String HEBERGEUR_URL = "http://localhost:8083/hebergeur";
    private static final String PROBA_URL = "http://localhost:8082/proba";

    // Création du webClient (pour effectuer les requêtes)
    WebClient webClient = WebClient.create(HEBERGEUR_URL);
    WebClient webClient2 = WebClient.create(PROBA_URL);

    // Effectue un GET sur l'url HEBERGEUR_URL/dispo
    public Mono<String> rechercherHebergeur(int port) {
        System.out.println("--------Appariement cherche une partie pour Joueur---------");

        // New
        String j_port = "http://localhost:"+port+"/joueur";
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/dispo")
                        .queryParam("port", j_port)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<ArrayList<Integer>> aide() {

        return webClient2.get()
                .uri("/aide")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<>() {
                });
    }

}