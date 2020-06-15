package ru.dorofeev.helpforrust.fragments.raidCalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.models.RaidCalculatorListItem;
import ru.dorofeev.helpforrust.models.WeaponsWithValue;

public class WeaponsAdapter extends RecyclerView.Adapter<WeaponsAdapter.WeaponsViewHolder> {

    private List<RaidCalculatorListItem> raidCalculatorListItems;
    private Context context;

    public WeaponsAdapter(List<RaidCalculatorListItem> raidCalculatorListItems, Context context) {
        this.raidCalculatorListItems = raidCalculatorListItems;
        this.context = context;
    }

    @NonNull
    @Override
    public WeaponsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wepons_list, parent, false);
        return new WeaponsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeaponsViewHolder holder, int position) {
        holder.bindData(raidCalculatorListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return raidCalculatorListItems != null ? raidCalculatorListItems.size() : 0;
    }

    public class WeaponsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weaponImage)
        ImageView weaponImage;
        @BindView(R.id.weaponValue)
        TextView weaponValue;

        public WeaponsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(RaidCalculatorListItem raidCalculatorListItem) {
            Picasso.get().load(raidCalculatorListItem.getWeaponsWithValue().getWeapons().getImageUrl()).into(weaponImage);
            weaponValue.setText(String.valueOf(raidCalculatorListItem.getWeaponsWithValue().getValue()));
        }
    }
}
