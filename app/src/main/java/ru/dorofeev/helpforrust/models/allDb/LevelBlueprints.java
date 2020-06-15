package ru.dorofeev.helpforrust.models.allDb;

import java.util.List;

import ru.dorofeev.helpforrust.models.Blueprint;

public class LevelBlueprints {
    private List<Blueprint> Level_1;
    private List<Blueprint> Level_2;
    private List<Blueprint> Level_3;

    public LevelBlueprints(List<Blueprint> level_1, List<Blueprint> level_2, List<Blueprint> level_3) {
        Level_1 = level_1;
        Level_2 = level_2;
        Level_3 = level_3;
    }

    public List<Blueprint> getLevel_1() {
        return Level_1;
    }

    public void setLevel_1(List<Blueprint> level_1) {
        Level_1 = level_1;
    }

    public List<Blueprint> getLevel_2() {
        return Level_2;
    }

    public void setLevel_2(List<Blueprint> level_2) {
        Level_2 = level_2;
    }

    public List<Blueprint> getLevel_3() {
        return Level_3;
    }

    public void setLevel_3(List<Blueprint> level_3) {
        Level_3 = level_3;
    }
}
