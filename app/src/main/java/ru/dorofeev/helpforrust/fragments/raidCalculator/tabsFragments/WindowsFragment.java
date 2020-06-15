package ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import ru.dorofeev.helpforrust.models.Subject;

public class WindowsFragment extends BaseFragment {

    private static WindowsFragment instance;
    private static List<Subject> subjects;

    public static WindowsFragment getInstance() {
        if (instance == null) {
            instance = new WindowsFragment();
        }
        return instance;
    }

    public static WindowsFragment getInstance(List<Subject> subjectList) {
        subjects = subjectList;
        if (instance == null) {
            instance = new WindowsFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setSubjectList(subjects);
    }
}
