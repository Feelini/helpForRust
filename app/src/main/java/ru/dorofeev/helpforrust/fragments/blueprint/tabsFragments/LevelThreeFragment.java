package ru.dorofeev.helpforrust.fragments.blueprint.tabsFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.fragments.blueprint.BlueprintFragmentViewModel;
import ru.dorofeev.helpforrust.fragments.blueprint.BlueprintsAdapter;
import ru.dorofeev.helpforrust.models.Blueprint;
import ru.dorofeev.helpforrust.repo.database.CheckedItemEntity;

import java.util.List;

public class LevelThreeFragment extends Fragment {

    @BindView(R.id.tableCount)
    TextView tableCount;
    @BindView(R.id.blueprintList)
    RecyclerView blueprintList;
    @BindView(R.id.exploreBtn)
    Button exploreBtn;
    @BindView(R.id.chanceNumber)
    TextView chanceNumber;
    @BindView(R.id.blueprintName)
    TextView blueprintName;
    @BindView(R.id.deleteBtn)
    ImageView deleteBtn;
    @BindView(R.id.adView)
    AdView adView;
    private static LevelThreeFragment instance;
    private static BlueprintFragmentViewModel viewModel;
    private BlueprintsAdapter adapter;
    private List<Blueprint> blueprints;
    private Unbinder unbinder;
    private int selected = -1;
    private CheckedItemEntity selectedItem;

    public static LevelThreeFragment getInstance(){
        if (instance == null) {
            instance = new LevelThreeFragment();
        }
        return instance;
    }

    public static LevelThreeFragment getInstance(BlueprintFragmentViewModel getViewModel) {
        viewModel = getViewModel;
        if (instance == null) {
            instance = new LevelThreeFragment();
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        viewModel.getBlueprints().observe(getViewLifecycleOwner(),
                blueprints1 -> {
                    if (blueprints1 != null && blueprints1.size() > 0) {
                        blueprints = blueprints1;
                        showBlueprintList(blueprints1);
                    }
                });
        return inflater.inflate(R.layout.fragment_level_three, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                adView.setVisibility(View.VISIBLE);
            }
        });

        exploreBtn.setOnClickListener(v -> {
            if (selected != -1) {
                if (exploreBtn.getText().equals(getString(R.string.remove))) {
                    viewModel.deleteCheckedItem(selectedItem.getId(), "three");
                    exploreBtn.setText(R.string.explore);
                } else {
                    viewModel.saveNewItem(selected, "three");
                    exploreBtn.setText(getString(R.string.remove));
                }
            }
        });

        deleteBtn.setOnClickListener(v -> showAlertDialog());

        viewModel.fetchBlueprints("Level_3");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private void showBlueprintList(List<Blueprint> blueprints) {
        adapter = new BlueprintsAdapter(blueprints, getContext(), "Three");
        viewModel.getCheckedItems().observe(getViewLifecycleOwner(), checkedItemEntities -> {
            adapter.setCheckedItems(checkedItemEntities);
            selectedItem = adapter.getCurrentItem(selected);
            setChance(blueprints.size(), adapter.getBlueprintsCount());
            adapter.notifyDataSetChanged();
        });
        viewModel.fetchCheckedItems("three");
        blueprintList.setAdapter(adapter);
        blueprintList.setLayoutManager(new GridLayoutManager(getContext(), 5));
    }

    private void setChance(int size, int numberSelected) {
        double result = 1.0 / (size - numberSelected) * 100;
        if (size - numberSelected == 0){
            chanceNumber.setText(R.string.all_explored);
        } else {
            chanceNumber.setText(String.format("%.2f%%", result));
        }
    }

    public void onBlueprintClick(int position, Blueprint blueprint) {
        selected = position;
        adapter.setSelected(position);
        adapter.notifyDataSetChanged();
        tableCount.setText("x" + blueprint.getValue_1());
        blueprintName.setText(blueprint.getName());
        if (adapter.isAdded(position)){
            selectedItem = adapter.getCurrentItem(position);
            exploreBtn.setText(getString(R.string.remove));
        } else {
            exploreBtn.setText(R.string.explore);
        }
    }

    private void showAlertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(R.string.dialog_message)
                .setTitle(R.string.dialog_title);
        builder.setPositiveButton(R.string.dialog_ok, (dialog, id) -> {
            viewModel.deleteAllItems("three");
            exploreBtn.setText(R.string.explore);
        });
        builder.setNegativeButton(R.string.dialog_cancel, (dialog, id) -> {
            // User cancelled the dialog
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
