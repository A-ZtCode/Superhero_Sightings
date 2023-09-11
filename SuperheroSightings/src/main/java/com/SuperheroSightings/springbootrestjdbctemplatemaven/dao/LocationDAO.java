package com.SuperheroSightings.springbootrestjdbctemplatemaven.dao;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.model.Location;

import java.util.List;

public interface LocationDAO {
    Location addLocation(Location location);
    Location getLocationById(int id);
    List<Location> getAllLocations();
    void updateLocation(Location location);
    void deleteLocationById(int id);
    List<Location> getLocationsByHeroId(int heroId);
}
