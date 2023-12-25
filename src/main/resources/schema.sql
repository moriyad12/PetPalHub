
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
create table user
(
    id           int         not null auto_increment,
    phone_number varchar(20),
    role         int         not null,
    email        varchar(50) not null unique,
    password     varchar(50) not null,
    first_name   varchar(50) not null,
    last_name    varchar(50) not null,
    gender       int         not null,
    primary key (id)
);

create table adopter
(
    id int not null,
    primary key (id),
    foreign key (id) references user (id)
);

create table staff
(
    id int not null,
    primary key (id),
    foreign key (id) references user (id)
);

create table manager
(
    id int not null,
    primary key (id),
    foreign key (id) references user (id)
);
