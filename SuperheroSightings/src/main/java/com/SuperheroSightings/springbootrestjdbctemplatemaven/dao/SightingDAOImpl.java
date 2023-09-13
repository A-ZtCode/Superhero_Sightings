package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

@Repository
public class SightingDAOImpl implements SightingDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SightingDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        final String sql = "INSERT INTO Sightings(hero_id, location_id, date) VALUES(?,?,?)";
        jdbcTemplate.update(sql, sighting.getHero().getId(), sighting.getLocation().getId(), sighting.getDate());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setId(newId);
        return sighting;
    }

    @Override
    public Sighting getSightingById(int id) {
        final String sql = "SELECT * FROM Sightings WHERE sighting_id = ?";
        return jdbcTemplate.queryForObject(sql, new SightingMapper(), id);
    }

    @Override
    public List<Sighting> getAllSightings() {
        final String sql = "SELECT * FROM Sightings";
        return jdbcTemplate.query(sql, new SightingMapper());
    }

    public void updateSighting(Sighting sighting) {
        final String sql = "UPDATE Sightings SET hero_id=?, location_id=?, date=? WHERE sighting_id=?";
        int rowsAffected = jdbcTemplate.update(sql, sighting.getHero().getId(), sighting.getLocation().getId(), sighting.getDate(), sighting.getId());
        if(rowsAffected == 0) {
            throw new RuntimeException("Attempted to update a non-existent sighting");
        }
    }

    @Override
    public int deleteSightingById(int id) {
        final String sql = "DELETE FROM Sightings WHERE sighting_id=?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        final String sql = "SELECT * FROM Sightings WHERE date = ?";
        return jdbcTemplate.query(sql, new SightingMapper(), date);
    }

    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        final String sql = "SELECT * FROM Sightings WHERE location_id = ?";
        return jdbcTemplate.query(sql, new SightingMapper(), locationId);
    }
    @Override
    public List<Sighting> getSightingsByHeroId(int heroId) {
        final String sql = "SELECT * FROM Sightings WHERE hero_id = ?";
        return jdbcTemplate.query(sql, new SightingMapper(), heroId);
    }
    private static final class SightingMapper implements RowMapper<Sighting> {
        @Override
        public Sighting mapRow(ResultSet rs, int index) throws SQLException {
            Sighting sighting = new Sighting();
            sighting.setId(rs.getInt("sighting_id"));
            Hero hero = new Hero();
            hero.setId(rs.getInt("hero_id"));
            sighting.setHero(hero);
            Location location = new Location();
            location.setId(rs.getInt("location_id"));
            sighting.setLocation(location);
            sighting.setDate(rs.getDate("date").toLocalDate());
            return sighting;
        }
    }

}
