package com.orca.hebergeur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hebergeur")
public class HebergeurController {

    @Autowired
    private HebergeurService hebergeurService;

    // EndPoint GET /dispo
    @GetMapping("/dispo")
    public String dispo(String port) {
        return hebergeurService.dispo(port);
    }

    // New
    @GetMapping("/resumerDuTour")
    public void resumerDuTour(String actions) { hebergeurService.dispo(actions); }
}
