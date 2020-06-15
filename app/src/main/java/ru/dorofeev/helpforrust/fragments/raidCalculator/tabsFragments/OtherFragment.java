package ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments;

import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.List;

import ru.dorofeev.helpforrust.models.Subject;

public class OtherFragment extends BaseFragment {

    private static OtherFragment instance;
    private static List<Subject> subjects;

    public static OtherFragment getInstance() {
        if (instance == null) {
            instance = new OtherFragment();
        }
        return instance;
    }

    public static OtherFragment getInstance(List<Subject> subjectList) {
        subjects = subjectList;
        if (instance == null) {
            instance = new OtherFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setSubjectList(subjects);
    }
}
