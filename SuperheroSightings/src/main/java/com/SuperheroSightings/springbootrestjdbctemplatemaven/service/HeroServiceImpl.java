package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.HeroDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing operations related to heroes.
 *
 * This service provides a bridge between the controller and the DAO. It
 * ensures that business rules, data validation, and other operations are
 * applied consistently. The service utilizes the HeroDAO for database operations.
 *
 * The operations provided include CRUD operations for hero entities.
 */
@Service
public class HeroServiceImpl implements HeroService {

    private final HeroDAO heroDAO;

    /**
     * Constructor-based dependency injection of the HeroDAO.
     *
     * @param heroDAO The DAO to be used for hero-related database operations.
     */
    @Autowired
    public HeroServiceImpl(HeroDAO heroDAO) {
        this.heroDAO = heroDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hero addHero(Hero hero) {
        return heroDAO.addHero(hero);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hero getHeroById(int id) {
        return heroDAO.getHeroById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Hero updateHero(Hero hero) {
        heroDAO.updateHero(hero);
        return hero;  // After updating, return the hero object
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteHero(int id) {
        heroDAO.deleteHeroById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Hero> getAllHeroes() {
        return heroDAO.getAllHeroes();
    }
}
