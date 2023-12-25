use petpal;
-- Drop tables if they exist
DROP TABLE IF EXISTS Pet;
DROP TABLE IF EXISTS Shelter;
DROP TABLE IF EXISTS Location;

-- Location Table
CREATE TABLE Location (
    id INT PRIMARY KEY AUTO_INCREMENT,
    country VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    address VARCHAR(255)
);

-- Shelter Table
CREATE TABLE Shelter (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
	phone_number int,
    location_id INT,
    FOREIGN KEY (location_id) REFERENCES Location(id),
    UNIQUE KEY unique_location_id (location_id)
);

-- Pet Table
CREATE TABLE Pet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    gender int NOT NULL,
    healthStatus int NOT NULL,
    availability int NOT NULL,
    breed VARCHAR(255),
    behaviour VARCHAR(255),
    dateOfBirth DATE NOT NULL,
    description TEXT,
    species VARCHAR(255),
    imagePath VARCHAR(255),
    vaccineStatus int NOT NULL,
    shelter_id INT,
    FOREIGN KEY (shelter_id) REFERENCES Shelter(id)
);
