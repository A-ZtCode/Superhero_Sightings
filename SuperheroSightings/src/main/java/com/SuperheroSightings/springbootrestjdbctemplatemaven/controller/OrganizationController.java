package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    // Add a new organization
    // POST http://localhost:8080/organizations
    @PostMapping
    public Organization addOrganization(@RequestBody Organization organization) {
        return organizationService.addOrganization(organization);
    }

    // Retrieve a specific organization by ID
    // GET http://localhost:8080/organizations/{id}
    @GetMapping("/{id}")
    public Organization getOrganization(@PathVariable int id) {
        return organizationService.getOrganizationById(id);
    }

    // Update details of a specific organization by ID
    // PUT http://localhost:8080/organizations/{id}
    @PutMapping("/{id}")
    public Organization updateOrganization(@PathVariable int id, @RequestBody Organization organization) {
        organization.setId(id);
        return organizationService.updateOrganization(organization);
    }

    // Delete a specific organization by ID
    // DELETE http://localhost:8080/organizations/{id}
    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable int id) {
        organizationService.deleteOrganization(id);
    }

    // Retrieve all organizations
    // GET http://localhost:8080/organizations
    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }
}
