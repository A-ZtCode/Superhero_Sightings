package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing CRUD operations related to Organizations.
 *
 * This class provides RESTful API endpoints for client applications to perform operations
 * on the Organization entity. It supports creating, updating, deleting, and retrieving organization records.
 */
@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Creates a new organization record.
     *
     * API Endpoint: POST http://localhost:8080/organizations
     * Body: JSON representation of the organization to create
     *
     * @param organization Organization object containing details of the organization to be created.
     * @return Created organization with the assigned ID.
     */
    @PostMapping
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.addOrganization(organization);
    }

    /**
     * Retrieves details of a specific organization identified by its ID.
     *
     * API Endpoint: GET http://localhost:8080/organizations/{id}
     *
     * @param id ID of the organization to be retrieved.
     * @return Organization object with the specified ID.
     */
    @GetMapping("/{id}")
    public Organization getOrganization(@PathVariable int id) {
        return organizationService.getOrganizationById(id);
    }

    /**
     * Updates details of a specific organization identified by its ID.
     *
     * API Endpoint: PUT http://localhost:8080/organizations/{id}
     * Body: JSON representation of the updated organization details
     *
     * @param id ID of the organization to be updated.
     * @param organization Organization object containing updated details.
     * @return Updated organization details.
     */
    @PutMapping("/{id}")
    public Organization updateOrganization(@PathVariable int id, @RequestBody Organization organization) {
        organization.setId(id);
        return organizationService.updateOrganization(organization);
    }

    /**
     * Deletes a specific organization identified by its ID.
     *
     * API Endpoint: DELETE http://localhost:8080/organizations/{id}
     *
     * @param id ID of the organization to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable int id) {
        organizationService.deleteOrganization(id);
    }

    /**
     * Retrieves a list of all organizations in the system.
     *
     * API Endpoint: GET http://localhost:8080/organizations
     *
     * @return List of Organization objects.
     */
    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }
}
