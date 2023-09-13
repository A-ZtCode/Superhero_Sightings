package com.SuperheroSightings.springbootrestjdbctemplatemaven;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.LocationDAOImpl;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
public class LocationDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private LocationDAOImpl locationDAO;

    @Test
    public void testAddLocation() {
        Location location = new Location(1, "Gotham City", "Dark and Brooding", "123 Bat Lane", 40.7128, 74.0060);

        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);

        Location result = locationDAO.addLocation(location);

        assertEquals(1, result.getId());
    }

    @Test
    public void testGetLocationById() {
        Location location = new Location();
        location.setId(1);
        location.setName("Gotham City");
        location.setDescription("Dark and Brooding");
        location.setAddress("123 Gotham Street");
        location.setLatitude(40.7128);
        location.setLongitude(-74.0060);

        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), anyInt())).thenReturn(location);

        Location result = locationDAO.getLocationById(1);
        assertEquals(location, result);
    }

    @Test
    public void testUpdateLocation() {
        Location location = new Location();
        location.setId(1);
        location.setName("Metropolis");
        location.setDescription("City of Tomorrow");
        location.setAddress("456 Metropolis Street");
        location.setLatitude(40.7654);
        location.setLongitude(-74.9876);

        when(jdbcTemplate.update(
                "UPDATE Locations SET name=?, description=?, address=?, latitude=?, longitude=? WHERE location_id=?",
                "Metropolis",
                "City of Tomorrow",
                "456 Metropolis Street",
                40.7654,
                -74.9876,
                1
        )).thenReturn(1);

        locationDAO.updateLocation(location);
    }

    @Test
    public void testDeleteLocationById() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);
        locationDAO.deleteLocationById(1);
        // Like update,  mainly checking for no errors.
    }

    @Test
    public void testAddLocationWithNullInputs() {
        Location location = new Location();
        location.setName(null);
        location.setDescription(null);
        location.setAddress(null);

        assertThrows(Exception.class, () -> {
            locationDAO.addLocation(location);
        });
    }

    @Test
    public void testGetLocationByNonExistentId() {
        int nonExistentId = 999;
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(nonExistentId)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(Exception.class, () -> {
            locationDAO.getLocationById(nonExistentId);
        });
    }

    @Test
    public void testUpdateNonExistentLocation() {
        Location location = new Location();
        location.setId(999); // non-existent ID
        location.setName("Metropolis");
        location.setDescription("City of Tomorrow");

        when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), anyInt())).thenReturn(0);

        assertThrows(Exception.class, () -> {
            locationDAO.updateLocation(location);
        });
    }

    @Test
    public void testDeleteNonExistentLocation() {
        int nonExistentId = 999;
        when(jdbcTemplate.update(anyString(), eq(nonExistentId))).thenReturn(0);

        assertThrows(Exception.class, () -> {
            locationDAO.deleteLocationById(nonExistentId);
        });
    }

}
