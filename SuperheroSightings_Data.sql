-- Sample data for Locations table
INSERT INTO Locations (name, description, address, latitude, longitude) VALUES 
('Gotham City', 'Dark and broody city', '123 Bat Lane', 40.7128, -74.0060),
('Metropolis', 'City of hope', '234 Super Ave', 40.7608, -111.8910),
('Stark Tower', 'High-tech skyscraper in New York', '456 Iron Rd', 40.7306, -73.9352);

-- Sample data for Heroes table
INSERT INTO Heroes (name, description, superpower) VALUES
('Batman', 'Dark Knight of Gotham', 'Detective skills & gadgets'),
('Superman', 'Man of Steel', 'Flight, strength, x-ray vision'),
('Iron Man', 'Billionaire philanthropist', 'Powered Armor Suit');

-- Sample data for Organizations table
INSERT INTO Organizations (name, description, address_contact_info) VALUES
('Justice League', 'Team of superheroes', 'Watchtower, Space'),
('The Avengers', 'Earth''s mightiest heroes', '8901 Avengers Lane, NY'),
('League of Shadows', 'Group of assassins', 'Unknown');

-- Sample data for Sightings table
INSERT INTO Sightings (hero_id, location_id, date) VALUES
(1, 1, '2023-09-10'),  -- Batman sighted in Gotham
(2, 2, '2023-09-10'),  -- Superman sighted in Metropolis
(3, 3, '2023-09-11');  -- Iron Man sighted at Stark Tower

-- Sample data for Hero_Organization bridge table
INSERT INTO Hero_Organization (hero_id, organization_id) VALUES
(1, 1),  -- Batman is part of Justice League
(2, 1),  -- Superman is part of Justice League
(3, 2);  -- Iron Man is part of The Avengers
