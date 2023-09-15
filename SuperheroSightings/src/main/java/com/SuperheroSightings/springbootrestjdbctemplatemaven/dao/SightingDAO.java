package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;

import java.time.LocalDate;
import java.util.List;

/**
 * Data Access Object (DAO) interface for the Sighting entity.
 *
 * This interface defines the necessary CRUD (Create, Read, Update, Delete) operations
 * and additional querying functionalities for managing Sightings in the application.
 */
public interface SightingDAO {

    /**
     * Adds a new sighting to the data store.
     *
     * @param sighting The sighting details to be added.
     * @return The added sighting with its generated ID.
     */
    Sighting addSighting(Sighting sighting);

    /**
     * Retrieves a specific sighting by its ID.
     *
     * @param id The ID of the sighting to be retrieved.
     * @return The found sighting.
     */
    Sighting getSightingById(int id);

    /**
     * Retrieves all sightings from the data store.
     *
     * @return List of all sightings.
     */
    List<Sighting> getAllSightings();

    /**
     * Updates the details of an existing sighting in the data store.
     *
     * @param sighting The updated sighting details.
     */
    void updateSighting(Sighting sighting);

    /**
     * Deletes a sighting by its ID.
     *
     * @param id The ID of the sighting to be deleted.
     * @return Number of rows affected by the delete operation.
     */
    int deleteSightingById(int id);

    /**
     * Retrieves sightings based on the date they were recorded.
     *
     * @param date The date to filter sightings by.
     * @return List of sightings recorded on the specified date.
     */
    List<Sighting> getSightingsByDate(LocalDate date);

    /**
     * Retrieves sightings associated with a specific location.
     *
     * @param locationId The ID of the location.
     * @return List of sightings at the specified location.
     */
    List<Sighting> getSightingsByLocationId(int locationId);

    /**
     * Retrieves sightings of a specific hero.
     *
     * @param heroId The ID of the hero.
     * @return List of sightings of the specified hero.
     */
    List<Sighting> getSightingsByHeroId(int heroId);
}
