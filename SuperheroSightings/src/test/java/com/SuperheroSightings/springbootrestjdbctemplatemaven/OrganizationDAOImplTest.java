package com.SuperheroSightings.springbootrestjdbctemplatemaven;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.OrganizationDAOImpl;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Organization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OrganizationDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private OrganizationDAOImpl organizationDAO;

    @Test
    public void testAddOrganization() {
        Organization organization = new Organization();
        organization.setName("Justice League");
        organization.setDescription("Group of Superheroes");
        organization.setAddressContactInfo(null);

        when(jdbcTemplate.update(
                "INSERT INTO Organizations(name, description, address_contact_info) VALUES(?,?,?)",
                organization.getName(),
                organization.getDescription(),
                organization.getAddressContactInfo() // This would be null in this case.
        )).thenReturn(1);

        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);

        Organization result = organizationDAO.addOrganization(organization);

        assertEquals(1, result.getId());
    }

    @Test
    public void testGetOrganizationById() {
        Organization organization = new Organization();
        organization.setId(1);
        organization.setName("Justice League");
        organization.setDescription("Group of Superheroes");
        organization.setAddressContactInfo("123 Justice Ave, DC Universe");

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(organization);

        Organization result = organizationDAO.getOrganizationById(1);
        assertEquals(organization, result);
    }

    @Test
    public void testUpdateOrganization() {
        Organization organization = new Organization();
        organization.setId(1);
        organization.setName("Justice League");
        organization.setDescription("Group of Superheroes and Allies");
        organization.setAddressContactInfo("456 Justice Blvd, DC Universe");

        // Stub the jdbcTemplate behavior
        when(jdbcTemplate.update(
                "UPDATE Organizations SET name=?, description=?, address_contact_info=? WHERE organization_id=?",
                organization.getName(),
                organization.getDescription(),
                organization.getAddressContactInfo(),
                organization.getId()
        )).thenReturn(1);

        organizationDAO.updateOrganization(organization);
        // Checking for no errors.
    }

    @Test
    public void testDeleteOrganizationById() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);
        organizationDAO.deleteOrganizationById(1);
        // Checking for no errors.
    }
}

