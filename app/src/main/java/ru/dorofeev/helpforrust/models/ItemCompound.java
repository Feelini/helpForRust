package ru.dorofeev.helpforrust.models;

public class ItemCompound {
    private long id;
    private long items_compound_id;
    private long items_id;
    private int value_compound;

    public ItemCompound() {
    }

    public ItemCompound(long id, long items_compound_id, long items_id, int value_compound) {
        this.id = id;
        this.items_compound_id = items_compound_id;
        this.items_id = items_id;
        this.value_compound = value_compound;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getItems_compound_id() {
        return items_compound_id;
    }

    public void setItems_compound_id(long items_compound_id) {
        this.items_compound_id = items_compound_id;
    }

    public long getItems_id() {
        return items_id;
    }

    public void setItems_id(long items_id) {
        this.items_id = items_id;
    }

    public int getValue_compound() {
        return value_compound;
    }

    public void setValue_compound(int value_compound) {
        this.value_compound = value_compound;
    }
}
