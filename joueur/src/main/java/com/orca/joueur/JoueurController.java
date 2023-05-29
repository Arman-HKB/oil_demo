package com.orca.joueur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/joueur")
public class JoueurController {
    @GetMapping("/")
    public String hello() {
        return "Joueur is running";
    }

    // New
    @Autowired
    private JoueurService joueurService;
    @GetMapping("/jouerSonTour")
    public String jouerSonTour() {
        return joueurService.jouerSonTour();
    }

    @GetMapping("/obtenirScore")
    public int obtenirScore() {
        return joueurService.obtenirScore();
    }

}
