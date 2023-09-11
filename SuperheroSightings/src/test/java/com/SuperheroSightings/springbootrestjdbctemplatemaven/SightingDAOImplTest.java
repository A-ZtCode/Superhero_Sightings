package com.SuperheroSightings.springbootrestjdbctemplatemaven;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.SightingDAOImpl;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Hero;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Location;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Sighting;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SightingDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private SightingDAOImpl sightingDAO;

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

    @Test
    public void testDeleteSightingById() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);
        sightingDAO.deleteSightingById(1);
        // Checking for no errors.
    }
}

