package ru.dorofeev.helpforrust.models;

public class WeaponsWithValue {

    private Weapons weapons;
    private int value;

    public WeaponsWithValue(Weapons weapons, int value) {
        this.weapons = weapons;
        this.value = value;
    }

    public Weapons getWeapons() {
        return weapons;
    }

    public void setWeapons(Weapons weapons) {
        this.weapons = weapons;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
