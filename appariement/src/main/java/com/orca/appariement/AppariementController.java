package com.orca.appariement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

@RestController
@RequestMapping("/appariement")
public class AppariementController {

    // Injection d'appariementService
    @Autowired
    private AppariementService appariementService;

    // EndPoint GET /rechercher
    @GetMapping("/rechercher")
    public Mono<String> rechercherHebergeur(int port) {
        return appariementService.rechercherHebergeur(port);
    }

    @GetMapping("/aide")
    public Mono<ArrayList<Integer>> aide() {
        return appariementService.aide();
    }

}
