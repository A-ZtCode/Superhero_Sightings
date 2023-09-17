package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.OrganizationDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing operations related to organizations.
 *
 * This class provides the concrete implementation for the OrganizationService interface.
 * It uses the OrganizationDAO for interacting with the data source and ensures that the
 * correct business logic and operations are applied consistently across organization-related functionalities.
 */
@Service
public class OrganizationServiceImpl implements OrganizationService{

    private final OrganizationDAO organizationDAO;

    /**
     * Constructor to inject the OrganizationDAO dependency.
     *
     * @param organizationDAO the data access object for organizations.
     */
    @Autowired
    public OrganizationServiceImpl(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization addOrganization(Organization organization) {
        return organizationDAO.addOrganization(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization getOrganizationById(int id) {
        return organizationDAO.getOrganizationById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Organization updateOrganization(Organization organization) {
        organizationDAO.updateOrganization(organization);
        return organization;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteOrganization(int id) {
        organizationDAO.deleteOrganizationById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDAO.getAllOrganizations();
    }
}
