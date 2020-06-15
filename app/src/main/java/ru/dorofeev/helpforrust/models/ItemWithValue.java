package ru.dorofeev.helpforrust.models;

public class ItemWithValue {
    private Item item;
    private int value;

    public ItemWithValue(Item item, int value) {
        this.item = item;
        this.value = value;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
