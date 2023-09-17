package com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * Represents an organization, either of superheroes.
 *
 * This class provides a data model for an Organization, encapsulating attributes such as
 * name, description, contact address, and the list of members (heroes) affiliated with the
 * organization. The Lombok library annotations are used to automatically generate getter
 * and setter methods, as well as no-args and all-args constructors.
 *
 * @author Your Name (or the relevant author's name)
 * @version 1.0
 * @since 2023-09-11
 */
@NoArgsConstructor  // Lombok annotation to generate a no-args constructor.
@AllArgsConstructor // Lombok annotation to generate a constructor with arguments for all fields.
@Getter             // Lombok annotation to generate getters for all fields.
@Setter             // Lombok annotation to generate setters for all fields.
public class Organization {
    /**
     * The unique identifier for the organization. This is  generated by the database and used as the primary key.
     */
    private int id;

    /**
     * The name of the organization.
     */
    private String name;

    /**
     * A description of the organization. This provides more detailed information about the organization.
     */
    private String description;

    /**
     * The contact information for the organization. This could include the organization's address, phone number, email, etc.
     */
    private String addressContactInfo;

    /**
     * A list of heroes that are members of the organization. Each hero is represented by a Hero object.
     */
    private List<Hero> members; // Heroes affiliated with this organization
}
