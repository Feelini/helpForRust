package ru.dorofeev.helpforrust.models;

public class InfoListItem {

    private long id;
    private String iconUrl;
    private String name;

    public InfoListItem() {
    }

    public InfoListItem(long id, String iconUrl, String name) {
        this.id = id;
        this.iconUrl = iconUrl;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
