package com.SuperheroSightings.springbootrestjdbctemplatemaven.model;

import lombok.*;

public class Hero {
    private int id;
    private String name;
    private String description;
    private String superpower;

    // Constructor with parameters
    public Hero(int id, String name, String description, String superpower) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.superpower = superpower;
    }

    // Default constructor
    public Hero() {}

    // getters, setters, and other methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSuperpower() {
        return superpower;
    }

    public void setSuperpower(String superpower) {
        this.superpower = superpower;
    }
}
