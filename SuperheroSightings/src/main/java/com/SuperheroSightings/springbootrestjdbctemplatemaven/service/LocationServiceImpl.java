package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.LocationDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for managing operations related to locations.
 *
 * This class provides the concrete implementation of the LocationService interface.
 * It ensures the correct business logic, data validation, and other required operations
 * are applied consistently across location-related functionalities by delegating data
 * persistence tasks to the LocationDAO.
 */
@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDAO locationDAO;

    /**
     * Constructor for dependency injection of LocationDAO.
     *
     * @param locationDAO DAO object responsible for location data operations.
     */
    @Autowired
    public LocationServiceImpl(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location addLocation(Location location) {
        return locationDAO.addLocation(location);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location getLocationById(int id) {
        return locationDAO.getLocationById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Location updateLocation(Location location) {
        locationDAO.updateLocation(location);
        return location;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteLocation(int id) {
        locationDAO.deleteLocationById(id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> getAllLocations() {
        return locationDAO.getAllLocations();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Location> getLocationsByHeroId(int heroId) {
        return locationDAO.getLocationsByHeroId(heroId);
    }
}
