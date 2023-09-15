package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    // Fetch all locations
    // GET http://localhost:8080/api/locations
    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    // Fetch a location by its ID
    // GET http://localhost:8080/api/locations/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationService.getLocationById(id);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Create a new location
    // POST http://localhost:8080/api/locations
    // Body: JSON representation of the location to create
    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    // Update an existing location
    // PUT http://localhost:8080/api/locations/{id}
    // Body: JSON representation of the updated location details
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLocation(@PathVariable int id, @RequestBody Location location) {
        locationService.updateLocation(location);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Delete a location by its ID
    // DELETE http://localhost:8080/api/locations/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
