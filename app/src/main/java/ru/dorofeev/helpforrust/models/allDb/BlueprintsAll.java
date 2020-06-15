package ru.dorofeev.helpforrust.models.allDb;

public class BlueprintsAll {
    private LevelBlueprints Level;

    public BlueprintsAll(LevelBlueprints level) {
        Level = level;
    }

    public LevelBlueprints getLevel() {
        return Level;
    }

    public void setLevel(LevelBlueprints level) {
        Level = level;
    }
}
