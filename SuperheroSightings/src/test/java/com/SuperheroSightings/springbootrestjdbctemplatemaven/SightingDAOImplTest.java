package com.SuperheroSightings.springbootrestjdbctemplatemaven;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.SightingDAOImpl;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

/**
 * Test class for the SightingDAOImpl.
 *
 * This class contains unit tests for the SightingDAOImpl using the JUnit 5 framework
 * and Mockito for mocking dependencies. It ensures that the data access object
 * for sightings performs its CRUD operations correctly.
 */
@ExtendWith(MockitoExtension.class)
public class SightingDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SightingDAOImpl sightingDAO;

    /**
     * Test for adding a sighting.
     */
    @Test
    public void testAddSighting() {
        Sighting sighting = new Sighting();
        sighting.setHero(new Hero(1, "Superman", "Man of Steel", "Flight"));
        sighting.setLocation(new Location(1, "Gotham City", "Dark and Brooding", "123 Gotham Street", 40.7128, -74.0060));
        sighting.setDate(LocalDate.now());

        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), any())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);

        Sighting result = sightingDAO.addSighting(sighting);

        assertEquals(1, result.getId());
    }

    /**
     * Test for retrieving a sighting by its ID.
     */
    @Test
    public void testGetSightingById() {
        Sighting sighting = new Sighting();
        sighting.setId(1);
        sighting.setHero(new Hero(1, "Superman", "Man of Steel", "Flight"));
        sighting.setLocation(new Location(1, "Gotham City", "Dark and Brooding", "123 Gotham Street", 40.7128, -74.0060));
        sighting.setDate(LocalDate.now());

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(sighting);

        Sighting result = sightingDAO.getSightingById(1);
        assertEquals(sighting, result);
    }

    /**
     * Test for updating a sighting's details.
     */
    @Test
    public void testUpdateSighting() {
        Sighting sighting = new Sighting();
        sighting.setId(1);
        sighting.setHero(new Hero(2, "Batman", "Dark Knight", "Intelligence"));
        sighting.setLocation(new Location(2, "Metropolis", "City of Tomorrow", "456 Metropolis Street", 40.7654, -74.9876));
        sighting.setDate(LocalDate.now().minusDays(1));

        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), any(), anyInt())).thenReturn(1);

        sightingDAO.updateSighting(sighting);
        // Checking for no errors.
    }

    /**
     * Test for deleting a sighting by its ID.
     */
    @Test
    public void testDeleteSightingById() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);
        sightingDAO.deleteSightingById(1);
        // Checking for no errors.
    }

    /**
     * Test for adding a sighting with null inputs.
     */
    @Test
    public void testAddSightingWithNullInputs() {
        Sighting sighting = new Sighting();
        sighting.setHero(null);
        sighting.setLocation(null);
        sighting.setDate(null);

        assertThrows(Exception.class, () -> {
            sightingDAO.addSighting(sighting);
        });
    }

    /**
     * Test for retrieving a sighting by a non-existent ID.
     */
    @Test
    public void testGetSightingByNonExistentId() {
        int nonExistentId = 999;
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(nonExistentId)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(Exception.class, () -> {
            sightingDAO.getSightingById(nonExistentId);
        });
    }

    /**
     * Test for updating a non-existent sighting.
     */
    @Test
    public void testUpdateNonExistentSighting() {
        Sighting sighting = new Sighting();
        sighting.setId(999); // non-existent ID
        sighting.setHero(new Hero(1, "Spiderman", "Wall-crawler", "Web-slinging"));
        sighting.setLocation(new Location(1, "New York City", "Big Apple", "123 NYC Street", 40.7128, -74.0060));
        sighting.setDate(LocalDate.now());

        when(jdbcTemplate.update(anyString(), anyInt(), anyInt(), any(), anyInt())).thenReturn(0);

        assertThrows(Exception.class, () -> {
            sightingDAO.updateSighting(sighting);
        });
    }

    /**
     * Test for deleting a non-existent sighting.
     */
    @Test
    public void testDeleteNonExistentSighting() {
        int nonExistentId = 999; // Some ID that doesn't exist

        // This would mock the behavior to return '0' indicating no records were deleted
        when(jdbcTemplate.update(anyString(), eq(nonExistentId))).thenReturn(0);

        int result = sightingDAO.deleteSightingById(nonExistentId);

        assertEquals(0, result, "No records should be deleted for non-existent ID");
    }

}

