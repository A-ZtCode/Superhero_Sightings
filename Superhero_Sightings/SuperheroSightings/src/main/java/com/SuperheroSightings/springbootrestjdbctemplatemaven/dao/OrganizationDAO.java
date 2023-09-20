package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;

import java.util.List;

/**
 * Interface for data access operations related to the Organization entity.
 *
 * This interface defines CRUD and other operations for the Organization entity.
 */
public interface OrganizationDAO {

    /**
     * Adds a new organization to the database.
     *
     * @param organization The organization to be added.
     * @return The added organization, including the generated ID.
     */
    Organization addOrganization(Organization organization);

    /**
     * Retrieves an organization from the database based on its ID.
     *
     * @param id The ID of the organization to be retrieved.
     * @return The organization with the given ID, or null if not found.
     */
    Organization getOrganizationById(int id);

    /**
     * Retrieves all organizations from the database.
     *
     * @return A list of all organizations.
     */
    List<Organization> getAllOrganizations();

    /**
     * Updates the details of an existing organization in the database.
     *
     * @param organization The organization with updated details.
     */
    void updateOrganization(Organization organization);

    /**
     * Deletes an organization from the database based on its ID.
     *
     * @param id The ID of the organization to be deleted.
     */
    void deleteOrganizationById(int id);

    /**
     * Retrieves organizations that are associated with a specific hero.
     *
     * @param heroId The ID of the hero.
     * @return A list of organizations associated with the given hero.
     */
    List<Organization> getOrganizationsByHeroId(int heroId);
}
