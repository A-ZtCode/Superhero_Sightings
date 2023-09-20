package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.SightingDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Sighting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the SightingService interface.
 *
 * This class provides the concrete implementation of the SightingService interface.
 * It interacts with the data access layer to perform CRUD operations on the Sighting entity.
 * All methods are transactional, ensuring data integrity and consistency.
 */
@Service
public class SightingServiceImpl implements SightingService{

    private final SightingDAO sightingDAO;

    /**
     * Constructs a new SightingServiceImpl with the provided SightingDAO.
     *
     * @param sightingDAO the data access object to interact with the database.
     */
    @Autowired
    public SightingServiceImpl(SightingDAO sightingDAO) {
        this.sightingDAO = sightingDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sighting addSighting(Sighting sighting) {
        return sightingDAO.addSighting(sighting);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sighting getSightingById(int id) {
        return sightingDAO.getSightingById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Sighting updateSighting(Sighting sighting) {
        sightingDAO.updateSighting(sighting);
        return sighting;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteSighting(int id) {
        sightingDAO.deleteSightingById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Sighting> getAllSightings() {
        return sightingDAO.getAllSightings();
    }
}
