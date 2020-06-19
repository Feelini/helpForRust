package ru.dorofeev.helpforrust.fragments.blueprint;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.fragments.blueprint.tabsFragments.LevelOneFragment;
import ru.dorofeev.helpforrust.fragments.blueprint.tabsFragments.LevelThreeFragment;
import ru.dorofeev.helpforrust.fragments.blueprint.tabsFragments.LevelTwoFragment;
import ru.dorofeev.helpforrust.models.Blueprint;
import ru.dorofeev.helpforrust.utils.ViewModelFactory;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.tabs.TabLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class BlueprintFragment extends Fragment {

    @BindView(R.id.tabsBlueprint)
    TabLayout tabsBlueprint;
    private BlueprintFragmentViewModel viewModel;
    private Unbinder unbinder;
    private static BlueprintFragment instance;
    private InterstitialAd interstitialAd;
    private boolean isAdShowing = false;

    public static BlueprintFragment getInstance(){
        if (instance == null){
            instance = new BlueprintFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAdShowing = false;
        ViewModelFactory viewModelFactory = new ViewModelFactory(getActivity().getApplication());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(BlueprintFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_blueprint, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // test
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // release
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        tabsBlueprint.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        showLevelOneFragment();
                        break;
                    case 1:
                        showLevelTwoFragment();
                        break;
                    case 2:
                        showLevelThreeFragment();
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

        showLevelOneFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private void showLevelOneFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.containerViewPager, LevelOneFragment.getInstance(viewModel), LevelOneFragment.class.getName())
                .commit();
    }

    private void showLevelTwoFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.containerViewPager, LevelTwoFragment.getInstance(viewModel), LevelOneFragment.class.getName())
                .commit();
    }

    private void showLevelThreeFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.containerViewPager, LevelThreeFragment.getInstance(viewModel), LevelOneFragment.class.getName())
                .commit();
    }

    public void onBlueprintClick(Blueprint blueprint, int position, String type) {
        switch (type){
            case "One":
                LevelOneFragment.getInstance().onBlueprintClick(position, blueprint);
                break;
            case "Two":
                LevelTwoFragment.getInstance().onBlueprintClick(position, blueprint);
                break;
            case "Three":
                LevelThreeFragment.getInstance().onBlueprintClick(position, blueprint);
                break;
        }
        if (interstitialAd.isLoaded() && !isAdShowing){
            interstitialAd.show();
            isAdShowing = true;
        }
    }
}
