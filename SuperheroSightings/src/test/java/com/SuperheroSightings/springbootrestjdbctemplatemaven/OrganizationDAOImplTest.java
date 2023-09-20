package com.SuperheroSightings.springbootrestjdbctemplatemaven;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.OrganizationDAOImpl;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test class for the OrganizationDAOImpl.
 *
 * This class contains unit tests for the OrganizationDAOImpl using the JUnit 5 framework
 * and Mockito for mocking dependencies. It ensures that the data access object
 * for organizations performs its CRUD operations correctly.
 */
@ExtendWith(MockitoExtension.class)
public class OrganizationDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private OrganizationDAOImpl organizationDAO;

    /**
     * Test for adding an organization.
     */
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

    /**
     * Test for retrieving an organization by its ID.
     */
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

    /**
     * Test for updating an organization's details.
     */
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

    /**
     * Test for deleting an organization by its ID.
     */
    @Test
    public void testDeleteOrganizationById() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);
        organizationDAO.deleteOrganizationById(1);
        // Checking for no errors.
    }

    /**
     * Test for adding an organization with null inputs.
     */
    @Test
    public void testAddOrganizationWithNullInputs() {
        Organization org = new Organization();
        org.setName(null);
        org.setDescription(null);
        org.setAddressContactInfo(null);

        assertThrows(Exception.class, () -> {
            organizationDAO.addOrganization(org);
        });
    }

    /**
     * Test for retrieving an organization by a non-existent ID.
     */
    @Test
    public void testGetOrganizationByNonExistentId() {
        int nonExistentId = 999;
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(nonExistentId)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(Exception.class, () -> {
            organizationDAO.getOrganizationById(nonExistentId);
        });
    }

    /**
     * Test for updating a non-existent organization.
     */
    @Test
    public void testUpdateNonExistentOrganization() {
        Organization org = new Organization();
        org.setId(999); // non-existent ID
        org.setName("X-Men");
        org.setDescription("Mutants");

        when(jdbcTemplate.update(anyString(), any(), any(), anyInt())).thenReturn(0);

        assertThrows(Exception.class, () -> {
            organizationDAO.updateOrganization(org);
        });
    }

    /**
     * Test for deleting a non-existent organization.
     */
    @Test
    public void testDeleteNonExistentOrganization() {
        int nonExistentId = 999;
        when(jdbcTemplate.update(anyString(), eq(nonExistentId))).thenReturn(0);

        assertThrows(Exception.class, () -> {
            organizationDAO.deleteOrganizationById(nonExistentId);
        });
    }

}

