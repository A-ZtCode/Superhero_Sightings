package com.SuperheroSightings.springbootrestjdbctemplatemaven.controller;

import com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto.Organization;
import com.SuperheroSightings.springbootrestjdbctemplatemaven.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Controller class for managing CRUD operations related to Organizations.
 *
 * This class provides RESTful API endpoints for client applications to perform operations
 * on the Organization entity. It supports creating, updating, deleting, and retrieving organization records.
 */
@Controller // Change from RestController to Controller
@RequestMapping("/organizations")
public class OrganizationController {

    private final OrganizationService organizationService;

    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    /**
     * Creates a new organization record.
     *
     * API Endpoint: POST http://localhost:8080/organizations
     * Body: JSON representation of the organization to create
     *
     * @param organization Organization object containing details of the organization to be created.
     * @return Created organization with the assigned ID.
     */
    @PostMapping("/add")
    public String addOrganization(@ModelAttribute Organization organization, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // Handle file storage, save the path in the organization object
        }
        organizationService.addOrganization(organization);
        return "redirect:/organizations/list";
    }

    /**
     * Retrieves details of a specific organization identified by its ID.
     *
     * API Endpoint: GET http://localhost:8080/organizations/{id}
     *
     * @param id ID of the organization to be retrieved.
     * @return Organization object with the specified ID.
     */
    @GetMapping("/{id}")
    public Organization getOrganization(@PathVariable int id) {
        return organizationService.getOrganizationById(id);
    }

    /**
     * Updates details of a specific organization identified by its ID.
     *
     * API Endpoint: PUT http://localhost:8080/organizations/{id}
     * Body: JSON representation of the updated organization details
     *
     * @param id ID of the organization to be updated.
     * @param organization Organization object containing updated details.
     * @return Updated organization details.
     */
    @PutMapping("/update/{id}")
    public String updateOrganization(@PathVariable int id, @ModelAttribute Organization organization, @RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            // Handle file storage, save the path in the organization object
        }
        organization.setId(id);
        organizationService.updateOrganization(organization);
        return "redirect:/organizations/list";
    }

    /**
     * Deletes a specific organization identified by its ID.
     *
     * API Endpoint: DELETE http://localhost:8080/organizations/{id}
     *
     * @param id ID of the organization to be deleted.
     */
    @DeleteMapping("/{id}")
    public void deleteOrganization(@PathVariable int id) {
        organizationService.deleteOrganization(id);
    }

    /**
     * Retrieves a list of all organizations in the system.
     *
     * API Endpoint: GET http://localhost:8080/organizations
     *
     * @return List of Organization objects.
     */
    @GetMapping
    @ResponseBody
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }

    // Add a new method to display organizations in a template
    @GetMapping("/list")
    public String displayAllOrganizations(Model model) {
        List<Organization> organizations = organizationService.getAllOrganizations();
        model.addAttribute("organizations", organizations);
        return "organization-list";  // name of the Thymeleaf template
    }
    /**
     * Displays the form to add a new organization.
     *
     * API Endpoint: GET http://localhost:8080/organization/new
     *
     * @param model The model that holds data for the view.
     * @return The name of the view template (Thymeleaf template) to be rendered.
     */
    @GetMapping("/organization/new")
    public String showAddOrganizationForm(Model model) {
        model.addAttribute("organization", new Organization());
        return "organization-form";
    }

    /**
     * Displays the form to edit an existing organization.
     *
     * API Endpoint: GET http://localhost:8080/organization/edit/{id}
     *
     * @param id The ID of the organization to be edited.
     * @param model The model that holds data for the view.
     * @return The name of the view template (Thymeleaf template) to be rendered.
     */
    @GetMapping("/organization/edit/{id}")
    public String showEditOrganizationForm(@PathVariable int id, Model model) {
        Organization organization = organizationService.getOrganizationById(id);
        model.addAttribute("organization", organization);
        return "organization-form";
    }

    /**
     * Handles the form submission for adding a new organization or updating an existing one.
     *
     * API Endpoint: POST http://localhost:8080/organization/save
     *
     * @param organization The Organization object populated from the form.
     * @param model The model that holds data for the view.
     * @return Redirect to the list of organizations.
     */
    @PostMapping("/organization/save")
    public String saveOrganization(Organization organization, Model model) {
        if (organization.getId() == null) {
            organizationService.addOrganization(organization);
        } else {
            organizationService.updateOrganization(organization);
        }
        return "redirect:/organizations"; // Redirect to the list of organizations
    }

        @GetMapping("/details/{id}")
        public String displayOrganizationDetails(@PathVariable int id, Model model) {
            Organization organization = organizationService.getOrganizationById(id);
            model.addAttribute("organization", organization);
            return "organization-details";  // name of the Thymeleaf template for individual organization details
        }

    }
