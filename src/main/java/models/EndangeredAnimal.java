package models;

public class EndangeredAnimal extends Animal{

    private String health;
    private int age;


    public EndangeredAnimal(int id, String name, String health, int age) {
        super(id, name);
        this.health = health;
        this.age = age;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}

