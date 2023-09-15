package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sightings")
public class SightingController {

    private final SightingService sightingService;

    @Autowired
    public SightingController(SightingService sightingService) {
        this.sightingService = sightingService;
    }
    // Add a new sighting
    // POST http://localhost:8080/sightings
    @PostMapping
    public Sighting addSighting(@RequestBody Sighting sighting) {
        return sightingService.addSighting(sighting);
    }

    // Retrieve a specific sighting by ID
    // GET http://localhost:8080/sightings/{id}
    @GetMapping("/{id}")
    public Sighting getSighting(@PathVariable int id) {
        return sightingService.getSightingById(id);
    }

    // Update details of a specific sighting by ID
    // PUT http://localhost:8080/sightings/{id}
    @PutMapping("/{id}")
    public Sighting updateSighting(@PathVariable int id, @RequestBody Sighting sighting) {
        sighting.setId(id);
        return sightingService.updateSighting(sighting);
    }

    // Delete a specific sighting by ID
    // DELETE http://localhost:8080/sightings/{id}
    @DeleteMapping("/{id}")
    public void deleteSighting(@PathVariable int id) {
        sightingService.deleteSighting(id);
    }

    // Retrieve all sightings
    // GET http://localhost:8080/sightings
    @GetMapping
    public List<Sighting> getAllSightings() {
        return sightingService.getAllSightings();
    }
}
