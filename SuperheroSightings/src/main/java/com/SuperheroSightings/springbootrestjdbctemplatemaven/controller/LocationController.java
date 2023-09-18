package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Location;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@Controller // Change from RestController to Controller
@RequestMapping("/locations") // Changed from /api/locations to /locations
public class LocationController {

    @Autowired
    private LocationService locationService;

    /**
     * Retrieves a list of all locations in the system.
     *
     * API Endpoint: GET http://localhost:8080/locations
     *
     * @return List of Location objects.
     */
    @GetMapping
    @ResponseBody  // This ensures the method's return value is sent as JSON
    public List<Location> getAllLocations() {
        return locationService.getAllLocations();
    }

    /**
     * Retrieves details of a specific location identified by its ID.
     *
     * API Endpoint: GET http://localhost:8080/locations/{id}
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
     * API Endpoint: POST http://localhost:8080/locations
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
     * API Endpoint: PUT http://localhost:8080/locations/{id}
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
     * API Endpoint: DELETE http://localhost:8080/locations/{id}
     *
     * @param id ID of the location to be deleted.
     * @return ResponseEntity indicating the delete status.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Displays the form to add a new location.
     *
     * API Endpoint: GET http://localhost:8080/location/new
     *
     * @param model The model that holds data for the view.
     * @return The name of the view template (Thymeleaf template) to be rendered.
     */
    @GetMapping("/location/new")
    public String showAddLocationForm(Model model) {
        model.addAttribute("location", new Location());
        return "location-form";
    }

    /**
     * Displays the form to edit an existing location.
     *
     * API Endpoint: GET http://localhost:8080/location/edit/{id}
     *
     * @param id The ID of the location to be edited.
     * @param model The model that holds data for the view.
     * @return The name of the view template (Thymeleaf template) to be rendered.
     */
    @GetMapping("/location/edit/{id}")
    public String showEditLocationForm(@PathVariable int id, Model model) {
        Location location = locationService.getLocationById(id);
        model.addAttribute("location", location);
        return "location-form";
    }
    /**
     * Handles the form submission for adding a new location or updating an existing one.
     *
     * API Endpoint: POST http://localhost:8080/location/save
     *
     * @param location The Location object populated from the form.
     * @param model The model that holds data for the view.
     * @return Redirect to the list of locations.
     */
    @PostMapping("/location/save")
    public String saveLocation(Location location, Model model) {
        if (location.getId() == null) {
            locationService.addLocation(location);
        } else {
            locationService.updateLocation(location);
        }
        return "redirect:/locations"; // Redirect to the list of locations (this endpoint needs to be created)
    }
}
