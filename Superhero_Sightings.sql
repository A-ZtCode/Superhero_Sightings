DROP DATABASE IF EXISTS Superhero_Sightings;

-- Create the Database
CREATE DATABASE Superhero_Sightings;
USE Superhero_Sightings;

-- Drop tables if they exist
DROP TABLE IF EXISTS Hero_Organization;
DROP TABLE IF EXISTS Sightings;
DROP TABLE IF EXISTS Heroes;
DROP TABLE IF EXISTS Organizations;
DROP TABLE IF EXISTS Locations;

-- Create Locations table
CREATE TABLE Locations (
    location_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    address TEXT NOT NULL,
    latitude DECIMAL(9,6) NOT NULL,
    longitude DECIMAL(9,6) NOT NULL
);

-- Create Heroes table
CREATE TABLE Heroes (
    hero_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    superpower VARCHAR(255) NOT NULL
);

-- Create Organizations table
CREATE TABLE Organizations (
    organization_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    address_contact_info TEXT NOT NULL
);

-- Create Sightings table
CREATE TABLE Sightings (
    sighting_id INT AUTO_INCREMENT PRIMARY KEY,
    hero_id INT,
    location_id INT,
    date DATE NOT NULL,
    FOREIGN KEY (hero_id) REFERENCES Heroes(hero_id),
    FOREIGN KEY (location_id) REFERENCES Locations(location_id)
);

-- Create Hero_Organization bridge table
CREATE TABLE Hero_Organization (
    hero_id INT,
    organization_id INT,
    PRIMARY KEY (hero_id, organization_id),
    FOREIGN KEY (hero_id) REFERENCES Heroes(hero_id),
    FOREIGN KEY (organization_id) REFERENCES Organizations(organization_id)
);
