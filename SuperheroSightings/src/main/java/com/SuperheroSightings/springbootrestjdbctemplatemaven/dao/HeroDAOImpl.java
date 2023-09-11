package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HeroDAOImpl  implements HeroDAO{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public HeroDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Hero addHero(Hero hero) {
        final String sql = "INSERT INTO Heroes(name, description, superpower) VALUES(?,?,?)";
        jdbcTemplate.update(sql, hero.getName(), hero.getDescription(), hero.getSuperpower());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        hero.setId(newId);
        return hero;
    }

    @Override
    public Hero getHeroById(int id) {
        final String sql = "SELECT * FROM Heroes WHERE hero_id = ?";
        return jdbcTemplate.queryForObject(sql, new HeroMapper(), id);
    }

    @Override
    public List<Hero> getAllHeroes() {
        final String sql = "SELECT * FROM Heroes";
        return jdbcTemplate.query(sql, new HeroMapper());
    }

    @Override
    public void updateHero(Hero hero) {
        final String sql = "UPDATE Heroes SET name=?, description=?, superpower=? WHERE hero_id=?";
        jdbcTemplate.update(sql, hero.getName(), hero.getDescription(), hero.getSuperpower(), hero.getId());
    }

    @Override
    public void deleteHeroById(int id) {
        final String sql = "DELETE FROM Heroes WHERE hero_id=?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Hero> getHeroesByOrganizationId(int organizationId) {
        final String sql = "SELECT h.* FROM Heroes h JOIN Hero_Organization ho ON h.hero_id = ho.hero_id WHERE ho.organization_id = ?";
        return jdbcTemplate.query(sql, new HeroMapper(), organizationId);
    }

    @Override
    public List<Hero> getHeroesByLocationId(int locationId) {
        final String sql = "SELECT h.* FROM Heroes h JOIN Sightings s ON h.hero_id = s.hero_id WHERE s.location_id = ?";
        return jdbcTemplate.query(sql, new HeroMapper(), locationId);
    }
    private static final class HeroMapper implements RowMapper<Hero> {
        @Override
        public Hero mapRow(ResultSet rs, int index) throws SQLException {
            Hero hero = new Hero();
            hero.setId(rs.getInt("hero_id"));
            hero.setName(rs.getString("name"));
            hero.setDescription(rs.getString("description"));
            hero.setSuperpower(rs.getString("superpower"));
            return hero;
        }
    }
}
