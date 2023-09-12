package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Sighting;

import java.time.LocalDate;
import java.util.List;

public interface SightingDAO {
    Sighting addSighting(Sighting sighting);
    Sighting getSightingById(int id);
    List<Sighting> getAllSightings();
    void updateSighting(Sighting sighting);
    int deleteSightingById(int id);
    List<Sighting> getSightingsByDate(LocalDate date);
    List<Sighting> getSightingsByLocationId(int locationId);
    List<Sighting> getSightingsByHeroId(int heroId);
}
