-- Sample data for Locations table
INSERT INTO Locations (name, description, address, latitude, longitude) VALUES 
('Gotham City', 'Dark and broody city', '123 Bat Lane', 40.7128, -74.0060),
('Metropolis', 'City of hope', '234 Super Ave', 40.7608, -111.8910),
('Stark Tower', 'High-tech skyscraper in New York', '456 Iron Rd', 40.7306, -73.9352),
('X-Mansion', 'Home and school for mutants', '1407 Graymalkin Lane, North Salem', 41.3325, -73.6454),
('Asgard', 'Ethereal realm of the Norse gods', 'Above the Rainbow Bridge', 60.4720, 8.4689),
('SHIELD Helicarrier', 'Mobile base of SHIELD', 'Above international waters', 0, 0),
('Wakanda', 'Hidden African nation', 'Central Africa', -4.0383, 21.7587);

-- Sample data for Heroes table
INSERT INTO Heroes (name, description, superpower) VALUES
('Batman', 'Dark Knight of Gotham', 'Detective skills & gadgets'),
('Superman', 'Man of Steel', 'Flight, strength, x-ray vision'),
('Iron Man', 'Billionaire philanthropist', 'Powered Armor Suit'),
('Storm', 'The Weather Goddess of X-Men', 'Weather manipulation, magic and witchcraft, combat, thievery'),
('Wolverine', 'Mutant with adamantium skeleton', 'Regeneration & adamantium claws'),
('Captain America', 'Sentinel of Liberty', 'Super strength & vibranium shield'),
('Thor', 'God of Thunder', 'Control over lightning & Mjolnir'),
('Black Panther', 'King of Wakanda', 'Enhanced senses & strength, Vibranium suit');

-- Sample data for Organizations table
INSERT INTO Organizations (name, description, address_contact_info) VALUES
('Justice League', 'Team of superheroes', 'Watchtower, Space'),
('The Avengers', 'Earth''s mightiest heroes', '8901 Avengers Lane, NY'),
('League of Shadows', 'Group of assassins', 'Unknown'),
('X-Men', 'Team of mutants fighting for peace', 'X-Mansion, 1407 Graymalkin Lane, North Salem');

-- Sample data for Sightings table
INSERT INTO Sightings (hero_id, location_id, date) VALUES
(1, 1, '2023-09-10'),  -- Batman sighted in Gotham
(2, 2, '2023-09-10'),  -- Superman sighted in Metropolis
(3, 3, '2023-09-11'),  -- Iron Man sighted at Stark Tower
(4, 1, '2023-09-11'),  -- Storm sighted in Gotham
(5, 2, '2023-09-11'),  -- Wolverine sighted in Metropolis
(6, 3, '2023-09-12'),  -- Captain America sighted at Stark Tower
(7, 1, '2023-09-12'),  -- Thor sighted in Gotham
(8, 7, '2023-09-13');  -- Black Panther sighted in Wakanda

-- Sample data for Hero_Organization bridge table
INSERT INTO Hero_Organization (hero_id, organization_id) VALUES
(1, 1),  -- Batman is part of Justice League
(2, 1),  -- Superman is part of Justice League
(3, 2),  -- Iron Man is part of The Avengers
(4, 3),  -- Storm is part of X-Men
(5, 3),  -- Wolverine is part of X-Men
(6, 2),  -- Captain America is part of The Avengers
(7, 2),  -- Thor is part of The Avengers
(8, 2);  -- Black Panther is part of The Avengers
