package ru.dorofeev.helpforrust.models;

public class ItemCompoundWithValue {
    private ItemCompound itemCompound;
    private int value;

    public ItemCompoundWithValue(ItemCompound itemCompound, int value) {
        this.itemCompound = itemCompound;
        this.value = value;
    }

    public ItemCompound getItemCompound() {
        return itemCompound;
    }

    public void setItemCompound(ItemCompound itemCompound) {
        this.itemCompound = itemCompound;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
