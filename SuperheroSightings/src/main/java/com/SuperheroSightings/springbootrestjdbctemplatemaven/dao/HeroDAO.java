package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Hero;

import java.util.List;

public interface HeroDAO {
    Hero addHero(Hero hero);
    Hero getHeroById(int id);
    List<Hero> getAllHeroes();
    void updateHero(Hero hero);
    void deleteHeroById(int id);
    List<Hero> getHeroesByOrganizationId(int organizationId);
    List<Hero> getHeroesByLocationId(int locationId);
}
