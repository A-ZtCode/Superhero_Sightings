package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;

import java.util.List;

public interface OrganizationService {
    Organization addOrganization(Organization organization);
    Organization getOrganizationById(int id);
    Organization updateOrganization(Organization organization);
    void deleteOrganization(int id);
    List<Organization> getAllOrganizations();
}
