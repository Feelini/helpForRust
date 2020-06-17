package ru.dorofeev.helpforrust.repo.database;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "checked_items", indices = {@Index(value = {"position", "level"}, unique = true)})
public class CheckedItemEntity {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private int position;
    private String level;

    public CheckedItemEntity(long id, int position, String level) {
        this.id = id;
        this.position = position;
        this.level = level;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
