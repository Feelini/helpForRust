package ru.dorofeev.helpforrust.models;

public class ItemCompoundWithValue {
    private Item item;
    private int value;

    public ItemCompoundWithValue(Item item, int value) {
        this.item = item;
        this.value = value;
    }

    public Item getItemCompound() {
        return item;
    }

    public void setItemCompound(Item item) {
        this.item = item;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
