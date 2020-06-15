package ru.dorofeev.helpforrust.models;

import java.util.List;

public class RaidCalculatorListItem {
    private WeaponsWithValue weaponsWithValue;
    List<ItemWithValue> itemWithValues;
    List<ItemCompoundWithValue> itemCompoundWithValues;

    public RaidCalculatorListItem(WeaponsWithValue weaponsWithValue, List<ItemWithValue> itemWithValues, List<ItemCompoundWithValue> itemCompoundWithValues) {
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

    public List<ItemCompoundWithValue> getItemCompoundWithValues() {
        return itemCompoundWithValues;
    }

    public void setItemCompoundWithValues(List<ItemCompoundWithValue> itemCompoundWithValues) {
        this.itemCompoundWithValues = itemCompoundWithValues;
    }
}
