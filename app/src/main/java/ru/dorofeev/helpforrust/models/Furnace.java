package ru.dorofeev.helpforrust.models;

public class Furnace {

    private String id;
    private String imageUrl;
    private String type;

    public Furnace() {
    }

    public Furnace(String id, String imageUrl, String type) {
        this.id = id;
        this.imageUrl = imageUrl;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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
