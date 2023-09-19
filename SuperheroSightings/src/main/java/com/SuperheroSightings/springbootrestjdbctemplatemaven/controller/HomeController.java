package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    private final SightingService sightingService;

    @Autowired
    public HomeController(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Sighting> latestSightings = sightingService.getRecentSightings();
        // Print the latestSightings list to the console
        System.out.println("Latest Sightings: " + latestSightings);

        model.addAttribute("sightings", latestSightings);
        return "home";
    }
}
