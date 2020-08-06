package ru.dorofeev.helpforrust.fragments.info;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.fragments.furnace.FurnaceFragment;
import ru.dorofeev.helpforrust.fragments.furnace.FurnaceFragmentViewModel;
import ru.dorofeev.helpforrust.fragments.furnace.tabsFragments.IronFragment;
import ru.dorofeev.helpforrust.fragments.furnace.tabsFragments.MvkFragment;
import ru.dorofeev.helpforrust.fragments.furnace.tabsFragments.SulfurFragment;
import ru.dorofeev.helpforrust.fragments.purchase.PurchaseFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.WeaponsAdapter;
import ru.dorofeev.helpforrust.models.Furnace;
import ru.dorofeev.helpforrust.models.InfoListItem;
import ru.dorofeev.helpforrust.models.RaidCalculatorListItem;
import ru.dorofeev.helpforrust.utils.ViewModelFactory;

public class InfoFragment extends Fragment {
    @BindView(R.id.infoList)
    RecyclerView infoList;
    @BindView(R.id.adView)
    AdView adView;
    private InfoAdapter adapter;
    private List<InfoListItem> infoListItems;
    private InfoFragmentViewModel viewModel;
    private Unbinder unbinder;
    private static InfoFragment instance;
    private InterstitialAd interstitialAd;
    private boolean isAdShowing = false;
    private boolean isPay = false;

    public static InfoFragment getInstance(){
        if (instance == null){
            instance = new InfoFragment();
        }
        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!isPay) {
            isAdShowing = false;
        } else {
            isAdShowing = true;
        }
        ViewModelFactory viewModelFactory = new ViewModelFactory(getActivity().getApplication());
        viewModel = new ViewModelProvider(this, viewModelFactory).get(InfoFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (infoListItems.size() == 0 ){
            viewModel.getInfoList().observe(getViewLifecycleOwner(), infoListItems1 -> {
                infoListItems = infoListItems1;
                showInfoItemsList();
            });
        } else {
            showInfoItemsList();
        }
        return inflater.inflate(R.layout.fragment_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        if (!isPay) {
            AdRequest adRequest = new AdRequest.Builder().build();
            adView.loadAd(adRequest);
            adView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    super.onAdLoaded();
                    adView.setVisibility(View.VISIBLE);
                }
            });
        }

        interstitialAd = new InterstitialAd(getContext());
//        interstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712"); // test
        interstitialAd.setAdUnitId("ca-app-pub-9023638698585769/2358102939"); // release
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

        viewModel.fetchInfoList();
    }

    private void showInfoItemsList(List<InfoListItem> infoListItemList) {
        adapter = new InfoAdapter(infoListItemList, getContext());
        infoList.setAdapter(adapter);
        infoList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void removeAds(){
        isPay = true;
//        adView.setVisibility(View.GONE);
    }
}
