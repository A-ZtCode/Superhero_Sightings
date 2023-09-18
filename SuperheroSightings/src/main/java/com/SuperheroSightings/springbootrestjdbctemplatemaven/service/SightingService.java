package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service interface for managing operations related to sightings.
 *
 * This interface defines the core business operations associated with the sightings of superheroes.
 * It provides methods for adding, retrieving, updating, and deleting sightings, as well as retrieving
 * all sightings.
 *
 * Implementing classes will provide concrete implementations of these methods, ensuring the correct
 * business logic and operations are applied consistently across sighting-related functionalities.
 */

@Service
public interface SightingService {

    /**
     * Adds a new sighting.
     *
     * @param sighting the sighting object to be added.
     * @return the added sighting, typically with a generated ID.
     */
    Sighting addSighting(Sighting sighting);

    /**
     * Retrieves a specific sighting by its ID.
     *
     * @param id the ID of the sighting to retrieve.
     * @return the corresponding sighting object, or null if not found.
     */
    Sighting getSightingById(int id);

    /**
     * Updates an existing sighting.
     *
     * @param sighting the sighting object with updated information.
     * @return the updated sighting.
     */
    Sighting updateSighting(Sighting sighting);

    /**
     * Deletes a specific sighting by its ID.
     *
     * @param id the ID of the sighting to delete.
     */
    void deleteSighting(int id);

    /**
     * Retrieves all sightings.
     *
     * @return a list containing all sightings.
     */
    List<Sighting> getAllSightings();
}
