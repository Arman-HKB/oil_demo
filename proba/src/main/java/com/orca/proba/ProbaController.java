package com.orca.proba;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/proba")
public class ProbaController {
    @Autowired
    private ProbaService probaService;
    @GetMapping("/aide")
    public ArrayList<Integer> aide() {
        return probaService.aide();
    }

}
