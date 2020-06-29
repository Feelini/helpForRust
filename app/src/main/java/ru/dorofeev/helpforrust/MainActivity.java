package ru.dorofeev.helpforrust;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.dorofeev.helpforrust.fragments.blueprint.BlueprintFragment;
import ru.dorofeev.helpforrust.fragments.blueprint.BlueprintsAdapter;
import ru.dorofeev.helpforrust.fragments.furnace.FurnaceFragment;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.BillingResult;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.android.billingclient.api.SkuDetailsResponseListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.FirebaseApp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.dorofeev.helpforrust.fragments.purchase.PurchaseFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.RaidCalculatorFragment;
import ru.dorofeev.helpforrust.fragments.raidCalculator.SubjectAdapter;
import ru.dorofeev.helpforrust.models.Blueprint;
import ru.dorofeev.helpforrust.models.Subject;

public class MainActivity extends AppCompatActivity implements SubjectAdapter.OnSubjectClickListener,
        BlueprintsAdapter.OnBlueprintClickListener, PurchaseFragment.RemoveAdsClickListener {

    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;
    @BindView(R.id.bottomMenu)
    BottomNavigationView bottomMenu;
    private Unbinder unbinder;
    private int currentMenu = R.id.menu_raid_calculator;
    private String mSkuId = "remove_ads";
    private BillingClient billingClient;
    private Map<String, SkuDetails> mSkuDetailsMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);

        unbinder = ButterKnife.bind(this);

        initBilling();

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
                .replace(R.id.fragmentContainer, PurchaseFragment.getInstance(this), PurchaseFragment.class.getName())
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

    private void removeAds() {
        BlueprintFragment.getInstance().removeAds();
        FurnaceFragment.getInstance().removeAds();
    }

    private void initBilling() {
        billingClient = BillingClient.newBuilder(this)
                .enablePendingPurchases()
                .setListener((billingResult, list) -> {
                    if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK && list != null) {
                        payComplete();
                    }
                }).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@NonNull BillingResult billingResult) {
                if (billingResult.getResponseCode() == BillingClient.BillingResponseCode.OK) {
                    //below you can query information about products and purchase
                    querySkuDetails(); //query for products
                    List<Purchase> purchasesList = queryPurchases(); //query for purchases

                    //if the purchase has already been made to give the goods
                    for (int i = 0; i < purchasesList.size(); i++) {
                        String purchaseId = purchasesList.get(i).getSku();
                        if (TextUtils.equals(mSkuId, purchaseId)) {
                            payComplete();
                        }
                    }
                }
            }

            @Override
            public void onBillingServiceDisconnected() {
                //here when something went wrong, e.g. no internet connection
            }
        });
    }

    private void querySkuDetails() {
        SkuDetailsParams.Builder skuDetailsParamsBuilder = SkuDetailsParams.newBuilder();
        List<String> skuList = new ArrayList<>();
        skuList.add(mSkuId);
        skuDetailsParamsBuilder.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
        billingClient.querySkuDetailsAsync(skuDetailsParamsBuilder.build(), new SkuDetailsResponseListener() {
            @Override
            public void onSkuDetailsResponse(@NonNull BillingResult billingResult, @Nullable List<SkuDetails> list) {
                if (billingResult.getResponseCode() == 0) {
                    for (SkuDetails skuDetails : list) {
                        mSkuDetailsMap.put(skuDetails.getSku(), skuDetails);
                    }
                }
            }
        });
    }

    private List<Purchase> queryPurchases() {
        Purchase.PurchasesResult purchasesResult = billingClient.queryPurchases(BillingClient.SkuType.INAPP);
        return purchasesResult.getPurchasesList();
    }

    private void payComplete() {
        PurchaseFragment.getInstance().payComplete();
        removeAds();
//        Toast.makeText(getContext(), "Pay complete", Toast.LENGTH_SHORT).show();
    }

    public void launchBilling(String skuId) {
        BillingFlowParams billingFlowParams = BillingFlowParams.newBuilder()
                .setSkuDetails(mSkuDetailsMap.get(skuId))
                .build();
        billingClient.launchBillingFlow(this, billingFlowParams);
    }

    @Override
    public void removeAdsClick() {
        launchBilling(mSkuId);
    }
}
