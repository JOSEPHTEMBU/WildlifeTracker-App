CREATE DATABASE wildlife;

\c wildlife

CREATE TABLE sightings(
    id SERIAL PRIMARY KEY,
    animalName VARCHAR,
    animalAge INTEGER,
    animalHealth VARCHAR,
    rangerId INTEGER,
    type VARCHAR,
    locationId INTEGER,
    dateSighted TIMESTAMP
);

CREATE TABLE rangers(
    id SERIAL PRIMARY KEY,
    name VARCHAR NOT NULL,
    badgeNo INTEGER NOT NULL,
    contact VARCHAR NOT NULL
);

CREATE TABLE locations(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);