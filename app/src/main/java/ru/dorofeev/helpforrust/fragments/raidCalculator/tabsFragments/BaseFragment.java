package ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.fragments.raidCalculator.SubjectAdapter;
import ru.dorofeev.helpforrust.models.Subject;

public class BaseFragment extends Fragment {

    @BindView(R.id.subjectList)
    RecyclerView subjectList;
    private Unbinder unbinder;
    private SubjectAdapter adapter;
    private static List<Subject> subjects;

    protected void setSubjectList(List<Subject> subjects1){
        subjects = subjects1;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_raid_calculator_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        showSubjectList(subjects);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null){
            unbinder.unbind();
        }
    }

    protected void showSubjectList(List<Subject> subjects) {
        if (adapter == null) {
            adapter = new SubjectAdapter(subjects, getContext());
        }
        subjectList.setAdapter(adapter);
        subjectList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
    }

    public void setSelectedItem(int position){
        adapter.setSelectedPosition(position);
        adapter.notifyDataSetChanged();
    }
}
