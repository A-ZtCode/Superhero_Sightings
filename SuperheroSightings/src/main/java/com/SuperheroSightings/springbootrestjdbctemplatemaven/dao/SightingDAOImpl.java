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

/**
 * Implementation of the {@link SightingDAO} interface for managing Sighting entities.
 *
 * This class utilizes Spring's JdbcTemplate for data access and manipulation in the database.
 * It provides CRUD (Create, Read, Update, Delete) operations and additional querying functionalities
 * related to Sightings.
 */
@Repository
public class SightingDAOImpl implements SightingDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructs a new instance of the DAO implementation and injects the JdbcTemplate dependency.
     *
     * @param jdbcTemplate The JdbcTemplate to be used for database operations.
     */
    @Autowired
    public SightingDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sighting addSighting(Sighting sighting) {
        final String sql = "INSERT INTO Sightings(hero_id, location_id, date) VALUES(?,?,?)";
        jdbcTemplate.update(sql, sighting.getHero().getId(), sighting.getLocation().getId(), sighting.getDate());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        sighting.setId(newId);
        return sighting;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sighting getSightingById(int id) {
        final String sql = "SELECT * FROM Sightings WHERE sighting_id = ?";
        return jdbcTemplate.queryForObject(sql, new SightingMapper(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sighting> getAllSightings() {
        final String sql = "SELECT * FROM Sightings";
        return jdbcTemplate.query(sql, new SightingMapper());
    }

    /**
     * {@inheritDoc}
     */
    public void updateSighting(Sighting sighting) {
        final String sql = "UPDATE Sightings SET hero_id=?, location_id=?, date=? WHERE sighting_id=?";
        int rowsAffected = jdbcTemplate.update(sql, sighting.getHero().getId(), sighting.getLocation().getId(), sighting.getDate(), sighting.getId());
        if(rowsAffected == 0) {
            throw new RuntimeException("Attempted to update a non-existent sighting");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int deleteSightingById(int id) {
        final String sql = "DELETE FROM Sightings WHERE sighting_id=?";
        return jdbcTemplate.update(sql, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sighting> getSightingsByDate(LocalDate date) {
        final String sql = "SELECT * FROM Sightings WHERE date = ?";
        return jdbcTemplate.query(sql, new SightingMapper(), date);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sighting> getSightingsByLocationId(int locationId) {
        final String sql = "SELECT * FROM Sightings WHERE location_id = ?";
        return jdbcTemplate.query(sql, new SightingMapper(), locationId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sighting> getSightingsByHeroId(int heroId) {
        final String sql = "SELECT * FROM Sightings WHERE hero_id = ?";
        return jdbcTemplate.query(sql, new SightingMapper(), heroId);
    }

    /**
     * RowMapper implementation for mapping rows of the Sightings table into Sighting objects.
     */
    private static final class SightingMapper implements RowMapper<Sighting> {

        /**
         * Maps a row from the result set to a Sighting object.
         *
         * @param rs The result set from the query.
         * @param index The current row number.
         * @return The mapped Sighting object.
         * @throws SQLException If there is an issue accessing the data in the result set.
         */
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
