package com.SuperheroSightings.springbootrestjdbctemplatemaven;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.HeroDAOImpl;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * Test class for the HeroDAOImpl.
 *
 * This class contains unit tests for the HeroDAOImpl using the JUnit 5 framework
 * and Mockito for mocking dependencies. It ensures that the data access object
 * for heroes performs its CRUD operations correctly.
 */
@ExtendWith(MockitoExtension.class)
public class HeroDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private HeroDAOImpl heroDAO;

    /**
     * Test for adding a hero.
     */
    @Test
    public void testAddHero() {
        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Man of Steel");
        hero.setSuperpower("Flight");

        when(jdbcTemplate.update(anyString(), eq(hero.getName()), eq(hero.getDescription()), eq(hero.getSuperpower()), isNull())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);

        Hero result = heroDAO.addHero(hero);

        assertEquals(1, result.getId());
    }

    /**
     * Test for retrieving a hero by its ID.
     */
    @Test
    public void testGetHeroById() {
        Hero hero = new Hero();
        hero.setId(1);
        hero.setName("Superman");
        hero.setDescription("Man of Steel");
        hero.setSuperpower("Flight");

        // Stub the jdbcTemplate's queryForObject method to return the hero object
        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(1)))
                .thenReturn(hero);

        Hero result = heroDAO.getHeroById(1);

        assertEquals(hero, result);
    }

    /**
     * Test for updating a hero's details.
     */
    @Test
    public void testUpdateHero() {
        Hero hero = new Hero();
        hero.setId(1);
        hero.setName("Batman");
        hero.setDescription("Dark Knight");
        hero.setSuperpower("Intelligence");

        when(jdbcTemplate.update(anyString(), eq(hero.getName()), eq(hero.getDescription()), eq(hero.getSuperpower()), isNull(), eq(hero.getId()))).thenReturn(1);

        heroDAO.updateHero(hero);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(hero.getName()), eq(hero.getDescription()), eq(hero.getSuperpower()), isNull(), eq(hero.getId()));
    }

    /**
     * Test for deleting a hero by its ID.
     */
    @Test
    public void testDeleteHero() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);

        heroDAO.deleteHeroById(1);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(1));
    }

    /**
     * Test for getting all heroes.
     */
    @Test
    public void testGetAllHeroes() {
        List<Hero> heroes = List.of(
                new Hero(1, "Superman", "Man of Steel", "Flight", "/path/to/superman/image"),
                new Hero(2, "Batman", "Dark Knight", "Intelligence", "/path/to/batman/image")

        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(heroes);

        List<Hero> result = heroDAO.getAllHeroes();

        assertEquals(heroes, result);
    }

    /**
     * Test for adding a hero with null inputs.
     */
    // Test Add Hero with Null Inputs:
    @Test
    public void testAddHeroWithNullInputs() {
        Hero hero = new Hero();
        hero.setName(null);
        hero.setDescription(null);
        hero.setSuperpower(null);

        assertThrows(Exception.class, () -> {
            heroDAO.addHero(hero);
        });
    }

    /**
     * Test for retrieving a hero by a non-existent ID.
     */
    // Test Get Hero By Non-Existent ID:
    @Test
    public void testGetHeroByNonExistentId() {
        int nonExistentId = 999;
        when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(nonExistentId)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(Exception.class, () -> {
            heroDAO.getHeroById(nonExistentId);
        });
    }

    /**
     * Test for updating a non-existent hero.
     */
    // Test Update Non-Existent Hero:
    @Test
    public void testUpdateNonExistentHero() {
        Hero hero = new Hero();
        hero.setId(999); // non-existent ID
        hero.setName("Batman");
        hero.setDescription("Dark Knight");
        hero.setSuperpower("Intelligence");

        when(jdbcTemplate.update(anyString(), any(), any(), any(), anyInt())).thenReturn(0);

        assertThrows(Exception.class, () -> {
            heroDAO.updateHero(hero);
        });
    }

    /**
     * Test for deleting a non-existent hero.
     */
    // Test Delete Non-Existent Hero:
    @Test
    public void testDeleteNonExistentHero() {
        int nonExistentId = 999;
        when(jdbcTemplate.update(anyString(), eq(nonExistentId))).thenReturn(0);

        assertThrows(Exception.class, () -> {
            heroDAO.deleteHeroById(nonExistentId);
        });
    }

}

