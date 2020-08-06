package ru.dorofeev.helpforrust.models;

public class ItemOfInfoListItem {

    private long id;
    private String imageUrl;

    public ItemOfInfoListItem() {
    }

    public ItemOfInfoListItem(long id, String imageUrl) {
        this.id = id;
        this.imageUrl = imageUrl;
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
}
