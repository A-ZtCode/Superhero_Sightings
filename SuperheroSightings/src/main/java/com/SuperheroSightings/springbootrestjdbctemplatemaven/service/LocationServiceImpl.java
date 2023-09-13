package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.LocationDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationDAO locationDAO;

    @Autowired
    public LocationServiceImpl(LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    @Override
    public Location addLocation(Location location) {
        return locationDAO.addLocation(location);
    }

    @Override
    public Location getLocationById(int id) {
        return locationDAO.getLocationById(id);
    }

    @Override
    public Location updateLocation(Location location) {
        locationDAO.updateLocation(location);
        return location;
    }

    @Override
    public void deleteLocation(int id) {
        locationDAO.deleteLocationById(id);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDAO.getAllLocations();
    }

    @Override
    public List<Location> getLocationsByHeroId(int heroId) {
        return locationDAO.getLocationsByHeroId(heroId);
    }
}
