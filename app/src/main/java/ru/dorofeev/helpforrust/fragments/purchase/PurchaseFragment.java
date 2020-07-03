package ru.dorofeev.helpforrust.fragments.purchase;

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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ru.dorofeev.helpforrust.R;

public class PurchaseFragment extends Fragment {

    private static PurchaseFragment instance;
    private boolean payComplete = false;
    @BindView(R.id.adsOff)
    Button adsOff;
    @BindView(R.id.purchaseText)
    TextView purchaseText;
    private Unbinder unbinder;
    private static RemoveAdsClickListener removeAdsClickListener;

    public interface RemoveAdsClickListener{
        void removeAdsClick();
    }

    public static PurchaseFragment getInstance() {
        if (instance == null) {
            instance = new PurchaseFragment();
        }
        return instance;
    }

    public static PurchaseFragment getInstance(Context context) {
        if (instance == null) {
            instance = new PurchaseFragment();
        }
        if (context instanceof RemoveAdsClickListener){
            removeAdsClickListener = (RemoveAdsClickListener) context;
        }
        return instance;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_purchase, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);

        if (!payComplete) {
            adsOff.setOnClickListener(v -> {
                if (removeAdsClickListener != null) {
                    removeAdsClickListener.removeAdsClick();
                }
            });
        } else {
            purchaseText.setText(getString(R.string.thanks));
            adsOff.setClickable(false);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    public void payComplete(){
        payComplete = true;
    }
}
