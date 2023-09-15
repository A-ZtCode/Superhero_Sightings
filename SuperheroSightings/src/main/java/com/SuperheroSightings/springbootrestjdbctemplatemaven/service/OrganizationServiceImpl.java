package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.OrganizationDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    private final OrganizationDAO organizationDAO;

    @Autowired
    public OrganizationServiceImpl(OrganizationDAO organizationDAO) {
        this.organizationDAO = organizationDAO;
    }

    @Override
    public Organization addOrganization(Organization organization) {
        return organizationDAO.addOrganization(organization);
    }

    @Override
    public Organization getOrganizationById(int id) {
        return organizationDAO.getOrganizationById(id);
    }

    @Override
    public Organization updateOrganization(Organization organization) {
        organizationDAO.updateOrganization(organization);
        return organization;
    }

    @Override
    public void deleteOrganization(int id) {
        organizationDAO.deleteOrganizationById(id);
    }

    @Override
    public List<Organization> getAllOrganizations() {
        return organizationDAO.getAllOrganizations();
    }
}
