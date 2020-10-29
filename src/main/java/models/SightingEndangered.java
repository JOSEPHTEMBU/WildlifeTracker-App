
package models;

public class SightingEndangered extends Sighting{

    private int animalAge;
    private String animalHealth;
    private static final String TYPE ="Endangered";

    public int getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(int animalAge) {
        this.animalAge = animalAge;
    }

    public String getAnimalHealth() {
        return animalHealth;
    }

    public void setAnimalHealth(String animalHealth) {
        this.animalHealth = animalHealth;
    }

    public SightingEndangered(String animalName, int animalAge, String animalHealth, int rangerId, int locationId) {
        super(animalName, rangerId, locationId);
        this.animalAge = animalAge;
        this.animalHealth = animalHealth;
        this.setType(TYPE);
    }
}
