package ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import ru.dorofeev.helpforrust.models.Subject;

public class DoorsFragment extends BaseFragment {

    private static DoorsFragment instance;
    private static List<Subject> subjects;

    public static DoorsFragment getInstance(){
        if (instance == null) {
            instance = new DoorsFragment();
        }
        return instance;
    }

    public static DoorsFragment getInstance(List<Subject> subjectList) {
        subjects = subjectList;
        if (instance == null) {
            instance = new DoorsFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setSubjectList(subjects);
    }
}
