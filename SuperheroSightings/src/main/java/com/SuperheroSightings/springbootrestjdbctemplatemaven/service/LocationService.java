package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;

import java.util.List;

/**
 * Service interface for managing operations related to locations.
 *
 * This service outlines the standard operations that must be implemented
 * for location management in the application. It provides a contract
 * for CRUD operations on location entities, as well as additional
 * methods to retrieve locations by hero association.
 *
 * Implementing classes will ensure that business rules, data validation,
 * and other operations are applied consistently across location-related
 * functionalities.
 */
public interface LocationService {

    /**
     * Adds a new location to the system.
     *
     * @param location The location object to be added.
     * @return The location object after being persisted, typically with a generated ID.
     */
    Location addLocation(Location location);

    /**
     * Retrieves a specific location by its ID.
     *
     * @param id The ID of the location to retrieve.
     * @return The location object associated with the given ID.
     */
    Location getLocationById(int id);

    /**
     * Updates the details of an existing location.
     *
     * @param location The location object containing updated details.
     * @return The updated location object.
     */
    Location updateLocation(Location location);

    /**
     * Deletes a location from the system by its ID.
     *
     * @param id The ID of the location to delete.
     */
    void deleteLocation(int id);

    /**
     * Retrieves all locations from the system.
     *
     * @return A list of all location objects.
     */
    List<Location> getAllLocations();

    /**
     * Retrieves locations associated with a specific hero.
     *
     * @param heroId The ID of the hero.
     * @return A list of locations where the hero was sighted.
     */
    List<Location> getLocationsByHeroId(int heroId);
}
