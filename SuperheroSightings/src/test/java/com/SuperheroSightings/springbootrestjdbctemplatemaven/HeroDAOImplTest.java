package com.SuperheroSightings.springbootrestjdbctemplatemaven;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.HeroDAOImpl;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Hero;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class HeroDAOImplTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private HeroDAOImpl heroDAO;

    @Test
    public void testAddHero() {
        Hero hero = new Hero();
        hero.setName("Superman");
        hero.setDescription("Man of Steel");
        hero.setSuperpower("Flight");

        when(jdbcTemplate.update(anyString(), any(), any(), any())).thenReturn(1);
        when(jdbcTemplate.queryForObject(anyString(), eq(Integer.class))).thenReturn(1);

        Hero result = heroDAO.addHero(hero);

        assertEquals(1, result.getId());
    }

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


    @Test
    public void testUpdateHero() {
        Hero hero = new Hero();
        hero.setId(1);
        hero.setName("Batman");
        hero.setDescription("Dark Knight");
        hero.setSuperpower("Intelligence");

        when(jdbcTemplate.update(anyString(), any(), any(), any(), anyInt())).thenReturn(1);

        heroDAO.updateHero(hero);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(hero.getName()), eq(hero.getDescription()), eq(hero.getSuperpower()), eq(hero.getId()));
    }

    @Test
    public void testDeleteHero() {
        when(jdbcTemplate.update(anyString(), anyInt())).thenReturn(1);

        heroDAO.deleteHeroById(1);

        verify(jdbcTemplate, times(1)).update(anyString(), eq(1));
    }

    @Test
    public void testGetAllHeroes() {
        List<Hero> heroes = List.of(
                new Hero(1, "Superman", "Man of Steel", "Flight"),
                new Hero(2, "Batman", "Dark Knight", "Intelligence")
        );

        when(jdbcTemplate.query(anyString(), any(RowMapper.class))).thenReturn(heroes);

        List<Hero> result = heroDAO.getAllHeroes();

        assertEquals(heroes, result);
    }
}

