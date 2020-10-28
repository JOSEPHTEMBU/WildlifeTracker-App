package models;

public class Ranger {
    private int id;
    private String name;
    private int badgeNo;
    private String contact;

    public Ranger(String name, int badgeNo, String contact) {
        this.name = name;
        this.badgeNo = badgeNo;
        this.contact = contact;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBadgeNo() {
        return badgeNo;
    }

    public void setBadgeNo(int badgeNo) {
        this.badgeNo = badgeNo;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
