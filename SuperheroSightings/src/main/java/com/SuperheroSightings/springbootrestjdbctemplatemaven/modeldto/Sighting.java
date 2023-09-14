package com.SuperheroSightings.springbootrestjdbctemplatemaven.modeldto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sighting {
    private int id;
    private Hero hero;
    private Location location;
    private LocalDate date;
}
