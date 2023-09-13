package com.SuperheroSightings.springbootrestjdbctemplatemaven.service;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;

import java.util.List;

public interface LocationService {
    Location addLocation(Location location);
    Location getLocationById(int id);
    Location updateLocation(Location location);
    void deleteLocation(int id);
    List<Location> getAllLocations();
    List<Location> getLocationsByHeroId(int heroId);
}
