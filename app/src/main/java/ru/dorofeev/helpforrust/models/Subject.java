package ru.dorofeev.helpforrust.models;

public class Subject {

    private long id;
    private String imageUrl;
    private String type;

    public Subject() {
    }

    public Subject(long id, String imageUrl, String type) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
