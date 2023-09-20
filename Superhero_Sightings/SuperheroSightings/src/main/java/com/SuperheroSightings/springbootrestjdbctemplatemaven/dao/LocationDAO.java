package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;

import java.util.List;

/**
 * Interface for Data Access Object (DAO) related to the Location entity.
 *
 * This interface provides a contract for CRUD operations on the Location entity,
 * as well as querying by specific criteria.
 */
public interface LocationDAO {

    /**
     * Adds a new Location to the data store.
     *
     * @param location The Location entity to add.
     * @return The added Location with the generated identifier.
     */
    Location addLocation(Location location);

    /**
     * Retrieves a Location by its unique identifier.
     *
     * @param id The unique identifier of the desired Location.
     * @return The Location entity corresponding to the provided identifier,
     * or null if no such Location exists.
     */
    Location getLocationById(int id);

    /**
     * Retrieves all Location entities from the data store.
     *
     * @return A List of all Location entities.
     */
    List<Location> getAllLocations();

    /**
     * Updates the details of an existing Location in the data store.
     *
     * @param location The Location entity with updated details.
     * The identifier should match the Location to be updated.
     */
    void updateLocation(Location location);

    /**
     * Deletes a Location from the data store based on its unique identifier.
     *
     * @param id The unique identifier of the Location to be deleted.
     */
    void deleteLocationById(int id);

    /**
     * Retrieves all Location entities where a specific Hero has been sighted.
     *
     * @param heroId The unique identifier of the Hero.
     * @return A List of Location entities where the specified Hero has been sighted.
     */
    List<Location> getLocationsByHeroId(int heroId);
}
