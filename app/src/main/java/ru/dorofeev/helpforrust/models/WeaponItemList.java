package ru.dorofeev.helpforrust.models;

import java.util.List;

public class WeaponItemList {

    private WeaponsWithValue weaponsWithValue;
    private List<ItemWithValue> itemWithValues;
    private List<ItemWithValue> itemCompoundWithValues;

    public WeaponItemList(WeaponsWithValue weaponsWithValue, List<ItemWithValue> itemWithValues, List<ItemWithValue> itemCompoundWithValues) {
        this.weaponsWithValue = weaponsWithValue;
        this.itemWithValues = itemWithValues;
        this.itemCompoundWithValues = itemCompoundWithValues;
    }

    public WeaponsWithValue getWeaponsWithValue() {
        return weaponsWithValue;
    }

    public void setWeaponsWithValue(WeaponsWithValue weaponsWithValue) {
        this.weaponsWithValue = weaponsWithValue;
    }

    public List<ItemWithValue> getItemWithValues() {
        return itemWithValues;
    }

    public void setItemWithValues(List<ItemWithValue> itemWithValues) {
        this.itemWithValues = itemWithValues;
    }

    public List<ItemWithValue> getItemCompoundWithValues() {
        return itemCompoundWithValues;
    }

    public void setItemCompoundWithValues(List<ItemWithValue> itemCompoundWithValues) {
        this.itemCompoundWithValues = itemCompoundWithValues;
    }
}
