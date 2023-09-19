package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Hero;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.HeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
/**
 * Controller class for managing CRUD operations related to Heroes.
 *
 * This class provides RESTful API endpoints for client applications to perform operations
 * on the Hero entity. It supports creating, updating, deleting, and retrieving hero records.
 *
*/
@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final HeroService heroService;
    @PostMapping("/uploadHeroImage")
    public ResponseEntity<?> uploadHeroImage(@RequestParam("file") MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            heroService.saveImage(file.getInputStream(), fileName);
        } catch (IOException e) {
            return new ResponseEntity<>("Image upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Image uploaded successfully", HttpStatus.OK);
    }
    /**
     * Constructor that initializes the HeroService dependency.
     *
     * @param heroService Service class for hero operations.
     */
    @Autowired
    public HeroController(HeroService heroService) {
        this.heroService = heroService;
    }

    /**
     * Creates /adds a new hero record.
     *
     * @param hero Hero object containing details of the hero to be created.
     * @return Created/added hero with the assigned ID.
     *
     * API Endpoint: POST http://localhost:8080/heroes
     * Body: JSON representation of the Hero to add
     */
    @PostMapping
    public Hero addHero(@RequestBody Hero hero) {
        return heroService.addHero(hero);
    }

    /**
     * Updates details of a specific hero identified by its ID.
     *
     * @param id ID of the hero to be updated.
     * @param hero Hero object containing updated details.
     * @return Updated hero object.
     *
     * API Endpoint: PUT http://localhost:8080/heroes/{id}
     *  Body: JSON representation of the updated Hero details
     */
    @PutMapping("/{id}")
    public Hero updateHero(@PathVariable int id, @RequestBody Hero hero) {
        hero.setId(id);
        return heroService.updateHero(hero);
    }

    /**
     * Retrieves details of a specific hero identified by its ID.
     *
     * @param id ID of the hero to be retrieved.
     * @return Hero object containing details of the specified hero.
     *
     * API Endpoint: GET http://localhost:8080/heroes/{id}
     */
    @GetMapping("/{id}")
    public Hero getHero(@PathVariable int id) {
        return heroService.getHeroById(id);
    }

    /**
     * Deletes a specific hero identified by its ID.
     *
     * @param id ID of the hero to be deleted.
     *
     * API Endpoint: DELETE http://localhost:8080/heroes/{id}
     */
    @DeleteMapping("/{id}")
    public void deleteHero(@PathVariable int id) {
        heroService.deleteHero(id);
    }

    /**
     * Retrieves a list of all heroes in the system.
     *
     * @return List of Hero objects.
     *
     * API Endpoint: GET http://localhost:8080/heroes
     */
    @GetMapping
    public List<Hero> getAllHeroes() {
        return heroService.getAllHeroes();
    }

    // New method to display heroes in a template
    @GetMapping("/list")
    public String displayAllHeroes(Model model) {
        List<Hero> heroes = heroService.getAllHeroes();
        model.addAttribute("heroes", heroes);
        return "hero-crud";  // name of the Thymeleaf template
    }

    @GetMapping("/details/{id}")
    public String displayHeroDetails(@PathVariable int id, Model model) {
        Hero hero = heroService.getHeroById(id);
        model.addAttribute("hero", hero);
        return "hero-details";  // name of the Thymeleaf template for individual hero details
    }

    /**
     * Displays the form to add a new hero.
     *
     * API Endpoint: GET http://localhost:8080/hero/new
     *
     * @param model The model that holds data for the view.
     * @return The name of the view template (Thymeleaf template) to be rendered.
     */
    @GetMapping("/hero/new")
    public String showAddForm(Model model) {
        model.addAttribute("hero", new Hero());
        return "hero-form";
    }

    /**
     * Displays the form to edit an existing hero.
     *
     * API Endpoint: GET http://localhost:8080/hero/edit/{id}
     *
     * @param id The ID of the hero to be edited.
     * @param model The model that holds data for the view.
     * @return The name of the view template (Thymeleaf template) to be rendered.
     */
    @GetMapping("/hero/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Hero hero = heroService.getHeroById(id);
        model.addAttribute("hero", hero);
        return "hero-form";
    }
    /**
     * Saves a hero to the database. If the hero has an ID, it updates the existing record, otherwise it adds a new record.
     *
     * API Endpoint: POST http://localhost:8080/hero/save
     *
     * @param hero The hero object to be saved, constructed from the form data.
     * @return Redirects to the list of heroes after the hero is saved.
     */
    @PostMapping("/hero/save")
    public String saveHero(@ModelAttribute Hero hero) {
        if (hero.getId() == null) {
            heroService.addHero(hero);
        } else {
            heroService.updateHero(hero);
        }
        return "redirect:/heroes";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("heroImage") MultipartFile file, @RequestParam("heroId") Integer heroId) {
        // Validate file type
        String contentType = file.getContentType();
        if (!contentType.equals("image/jpeg") && !contentType.equals("image/png")) {
            return "Only JPG and PNG images are allowed.";
        }

        // Validate file size (for example, limit to 5MB)
        if (file.getSize() > 5 * 1024 * 1024) {
            return "File size should not exceed 5MB.";
        }

        // Define the folder path
        String folder = "images/";
        // Get the original file name
        String originalFilename = file.getOriginalFilename();
        // Append timestamp to the filename to make it unique
        String filename = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + "_" + originalFilename;
        // Define the path to save the file
        Path path = Paths.get(folder + filename);

        try {
            // Save the file to the path
            Files.write(path, file.getBytes());

            // Update the hero's record with the image path
            Hero hero = heroService.getHeroById(heroId);
            hero.setImagePath(folder + filename);
            heroService.updateHero(hero);

            // Redirect or return success response
            return "Image uploaded successfully!";
        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
            return "Error occurred while uploading the image.";
        }
    }
}
