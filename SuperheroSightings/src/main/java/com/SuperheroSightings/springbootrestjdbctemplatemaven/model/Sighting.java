package com.SuperheroSightings.springbootrestjdbctemplatemaven.model;

import java.time.LocalDate;

public class Sighting {
    private int id;
    private Hero hero;
    private Location location;
    private LocalDate date;
    // getters, setters, and other methods


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
