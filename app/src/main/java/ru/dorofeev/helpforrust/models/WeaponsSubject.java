package ru.dorofeev.helpforrust.models;

public class WeaponsSubject {

    private long id;
    private long subject_id;
    private int value;
    private long weapons_id;

    public WeaponsSubject() {
    }

    public WeaponsSubject(long id, long subject_id, int value, long weapons_id) {
        this.id = id;
        this.subject_id = subject_id;
        this.value = value;
        this.weapons_id = weapons_id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(long subject_id) {
        this.subject_id = subject_id;
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
