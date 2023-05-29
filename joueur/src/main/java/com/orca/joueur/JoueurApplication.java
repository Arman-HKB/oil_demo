
package com.orca.joueur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JoueurApplication implements CommandLineRunner {
    private boolean inGame = false;
    @Autowired
    private JoueurService joueurService;

    public boolean isInGame() {
        return inGame;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    public static void main(String[] args) {
        SpringApplication.run(JoueurApplication.class, args);
    }

    @Override
    public void run(String[] args)  {

        // Création du joueur
        Joueur him = new Joueur();

        // Le joueur tente de rejoindre une partie
        joueurService.rejoindrePartie(him.getPort()).subscribe(partieId -> {
            if (partieId != null) {
                //System.out.println("Partie trouvée (" + partieId + ")"); // S'affiche après le déroulé de la partie, bizarre
                this.setInGame(true);
            } else {
                System.out.println("Pas de partie disponible");
                this.setInGame(false);
            }
        });
        for (int j = 0; j < 30; j++) {
            try {
                Thread.sleep(1000);
                if (this.isInGame()) {
                    /*joueurService.demanderProba()
                            .subscribe(liste -> {
                                StringBuilder message = new StringBuilder("Proba a dit à Joueur de relancer les dés aux positions : ");
                                for (int i = 0; i < liste.size(); i++) {
                                    if (liste.get(i) == 1) {
                                        message.append(i).append(", ");
                                    }
                                }
                                System.out.println(message.substring(0, message.length() - 2));
                            });
                    break;*/
                }
                else {
                    System.out.println("Recherche en cours...");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
