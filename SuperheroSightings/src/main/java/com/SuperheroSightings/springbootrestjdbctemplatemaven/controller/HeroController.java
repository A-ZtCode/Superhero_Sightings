package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 */
@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;

    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    // Add a new hero
    // POST http://localhost:8080/heroes
    @PostMapping
    public Hero addHero(@RequestBody Hero hero) {
        return heroService.addHero(hero);
    }

    // Update details of a specific hero by ID
    // PUT http://localhost:8080/heroes/{id}
    @PutMapping("/{id}")
    public Hero updateHero(@PathVariable int id, @RequestBody Hero hero) {
        hero.setId(id);
        return heroService.updateHero(hero);
    }

    // Retrieve a specific hero by ID
    // GET http://localhost:8080/heroes/{id}

    @GetMapping("/{id}")
    public Hero getHero(@PathVariable int id) {
        return heroService.getHeroById(id);
    }

    // Delete a specific hero by ID
    // DELETE http://localhost:8080/heroes/{id}
    @DeleteMapping("/{id}")
    public void deleteHero(@PathVariable int id) {
        heroService.deleteHero(id);
    }

    // Retrieve all heroes
    // GET http://localhost:8080/heroes
    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAllHeroes();
    }
}
