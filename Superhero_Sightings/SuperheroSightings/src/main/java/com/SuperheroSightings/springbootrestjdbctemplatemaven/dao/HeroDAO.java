package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;

import java.util.List;

/**
 * Data Access Object (DAO) interface for managing operations related to Heroes.
 *
 * This interface defines the standard operations to be performed on a Hero object.
 * Implementations of this interface will provide concrete logic for these operations,  interacting
 * with the database.
 */
public interface HeroDAO {

    /**
     * Creates a new Hero record.
     *
     * @param hero Hero object containing details of the hero to be created.
     * @return Created hero with the assigned ID.
     */
    Hero addHero(Hero hero);

    /**
     * Retrieves details of a specific hero identified by its ID.
     *
     * @param id ID of the hero to be retrieved.
     * @return Hero object with the specified ID.
     */
    Hero getHeroById(int id);

    /**
     * Retrieves a list of all heroes in the system.
     *
     * @return List of Hero objects.
     */
    List<Hero> getAllHeroes();

    /**
     * Updates details of a specific hero.
     *
     * @param hero Hero object containing updated details.
     */
    void updateHero(Hero hero);

    /**
     * Deletes a specific hero identified by its ID.
     *
     * @param id ID of the hero to be deleted.
     */
    void deleteHeroById(int id);

    /**
     * Retrieves a list of heroes affiliated with a specific organization identified by its ID.
     *
     * @param organizationId ID of the organization.
     * @return List of Hero objects affiliated with the specified organization.
     */
    List<Hero> getHeroesByOrganizationId(int organizationId);

    /**
     * Retrieves a list of heroes sighted at a specific location identified by its ID.
     *
     * @param locationId ID of the location.
     * @return List of Hero objects sighted at the specified location.
     */
    List<Hero> getHeroesByLocationId(int locationId);
}
