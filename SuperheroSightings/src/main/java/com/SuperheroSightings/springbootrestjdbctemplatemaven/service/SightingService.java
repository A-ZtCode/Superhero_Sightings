package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;

import java.util.List;

public interface SightingService {
    Sighting addSighting(Sighting sighting);
    Sighting getSightingById(int id);
    Sighting updateSighting(Sighting sighting);
    void deleteSighting(int id);
    List<Sighting> getAllSightings();
}
