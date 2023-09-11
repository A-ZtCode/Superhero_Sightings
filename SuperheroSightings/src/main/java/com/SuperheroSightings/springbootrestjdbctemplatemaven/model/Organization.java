package com.SuperheroSightings.springbootrestjdbctemplatemaven.model;

import java.util.List;

public class Organization {
    private int id;
    private String name;
    private String description;
    private String addressContactInfo;
    private List<Hero> members; // Heroes affiliated with this organization

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

    public String getAddressContactInfo() {
        return addressContactInfo;
    }

    public void setAddressContactInfo(String addressContactInfo) {
        this.addressContactInfo = addressContactInfo;
    }

    public List<Hero> getMembers() {
        return members;
    }

    public void setMembers(List<Hero> members) {
        this.members = members;
    }
}
