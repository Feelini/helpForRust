package ru.dorofeev.helpforrust.fragments.raidCalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

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
import ru.dorofeev.helpforrust.models.ItemCompoundWithValue;
import ru.dorofeev.helpforrust.models.ItemWeapon;
import ru.dorofeev.helpforrust.models.ItemWithValue;
import ru.dorofeev.helpforrust.models.RaidCalculatorListItem;
import ru.dorofeev.helpforrust.models.Subject;
import ru.dorofeev.helpforrust.models.Weapons;
import ru.dorofeev.helpforrust.models.WeaponsSubject;
import ru.dorofeev.helpforrust.models.WeaponsWithValue;
import ru.dorofeev.helpforrust.models.allDb.Db;
import ru.dorofeev.helpforrust.models.allDb.RaidCalculatorList;
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
    @BindView(R.id.buttonMinus)
    ImageButton buttonMinus;
    @BindView(R.id.buttonPlus)
    ImageButton buttonPlus;
    @BindView(R.id.multiplier)
    TextView multiplier;
    private RaidCalculatorFragmentViewModel viewModel;
    private static RaidCalculatorFragment instance;
    private Unbinder unbinder;
    private WeaponsAdapter adapter;
    private List<WeaponsSubject> weaponsSubjects;
    private RaidCalculatorList raidCalculatorList;
    private List<ItemWeapon> itemWeapons;
    private List<ItemWithValue> itemWithValues;
    private List<ItemCompound> itemCompounds;
    private List<ItemCompoundWithValue> itemCompoundWithValues;
    private List<RaidCalculatorListItem> raidCalculatorListItems;
    private List<WeaponsWithValue> weaponsWithValues = new ArrayList<>();

    public static RaidCalculatorFragment getInstance() {
        if (instance == null) {
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
        viewModel.getRaidCalculator().observe(getViewLifecycleOwner(), raidCalculatorList -> {
            this.raidCalculatorList = raidCalculatorList;
            getSubjectsType(raidCalculatorList.getSubject());
            showDoorsFragment();
        });

        return inflater.inflate(R.layout.fragment_raid_calculator, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        viewModel.fetchRaidCalculator();

        tabsRaidCalculator.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
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

        buttonMinus.setOnClickListener(v -> {
            if (adapter != null) {
                int multiplierValue = Integer.parseInt(multiplier.getText().toString());
                if (multiplierValue > 1) {
                    multiplierValue--;
                    multiplier.setText(String.format("%d", multiplierValue));
                    adapter.setMultiplier(multiplierValue);
                    adapter.notifyDataSetChanged();
                }
            }
        });
        buttonPlus.setOnClickListener(v -> {
            if (adapter != null) {
                int multiplierValue = Integer.parseInt(multiplier.getText().toString());
                multiplierValue++;
                multiplier.setText(String.format("%d", multiplierValue));
                adapter.setMultiplier(multiplierValue);
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private void getSubjectsType(List<Subject> subjects) {
        for (Subject subject : subjects) {
            if (subject != null) {
                switch (subject.getType()) {
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
    }

    private void showDoorsFragment() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tabsSubjectList, DoorsFragment.getInstance(subjectsDoors), IronFragment.class.getName())
                .commit();
    }

    private void showWallsFragment() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tabsSubjectList, WallsFragment.getInstance(subjectsWalls), WallsFragment.class.getName())
                .commit();
    }

    private void showWindowsFragment() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tabsSubjectList, WindowsFragment.getInstance(subjectsWindows), WindowsFragment.class.getName())
                .commit();
    }

    private void showOtherFragment() {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.tabsSubjectList, OtherFragment.getInstance(subjectsOther), OtherFragment.class.getName())
                .commit();
    }

    private void showWeaponsList(List<RaidCalculatorListItem> raidCalculatorListItems, int multiplier) {
        adapter = new WeaponsAdapter(raidCalculatorListItems, getContext(), multiplier);
        weaponsList.setAdapter(adapter);
        weaponsList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    public void onSubjectClick(int position, String type, Subject subject) {
        int multiplierValue = Integer.parseInt(multiplier.getText().toString());
        raidCalculatorListItems = new ArrayList<>();
        weaponsSubjects = getWeaponSubject(subject.getId(), raidCalculatorList.getWeaponsSubject());
        weaponsWithValues = getWeaponsWithValue(weaponsSubjects, raidCalculatorList.getWeapons());
        for (WeaponsWithValue weaponsWithValue : weaponsWithValues) {
            itemWeapons = getItemsWeapon(weaponsWithValue, raidCalculatorList.getItemsWeapons());
            itemWithValues = getItemsWithValue(itemWeapons, raidCalculatorList.getItems());
            itemCompounds = getItemsCompound(itemWithValues, raidCalculatorList.getItemsCompound());
            itemCompoundWithValues = getItemsCompoundWithValue(itemCompounds, raidCalculatorList.getItems());
            raidCalculatorListItems.add(new RaidCalculatorListItem(weaponsWithValue, itemWithValues, itemCompoundWithValues));
        }

        showWeaponsList(raidCalculatorListItems, multiplierValue);

        switch (type) {
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

    private List<ItemCompoundWithValue> getItemsCompoundWithValue(List<ItemCompound> itemCompounds, List<Item> items) {
        List<ItemCompoundWithValue> itemCompoundWithValueList = new ArrayList<>();
        for (ItemCompound itemCompound : itemCompounds) {
            for (Item item : items) {
                if (item != null && itemCompound != null) {
                    if (itemCompound.getItems_compound_id() == item.getId()) {
                        itemCompoundWithValueList.add(new ItemCompoundWithValue(item, itemCompound.getValue_compound()));
                    }
                }
            }
        }
        return itemCompoundWithValueList;
    }

    private List<ItemCompound> getItemsCompound(List<ItemWithValue> itemWithValues, List<ItemCompound> itemsCompounds) {
        List<ItemCompound> itemCompoundList = new ArrayList<>();
        for (ItemWithValue itemWithValue : itemWithValues) {
            for (ItemCompound itemCompound : itemsCompounds) {
                if (itemCompound != null && itemWithValue != null) {
                    if (itemWithValue.getItem().getId() == itemCompound.getItems_id()) {
                        ItemCompound itemCompound1 = new ItemCompound(itemCompound.getId(),
                                itemCompound.getItems_compound_id(), itemCompound.getItems_id(),
                                itemCompound.getValue_compound() * itemWithValue.getValue());
                        itemCompoundList.add(itemCompound1);
                    }
                }
            }
        }
        return itemCompoundList;
    }

    private List<ItemWithValue> getItemsWithValue(List<ItemWeapon> itemWeapons, List<Item> items) {
        List<ItemWithValue> itemWithValues = new ArrayList<>();
        for (ItemWeapon itemWeapon : itemWeapons) {
            for (Item item : items) {
                if (item != null && itemWeapon != null) {
                    if (itemWeapon.getItems_id() == item.getId()) {
                        itemWithValues.add(new ItemWithValue(item, itemWeapon.getValue()));
                    }
                }
            }
        }
        return itemWithValues;
    }

    private List<ItemWeapon> getItemsWeapon(WeaponsWithValue weaponsWithValue, List<ItemWeapon> itemsWeapons) {
        List<ItemWeapon> itemWeaponList = new ArrayList<>();
        for (ItemWeapon itemWeapon : itemsWeapons) {
            if (itemWeapon != null && weaponsWithValue != null) {
                if (weaponsWithValue.getWeapons().getId() == itemWeapon.getWeapons_id()) {
                    ItemWeapon itemWeapon1 = new ItemWeapon(itemWeapon.getId(),
                            itemWeapon.getItems_id(),
                            itemWeapon.getValue() * weaponsWithValue.getValue(),
                            itemWeapon.getWeapons_id());
                    itemWeaponList.add(itemWeapon1);
                }
            }
        }

        return itemWeaponList;
    }

    private List<WeaponsWithValue> getWeaponsWithValue(List<WeaponsSubject> weaponsSubjects, List<Weapons> weapons) {
        List<WeaponsWithValue> weaponsWithValues = new ArrayList<>();
        for (WeaponsSubject weaponsSubject : weaponsSubjects) {
            for (Weapons weapon : weapons) {
                if (weapon != null && weaponsSubject != null) {
                    if (weaponsSubject.getWeapons_id() == weapon.getId()) {
                        weaponsWithValues.add(new WeaponsWithValue(weapon, weaponsSubject.getValue()));
                    }
                }
            }
        }
        return weaponsWithValues;
    }

    private List<WeaponsSubject> getWeaponSubject(long id, List<WeaponsSubject> weaponsSubjects) {
        List<WeaponsSubject> weaponsSubjectsById = new ArrayList<>();
        for (WeaponsSubject weaponsSubject : weaponsSubjects) {
            if (weaponsSubject != null) {
                if (weaponsSubject.getSubject_id() == id) {
                    weaponsSubjectsById.add(weaponsSubject);
                }
            }
        }
        return weaponsSubjectsById;
    }
}
