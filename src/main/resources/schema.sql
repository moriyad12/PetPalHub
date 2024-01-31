
-- Drop tables if they exist
DROP TABLE IF EXISTS Pet;
DROP TABLE IF EXISTS Shelter;
DROP TABLE IF EXISTS Location;
DROP TABLE IF EXISTS Adopter;
DROP TABLE IF EXISTS Staff;
DROP TABLE IF EXISTS Manager;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Application;
DROP TABLE IF EXISTS Record;

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
    code VARCHAR(50) DEFAULT '000000',
    FOREIGN KEY (location_id) REFERENCES Location(id)
);

-- Pet Table
CREATE TABLE Pet (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    gender int NOT NULL,
    health_status int NOT NULL,
    availability int NOT NULL,
    breed int,
    behaviour int,
    date_of_birth DATE NOT NULL,
    description TEXT,
    species VARCHAR(255),
    image_path VARCHAR(255),
    vaccine_status int NOT NULL,
    shelter_id INT,
    FOREIGN KEY (shelter_id) REFERENCES Shelter(id)
);

CREATE TABLE Users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    phone_number VARCHAR(20),
    role INT NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender INT NOT NULL,
    profile_picture_path varchar(500),
    enabled BOOLEAN NOT NULL DEFAULT FALSE,
    sign_in_with_email BOOLEAN NOT NULL DEFAULT FALSE
);


CREATE TABLE Adopter (
    id INT PRIMARY KEY,
    FOREIGN KEY (id) REFERENCES Users (id)
);

-- Create Staff table
CREATE TABLE Staff (
    id INT PRIMARY KEY,
    shelter_id INT,
    FOREIGN KEY (id) REFERENCES Users (id),
    FOREIGN KEY (shelter_id) REFERENCES Shelter(id)
);

-- Create Manager table
CREATE TABLE Manager (
    id INT PRIMARY KEY,
    shelter_id INT,
    FOREIGN KEY (id) REFERENCES Users (id),
    FOREIGN KEY (shelter_id) REFERENCES Shelter(id)
);

CREATE TABLE Application (
    pet_id INT,
    adopter_id INT,
    application_date DATE NOT NULL,
    status INT NOT NULL,
    PRIMARY KEY (pet_id, adopter_id),
    FOREIGN KEY (pet_id) REFERENCES Pet(id),
    FOREIGN KEY (adopter_id) REFERENCES Adopter(id)
);

