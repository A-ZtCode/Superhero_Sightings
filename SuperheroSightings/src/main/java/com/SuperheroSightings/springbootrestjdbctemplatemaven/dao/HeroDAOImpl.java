package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Implementation of the HeroDAO interface for managing operations related to Heroes using JDBC.
 *
 * This class provides the logic for the CRUD operations on the Hero entity in the database.
 */
@Repository
public class HeroDAOImpl implements HeroDAO {

    private final JdbcTemplate jdbcTemplate;

    /**
     * Constructor to autowire the JdbcTemplate dependency.
     *
     * @param jdbcTemplate Object to interact with the database.
     */
    @Autowired
    public HeroDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hero addHero(Hero hero) {
        final String sql = "INSERT INTO Heroes(name, description, superpower, imagePath) VALUES(?,?,?,?)";
        jdbcTemplate.update(sql, hero.getName(), hero.getDescription(), hero.getSuperpower(), hero.getImagePath());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setId(newId);
        return hero;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hero getHeroById(int id) {
        final String sql = "SELECT * FROM Heroes WHERE hero_id = ?";
        return jdbcTemplate.queryForObject(sql, new HeroMapper(), id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Hero> getAllHeroes() {
        final String sql = "SELECT * FROM Heroes";
        return jdbcTemplate.query(sql, new HeroMapper());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateHero(Hero hero) {
        final String sql = "UPDATE Heroes SET name=?, description=?, superpower=?, imagePath=? WHERE hero_id=?";
        int updatedRows = jdbcTemplate.update(sql, hero.getName(), hero.getDescription(), hero.getSuperpower(), hero.getImagePath(), hero.getId());
        if (updatedRows == 0) {
            throw new RuntimeException("Hero with ID " + hero.getId() + " does not exist.");
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteHeroById(int id) {
        final String sql = "DELETE FROM Heroes WHERE hero_id=?";
        int affectedRows = jdbcTemplate.update(sql, id);
        if (affectedRows == 0) {
            throw new RuntimeException("Hero with ID " + id + " does not exist.");
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Hero> getHeroesByOrganizationId(int organizationId) {
        final String sql = "SELECT h.* FROM Heroes h JOIN Hero_Organization ho ON h.hero_id = ho.hero_id WHERE ho.organization_id = ?";
        return jdbcTemplate.query(sql, new HeroMapper(), organizationId);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Hero> getHeroesByLocationId(int locationId) {
        final String sql = "SELECT h.* FROM Heroes h JOIN Sightings s ON h.hero_id = s.hero_id WHERE s.location_id = ?";
        return jdbcTemplate.query(sql, new HeroMapper(), locationId);
    }

    /**
     * Mapper class to map the result set rows to Hero object properties.
     */
    private static final class HeroMapper implements RowMapper<Hero> {

        /**
         * Maps a result set row to a Hero object.
         *
         * @param rs Result set containing the data.
         * @param index Row number of the result set.
         * @return Hero object with properties set from the result set row.
         * @throws SQLException In case of any SQL errors.
         */
        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("hero_id"));
            hero.setName(rs.getString("name"));
            hero.setDescription(rs.getString("description"));
            hero.setSuperpower(rs.getString("superpower"));
            hero.setImagePath(rs.getString("imagePath"));  // Added this line
            return hero;
        }
    }
}
