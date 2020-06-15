package ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import ru.dorofeev.helpforrust.models.Subject;

public class WallsFragment extends BaseFragment {

    private static WallsFragment instance;
    private static List<Subject> subjects;

    public static WallsFragment getInstance() {
        if (instance == null) {
            instance = new WallsFragment();
        }
        return instance;
    }

    public static WallsFragment getInstance(List<Subject> subjectList) {
        subjects = subjectList;
        if (instance == null) {
            instance = new WallsFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setSubjectList(subjects);
    }
}
