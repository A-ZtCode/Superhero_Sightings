package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.HeroDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HeroServiceImpl implements HeroService {

    private final HeroDAO heroDAO;

    @Autowired
    public HeroServiceImpl(HeroDAO heroDAO) {
        this.heroDAO = heroDAO;
    }

    @Override
    public Hero addHero(Hero hero) {
        return heroDAO.addHero(hero);
    }

    @Override
    public Hero getHeroById(int id) {
        return heroDAO.getHeroById(id);
    }

    @Override
    public Hero updateHero(Hero hero) {
        heroDAO.updateHero(hero);
        return hero;  // After updating, return the hero object
    }
    @Override
    public void deleteHero(int id) {
        heroDAO.deleteHeroById(id);
    }

    @Override
    public List<Hero> getAllHeroes() {
        return heroDAO.getAllHeroes();
    }
}
