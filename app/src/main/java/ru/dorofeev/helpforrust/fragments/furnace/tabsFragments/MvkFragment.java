package ru.dorofeev.helpforrust.fragments.furnace.tabsFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.fragments.furnace.FurnaceAdapter;
import ru.dorofeev.helpforrust.models.Furnace;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MvkFragment extends Fragment {

    @BindView(R.id.furnaceList)
    RecyclerView furnaceList;
    private static MvkFragment instance;
    private static List<Furnace> furnaces;
    private Unbinder unbinder;
    private FurnaceAdapter adapter;

    public static MvkFragment getInstance(List<Furnace> furnaceList) {
        furnaces = furnaceList;
        if (instance == null) {
            instance = new MvkFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_furnace_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        showFurnaceList(furnaces);
    }

    private void showFurnaceList(List<Furnace> furnaces) {
        if (adapter == null) {
            adapter = new FurnaceAdapter(furnaces, getContext());
        }
        furnaceList.setAdapter(adapter);
        furnaceList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }
}
