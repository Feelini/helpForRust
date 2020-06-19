package ru.dorofeev.helpforrust.fragments.furnace;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.fragments.furnace.tabsFragments.IronFragment;
import ru.dorofeev.helpforrust.fragments.furnace.tabsFragments.MvkFragment;
import ru.dorofeev.helpforrust.fragments.furnace.tabsFragments.SulfurFragment;
import ru.dorofeev.helpforrust.models.Furnace;
import ru.dorofeev.helpforrust.utils.ViewModelFactory;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class FurnaceFragment extends Fragment {

    @BindView(R.id.tabsFurnace)
    TabLayout tabsFurnace;
    @BindView(R.id.adView)
    AdView adView;
    private FurnaceFragmentViewModel viewModel;
    private Unbinder unbinder;
    private List<Furnace> furnaceIron = new ArrayList<>();
    private List<Furnace> furnaceSulfur = new ArrayList<>();
    private List<Furnace> furnaceMvk = new ArrayList<>();
    private static FurnaceFragment instance;
    private InterstitialAd interstitialAd;
    private boolean isAdShowing = false;

    public static FurnaceFragment getInstance(){
        if (instance == null){
            instance = new FurnaceFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isAdShowing = false;
        ViewModelFactory viewModelFactory = new ViewModelFactory(getActivity().getApplication());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(FurnaceFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (furnaceIron.size() == 0 || furnaceSulfur.size() == 0 || furnaceMvk.size() == 0){
            viewModel.getFurnace().observe(getViewLifecycleOwner(), furnaces -> {
                getFurnaceType(furnaces);
                showLevelIronFragment();
            });
        } else {
            showLevelIronFragment();
        }
        return inflater.inflate(R.layout.fragment_furnace, container, false);
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

        interstitialAd = new InterstitialAd(getContext());
        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // test
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // release
        interstitialAd.loadAd(new AdRequest.Builder().build());
        interstitialAd.setAdListener(new AdListener(){
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                if (!isAdShowing) {
                    interstitialAd.show();
                    isAdShowing = true;
                }
            }

            @Override
            public void onAdClosed() {
                super.onAdClosed();
                interstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        viewModel.fetchFurnace();

        tabsFurnace.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){
                    case 0:
                        showLevelIronFragment();
                        break;
                    case 1:
                        showLevelSulfurFragment();
                        break;
                    case 2:
                        showLevelMvkFragment();
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
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private void getFurnaceType(List<Furnace> furnaces){
        for (Furnace furnace: furnaces){
            switch (furnace.getType()){
                case "iron":
                    furnaceIron.add(furnace);
                    break;
                case "sulfur":
                    furnaceSulfur.add(furnace);
                    break;
                case "MVK":
                    furnaceMvk.add(furnace);
                    break;
            }
        }
    }

    private void showLevelIronFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.blueprintListContainer, IronFragment.getInstance(furnaceIron), IronFragment.class.getName())
                .commit();
    }

    private void showLevelSulfurFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.blueprintListContainer, SulfurFragment.getInstance(furnaceSulfur), SulfurFragment.class.getName())
                .commit();
    }

    private void showLevelMvkFragment(){
        getChildFragmentManager().beginTransaction()
                .replace(R.id.blueprintListContainer, MvkFragment.getInstance(furnaceMvk), MvkFragment.class.getName())
                .commit();
    }
}
