package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Controller class for managing CRUD operations related to Locations.
 *
 * This class provides RESTful API endpoints for client applications to perform operations
 * on the Location entity. It supports creating, updating, deleting, and retrieving location records.
 *
 * @author Mercy Aigbe Azeta
 * @version 1.0
 * @since (2023-09-11)
 */
@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * Retrieves a list of all locations in the system.
     *
     * API Endpoint: GET http://localhost:8080/api/locations
     *
     * @return List of Location objects.
     */
    @GetMapping
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    /**
     * Retrieves details of a specific location identified by its ID.
     *
     * API Endpoint: GET http://localhost:8080/api/locations/{id}
     *
     * @param id ID of the location to be retrieved.
     * @return ResponseEntity containing the Location object or an error status.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationService.getLocationById(id);
        if (location != null) {
            return new ResponseEntity<>(location, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Creates a new location record.
     *
     * API Endpoint: POST http://localhost:8080/api/locations
     * Body: JSON representation of the location to create
     *
     * @param location Location object containing details of the location to be created.
     * @return Created location with the assigned ID.
     */
    @PostMapping
    public Location addLocation(@RequestBody Location location) {
        return locationService.addLocation(location);
    }

    /**
     * Updates details of a specific location identified by its ID.
     *
     * API Endpoint: PUT http://localhost:8080/api/locations/{id}
     * Body: JSON representation of the updated location details
     *
     * @param id ID of the location to be updated.
     * @param location Location object containing updated details.
     * @return ResponseEntity indicating the update status.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateLocation(@PathVariable int id, @RequestBody Location location) {
        locationService.updateLocation(location);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Deletes a specific location identified by its ID.
     *
     * API Endpoint: DELETE http://localhost:8080/api/locations/{id}
     *
     * @param id ID of the location to be deleted.
     * @return ResponseEntity indicating the delete status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
