CREATE DATABASE wildlife;

\c wildlife

CREATE TABLE sightings(
    id SERIAL PRIMARY KEY,
    name VARCHAR,
    age INTEGER,
    health VARCHAR,
    rangerId INTEGER,
    locationId INTEGER
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