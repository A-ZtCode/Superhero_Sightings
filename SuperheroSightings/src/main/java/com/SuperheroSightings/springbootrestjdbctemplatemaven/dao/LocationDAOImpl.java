package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class LocationDAOImpl implements LocationDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public LocationDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public Location addLocation(Location location) {
        final String sql = "INSERT INTO Locations(name, description, address, latitude, longitude) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, location.getName(), location.getDescription(), location.getAddress(), location.getLatitude(), location.getLongitude());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        location.setId(newId);
        return location;
    }

    @Override
    public Location getLocationById(int id) {
        final String sql = "SELECT * FROM Locations WHERE location_id = ?";
        return jdbcTemplate.queryForObject(sql, new LocationMapper(), id);
    }

    @Override
    public List<Location> getAllLocations() {
        final String sql = "SELECT * FROM Locations";
        return  jdbcTemplate.query(sql, new LocationMapper());
    }

    @Override
    public void updateLocation(Location location) {
        final String sql = "UPDATE Locations SET name=?, description=?, address=?, latitude=?, longitude=? WHERE location_id=?";
        jdbcTemplate.update(sql, location.getName(), location.getDescription(), location.getAddress(), location.getLatitude(), location.getLongitude(), location.getId());
    }

    @Override
    public void deleteLocationById(int id) {
        final String sql = "DELETE FROM Locations WHERE location_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Location> getLocationsByHeroId(int heroId) {
        final String sql = "SELECT l.* FROM Locations l JOIN Sightings s ON l.location_id = s.location_id WHERE s.hero_id = ?";
        return  jdbcTemplate.query(sql, new LocationMapper(), heroId);
    }

    private static final class LocationMapper implements RowMapper<Location> {
        @Override
        public Location mapRow(ResultSet rs, int index) throws SQLException {
            Location location = new Location();
            location.setId(rs.getInt("location_id"));
            location.setName(rs.getString("name"));
            location.setDescription(rs.getString("description"));
            location.setAddress(rs.getString("address"));
            location.setLatitude(rs.getDouble("latitude"));
            location.setLongitude(rs.getDouble("longitude"));
            return location;
        }
    }
}
