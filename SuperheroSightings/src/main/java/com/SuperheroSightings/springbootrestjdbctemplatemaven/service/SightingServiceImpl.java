package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.SightingDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SightingServiceImpl implements SightingService{

    private final SightingDAO sightingDAO;

    @Autowired
    public SightingServiceImpl(SightingDAO sightingDAO) {
        this.sightingDAO = sightingDAO;
    }

    @Override
    public Sighting addSighting(Sighting sighting) {
        return sightingDAO.addSighting(sighting);
    }

    @Override
    public Sighting getSightingById(int id) {
        return sightingDAO.getSightingById(id);
    }

    @Override
    public Sighting updateSighting(Sighting sighting) {
        sightingDAO.updateSighting(sighting);
        return sighting;
    }

    @Override
    public void deleteSighting(int id) {
        sightingDAO.deleteSightingById(id);
    }

    @Override
    public List<Sighting> getAllSightings() {
        return sightingDAO.getAllSightings();
    }
}
