package ru.dorofeev.helpforrust.fragments.raidCalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.fragments.furnace.tabsFragments.IronFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments.DoorsFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments.OtherFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments.WallsFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.tabsFragments.WindowsFragment;
import ru.dorofeev.helpforrust.models.Item;
import ru.dorofeev.helpforrust.models.ItemCompound;
import ru.dorofeev.helpforrust.models.ItemWeapon;
import ru.dorofeev.helpforrust.models.ItemWithValue;
import ru.dorofeev.helpforrust.models.Subject;
import ru.dorofeev.helpforrust.models.WeaponItemList;
import ru.dorofeev.helpforrust.models.Weapons;
import ru.dorofeev.helpforrust.models.WeaponsSubject;
import ru.dorofeev.helpforrust.models.WeaponsWithValue;
import ru.dorofeev.helpforrust.models.allDb.Db;
import ru.dorofeev.helpforrust.utils.Helper;
import ru.dorofeev.helpforrust.utils.ViewModelFactory;

public class RaidCalculatorFragment extends Fragment {

    private List<Subject> subjectsDoors = new ArrayList<>();
    private List<Subject> subjectsWalls = new ArrayList<>();
    private List<Subject> subjectsWindows = new ArrayList<>();
    private List<Subject> subjectsOther = new ArrayList<>();
    @BindView(R.id.tabsRaidCalculator)
    TabLayout tabsRaidCalculator;
    @BindView(R.id.weaponsList)
    RecyclerView weaponsList;
    private RaidCalculatorFragmentViewModel viewModel;
    private static RaidCalculatorFragment instance;
    private Unbinder unbinder;
    private WeaponsAdapter adapter;
    private List<Weapons> weapons;
    private List<ItemWeapon> itemWeapons;
    private List<ItemCompound> itemCompounds;
    private List<Item> items;
    private List<WeaponsWithValue> weaponsWithValues = new ArrayList<>();
    private Db db;

    public static RaidCalculatorFragment getInstance(){
        if (instance == null){
            instance = new RaidCalculatorFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewModelFactory viewModelFactory = new ViewModelFactory(getActivity().getApplication());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(RaidCalculatorFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel.getSubjects().observe(getViewLifecycleOwner(), subjects1 -> {
            getSubjectsType(subjects1);
            showDoorsFragment();
        });
        viewModel.getWeapons().observe(getViewLifecycleOwner(), weapons1 -> {
            weapons = weapons1;
        });
//        viewModel.getWeaponsSubject().observe(getViewLifecycleOwner(), weaponsSubjects -> {
//            weaponsWithValues = getWeaponsWithValue(weaponsSubjects);
//            List<WeaponItemList> weaponItemLists = Helper.getInstance().getWeaponsItemsList(weaponsSubjects, weapons, items, itemWeapons, itemCompounds);
//            showWeaponsList(weaponsWithValues);
//        });
        viewModel.getDb().observe(getViewLifecycleOwner(), db ->
                this.db = db
        );
        viewModel.getItemWeapons().observe(getViewLifecycleOwner(), itemWeapons1 -> itemWeapons = itemWeapons1);
        viewModel.getItemCompounds().observe(getViewLifecycleOwner(), itemCompounds1 -> itemCompounds = itemCompounds1);
        viewModel.getItems().observe(getViewLifecycleOwner(), items1 -> items = items1);

        return inflater.inflate(R.layout.fragment_raid_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        viewModel.fetchSubjects();
        viewModel.fetchWeapons();
        viewModel.fetchItemWeapons();
        viewModel.fetchItemCompounds();
        viewModel.fetchItems();
        viewModel.fetchDb();

        tabsRaidCalculator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        showDoorsFragment();
                        break;
                    case 1:
                        showWallsFragment();
                        break;
                    case 2:
                        showWindowsFragment();
                        break;
                    case 3:
                        showOtherFragment();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null){
            unbinder.unbind();
        }
    }

    private List<WeaponsWithValue> getWeaponsWithValue(List<WeaponsSubject> weaponsSubjects){
        List<WeaponsWithValue> weaponsWithValues = new ArrayList<>();
        List<ItemWithValue> itemWithValues = new ArrayList<>();
        for (WeaponsSubject weaponsSubject: weaponsSubjects){
            for (ItemWeapon itemWeapon: itemWeapons){
                if (itemWeapon.getWeapons_id() == weaponsSubject.getWeapons_id()){
                    itemWithValues.add(new ItemWithValue(items.get((int) itemWeapon.getItems_id()), itemWeapon.getValue()));
                }
            }
            weaponsWithValues.add(new WeaponsWithValue(weapons.get((int) weaponsSubject.getWeapons_id() - 1), weaponsSubject.getValue()));;
        }
        return weaponsWithValues;
    }

    private void getSubjectsType(List<Subject> subjects){
        for (Subject subject: subjects){
            switch (subject.getType()){
                case "Двери":
                    subjectsDoors.add(subject);
                    break;
                case "Окна":
                    subjectsWindows.add(subject);
                    break;
                case "Стены":
                    subjectsWalls.add(subject);
                    break;
                case "Другое":
                    subjectsOther.add(subject);
                    break;
            }
        }
    }

    private void showDoorsFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tabsSubjectList, DoorsFragment.getInstance(subjectsDoors), IronFragment.class.getName())
                .commit();
    }

    private void showWallsFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tabsSubjectList, WallsFragment.getInstance(subjectsWalls), WallsFragment.class.getName())
                .commit();
    }

    private void showWindowsFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tabsSubjectList, WindowsFragment.getInstance(subjectsWindows), WindowsFragment.class.getName())
                .commit();
    }

    private void showOtherFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tabsSubjectList, OtherFragment.getInstance(subjectsOther), OtherFragment.class.getName())
                .commit();
    }

    private void showWeaponsList(List<WeaponsWithValue> weaponsWithValues){
        adapter = new WeaponsAdapter(weaponsWithValues, getContext());
        weaponsList.setAdapter(adapter);
        weaponsList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    public void onSubjectClick(int position, String type, Subject subject) {
        viewModel.fetchWeaponsSubject(subject.getId());
        switch (type){
            case "Двери":
                DoorsFragment.getInstance().setSelectedItem(position);
                break;
            case "Окна":
                WindowsFragment.getInstance().setSelectedItem(position);
                break;
            case "Стены":
                WallsFragment.getInstance().setSelectedItem(position);
                break;
            case "Другое":
                OtherFragment.getInstance().setSelectedItem(position);
                break;
        }
    }
}
