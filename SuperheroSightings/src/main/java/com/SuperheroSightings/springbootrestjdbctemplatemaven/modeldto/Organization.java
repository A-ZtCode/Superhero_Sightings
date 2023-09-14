package com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Organization {
    private int id;
    private String name;
    private String description;
    private String addressContactInfo;
    private List<Hero> members; // Heroes affiliated with this organization
}
