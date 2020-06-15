package ru.dorofeev.helpforrust.models;

public class ItemWeapon {
    private long id;
    private long items_id;
    private int value;
    private long weapons_id;

    public ItemWeapon() {
    }

    public ItemWeapon(long id, long items_id, int value, long weapons_id) {
        this.id = id;
        this.items_id = items_id;
        this.value = value;
        this.weapons_id = weapons_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItems_id() {
        return items_id;
    }

    public void setItems_id(long items_id) {
        this.items_id = items_id;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public long getWeapons_id() {
        return weapons_id;
    }

    public void setWeapons_id(long weapons_id) {
        this.weapons_id = weapons_id;
    }
}
