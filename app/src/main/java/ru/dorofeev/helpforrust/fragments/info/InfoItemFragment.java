package ru.dorofeev.helpforrust.fragments.info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.models.ItemOfInfoListItem;

public class InfoItemFragment extends Fragment {
    @BindView(R.id.infoTabList)
    RecyclerView infoTabList;
    @BindView(R.id.adView)
    AdView adView;
    private InfoItemAdapter adapter;
    private static List<ItemOfInfoListItem> itemOfInfoListItem = new ArrayList<>();
    private Unbinder unbinder;
    private static InfoItemFragment instance;
    private boolean isPay = false;

    public static InfoItemFragment getInstance(){
        if (instance == null){
            instance = new InfoItemFragment();
        }
        return instance;
    }

    public static InfoItemFragment getInstance(List<ItemOfInfoListItem> getItemOfInfoListItemAndBuffs){
        if (instance == null){
            instance = new InfoItemFragment();
        }
        itemOfInfoListItem = getItemOfInfoListItemAndBuffs;
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_info_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

//        if (!isPay) {
//            AdRequest adRequest = new AdRequest.Builder().build();
//            adView.loadAd(adRequest);
//            adView.setAdListener(new AdListener() {
//                @Override
//                public void onAdLoaded() {
//                    super.onAdLoaded();
//                    adView.setVisibility(View.VISIBLE);
//                }
//            });
//        }

        showInfoListItemList(itemOfInfoListItem);
    }

    private void showInfoListItemList(List<ItemOfInfoListItem> infoListItemList) {
        adapter = new InfoItemAdapter(infoListItemList, getContext());
        infoTabList.setAdapter(adapter);
        infoTabList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
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
