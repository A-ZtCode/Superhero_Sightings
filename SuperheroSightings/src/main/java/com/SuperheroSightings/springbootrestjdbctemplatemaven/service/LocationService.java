package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.dao.LocationDAO;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationDAO locationDAO;

    public Location addLocation(Location location) {

        return locationDAO.addLocation(location);
    }

    public Location getLocationById(int id) {
        return locationDAO.getLocationById(id);
    }

    public Location updateLocation(Location location) {
        locationDAO.updateLocation(location);
        return location;
    }

    public void deleteLocation(int id) {
        locationDAO.deleteLocationById(id);
    }

    public List<Location> getAllLocations() {
        return locationDAO.getAllLocations();
    }
}
