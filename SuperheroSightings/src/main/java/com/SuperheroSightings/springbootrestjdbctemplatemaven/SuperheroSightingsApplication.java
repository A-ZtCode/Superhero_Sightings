package com.SuperheroSightings.springbootrestjdbctemplatemaven;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point of the Superhero Sightings Spring Boot application.
 *
 * This class serves as the entry point for the Spring Boot application. It sets up
 * the Spring context, scans components, and initializes the entire application.
 * By running this class, it will bootstrap the Superhero Sightings application.
 *
 * @author Your Name (or the relevant author's name)
 * @version 1.0
 * @since 2023-09-11
 */
@SpringBootApplication
public class SuperheroSightingsApplication {

	/**
	 * The main method which serves as an entry point for the application.
	 *
	 * @param args command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(SuperheroSightingsApplication.class, args);
	}
}
