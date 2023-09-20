package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.SightingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing CRUD operations related to Sightings.
 *
 * This class provides RESTful API endpoints for client applications to perform operations
 * on the Sighting entity. It supports creating, updating, deleting, and retrieving sighting records.
 */
@RestController
@RequestMapping("/sightings")
public class SightingController {

    private final SightingService sightingService;

    @Autowired
    public SightingController(SightingService sightingService) {
        this.sightingService = sightingService;
    }

    /**
     * Creates a new sighting record.
     *
     * API Endpoint: POST http://localhost:8080/sightings
     * Body: JSON representation of the sighting to create
     *
     * @param sighting Sighting object containing details of the sighting to be created.
     * @return Created sighting with the assigned ID.
     */
    @PostMapping
    public Sighting addSighting(@RequestBody Sighting sighting) {
        return sightingService.addSighting(sighting);
    }

    /**
     * Retrieves details of a specific sighting identified by its ID.
     *
     * API Endpoint: GET http://localhost:8080/sightings/{id}
     *
     * @param id ID of the sighting to be retrieved.
     * @return Sighting object with the specified ID.
     */
    @GetMapping("/{id}")
    public Sighting getSighting(@PathVariable int id) {
        return sightingService.getSightingById(id);
    }

    /**
     * Updates details of a specific sighting identified by its ID.
     *
     * API Endpoint: PUT http://localhost:8080/sightings/{id}
     * Body: JSON representation of the updated sighting details
     *
     * @param id ID of the sighting to be updated.
     * @param sighting Sighting object containing updated details.
     * @return Updated sighting details.
     */
    @PutMapping("/{id}")
    public Sighting updateSighting(@PathVariable int id, @RequestBody Sighting sighting) {
        sighting.setId(id);
        return sightingService.updateSighting(sighting);
    }

    /**
     * Deletes a specific sighting identified by its ID.
     *
     * API Endpoint: DELETE http://localhost:8080/sightings/{id}
     *
     * @param id ID of the sighting to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteSighting(@PathVariable int id) {
        sightingService.deleteSighting(id);
    }

    /**
     * Retrieves a list of all sightings in the system.
     *
     * API Endpoint: GET http://localhost:8080/sightings
     *
     * @return List of Sighting objects.
     */
    @GetMapping
    public List<Sighting> getAllSightings() {
        return sightingService.getAllSightings();
    }
}
