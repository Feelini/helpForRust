package ru.dorofeev.helpforrust.models;

public class Weapons {

    private long id;
    private String imageUrl;
    private String name;

    public Weapons() {
    }

    public Weapons(long id, String imageUrl, String name) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
