package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;

import java.util.List;

/**
 * Service interface for managing operations related to organizations.
 *
 * This interface defines the contract for the service layer's operations involving
 * organizations. The interface's methods ensure the correct business logic, data validation,
 * and other required operations are applied consistently across organization-related functionalities.
 * Implementing classes should provide the concrete implementation for each method.
 */
public interface OrganizationService {

    /**
     * Adds a new organization.
     *
     * @param organization the organization to be added.
     * @return the newly added organization with its generated ID.
     */
    Organization addOrganization(Organization organization);

    /**
     * Retrieves an organization by its ID.
     *
     * @param id the ID of the organization to retrieve.
     * @return the organization corresponding to the given ID.
     */
    Organization getOrganizationById(int id);

    /**
     * Updates the details of an existing organization.
     *
     * @param organization the organization with updated details.
     * @return the updated organization.
     */
    Organization updateOrganization(Organization organization);

    /**
     * Deletes an organization based on its ID.
     *
     * @param id the ID of the organization to be deleted.
     */
    void deleteOrganization(int id);

    /**
     * Retrieves a list of all organizations.
     *
     * @return a list of all organizations.
     */
    List<Organization> getAllOrganizations();
}
