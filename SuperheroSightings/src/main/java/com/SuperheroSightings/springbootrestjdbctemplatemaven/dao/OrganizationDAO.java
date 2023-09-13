package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;

import java.util.List;

public interface OrganizationDAO {
    Organization addOrganization(Organization organization);
    Organization getOrganizationById(int id);
    List<Organization> getAllOrganizations();
    void updateOrganization(Organization organization);
    void deleteOrganizationById(int id);
    List<Organization> getOrganizationsByHeroId(int heroId);
}
