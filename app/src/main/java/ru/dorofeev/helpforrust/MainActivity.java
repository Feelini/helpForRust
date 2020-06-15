package ru.dorofeev.helpforrust;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;

import ru.dorofeev.helpforrust.fragments.blueprint.BlueprintFragment;
import ru.dorofeev.helpforrust.fragments.blueprint.BlueprintsAdapter;
import ru.dorofeev.helpforrust.fragments.furnace.FurnaceFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.dorofeev.helpforrust.fragments.purchase.PurchaseFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.RaidCalculatorFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.SubjectAdapter;
import ru.dorofeev.helpforrust.models.Blueprint;
import ru.dorofeev.helpforrust.models.Subject;

public class MainActivity extends AppCompatActivity implements SubjectAdapter.OnSubjectClickListener,
        BlueprintsAdapter.OnBlueprintClickListener {

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    @BindView(R.id.bottomMenu)
    BottomNavigationView bottomMenu;
    private Unbinder unbinder;
    private int currentMenu = R.id.menu_raid_calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        unbinder = ButterKnife.bind(this);

        bottomMenu.setOnNavigationItemSelectedListener(listener);
        bottomMenu.setItemIconTintList(null);

        showRaidCalculatorFragment();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {
        if (item.getItemId() != currentMenu) {
            currentMenu = item.getItemId();
            switch (item.getItemId()) {
                case R.id.menu_raid_calculator:
                    showRaidCalculatorFragment();
                    break;
                case R.id.menu_furnace:
                    showFurnaceFragment();
                    break;
                case R.id.menu_blueprints:
                    showBlueprintsFragment();
                    break;
                case R.id.menu_purchase:
                    showPurchaseFragment();
                    break;
            }
        }
        return true;
    };

    private void showRaidCalculatorFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, RaidCalculatorFragment.getInstance(), RaidCalculatorFragment.class.getName())
                .commit();
    }

    private void showFurnaceFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, FurnaceFragment.getInstance(), FurnaceFragment.class.getName())
                .commit();
    }

    private void showBlueprintsFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, BlueprintFragment.getInstance(), BlueprintFragment.class.getName())
                .commit();
    }

    private void showPurchaseFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainer, PurchaseFragment.getInstance(), PurchaseFragment.class.getName())
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null){
            unbinder.unbind();
        }
    }

    @Override
    public void onSubjectClick(int position, String type, Subject subject) {
        RaidCalculatorFragment.getInstance().onSubjectClick(position, type, subject);
    }

    @Override
    public void onBlueprintClick(Blueprint blueprint, int position, String type) {
        BlueprintFragment.getInstance().onBlueprintClick(blueprint, position, type);
    }
}
