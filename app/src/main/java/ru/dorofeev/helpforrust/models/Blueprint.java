package ru.dorofeev.helpforrust.models;

public class Blueprint {
    private String imageURL;
    private String name;
    private String uid;
    private String value_1;

    public Blueprint(){}

    public Blueprint(String imageUrl, String name, String uid, String value_1) {
        this.imageURL = imageUrl;
        this.name = name;
        this.uid = uid;
        this.value_1 = value_1;
    }

    public String getImageUrl() {
        return imageURL;
    }

    public void setImageUrl(String imageUrl) {
        this.imageURL = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getValue_1() {
        return value_1;
    }

    public void setValue_1(String value_1) {
        this.value_1 = value_1;
    }
}
