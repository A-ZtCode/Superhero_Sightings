package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
import java.util.List;

/**
 * Service interface for managing operations related to heroes.
 *
 * This service provides a layer of abstraction between the controller
 * and the DAO, ensuring that business rules and data validation are
 * applied consistently. It offers CRUD operations for hero entities.
 *
 * Implementations of this interface should ensure that the provided
 * operations meet the specific business requirements of the application.
 *
 */
public interface HeroService {

    /**
     * Adds a new hero to the system.
     *
     * @param hero The hero object to be added.
     * @return The added hero with its generated ID.
     */
    Hero addHero(Hero hero);

    /**
     * Retrieves a hero by its unique ID.
     *
     * @param id The unique identifier of the desired hero.
     * @return The hero object if found; null otherwise.
     */
    Hero getHeroById(int id);

    /**
     * Updates the details of a specific hero.
     *
     * @param hero The hero object with updated data.
     * @return The updated hero object.
     */
    Hero updateHero(Hero hero);

    /**
     * Deletes a hero from the system based on its ID.
     *
     * @param id The unique identifier of the hero to be deleted/removed.
     */
    void deleteHero(int id);

    /**
     * Fetches all the heroes present in the system.
     *
     * @return A list containing all the hero objects.
     */
    List<Hero> getAllHeroes();
}
