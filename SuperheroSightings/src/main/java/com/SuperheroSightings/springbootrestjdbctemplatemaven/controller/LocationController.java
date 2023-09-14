package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    @PutMapping("/{id}")
    public Location updateLocation(@PathVariable int id, @RequestBody Location location) {
        location.setId(id);
        return locationService.updateLocation(location);
    }

    @GetMapping("/{id}")
    public Location getLocation(@PathVariable int id) {
        return locationService.getLocationById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
    }
}