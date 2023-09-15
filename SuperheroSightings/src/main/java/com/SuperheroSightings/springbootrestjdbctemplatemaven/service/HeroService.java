package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;

import java.util.List;

public interface HeroService {
    Hero addHero(Hero hero);
    Hero getHeroById(int id);
    Hero updateHero(Hero hero);
    void deleteHero(int id);
    List<Hero> getAllHeroes();
}
