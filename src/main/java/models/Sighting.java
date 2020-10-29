package models;

import java.sql.Timestamp;
import java.util.Date;

public class Sighting {

    private int id;
    private String animalName;
    private int rangerId;
    private int locationId;
    private String type;
    private Timestamp dateSighted;
    private static final String TYPE ="Non-Endangered";

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Timestamp getDateSighted() {
        return dateSighted;
    }

    public void setDateSighted(Timestamp dateSighted) {
        this.dateSighted = dateSighted;
    }



    public Sighting(String animalName, int rangerId, int locationId) {
        this.animalName = animalName;
        this.rangerId = rangerId;
        this.locationId = locationId;
        this.type = TYPE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAnimalName() {
        return animalName;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public int getRangerId() {
        return rangerId;
    }

    public void setRangerId(int rangerId) {
        this.rangerId = rangerId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
}
