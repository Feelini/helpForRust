package ru.dorofeev.helpforrust.fragments.raidCalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
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
    private int multiplier = 1;
    private Context context;

    public WeaponsAdapter(List<RaidCalculatorListItem> raidCalculatorListItems, Context context, int multiplier) {
        this.raidCalculatorListItems = raidCalculatorListItems;
        this.context = context;
        this.multiplier = multiplier;
    }

    @NonNull
    @Override
    public WeaponsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_wepons_list, parent, false);
        return new WeaponsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeaponsViewHolder holder, int position) {
        holder.bindData(raidCalculatorListItems.get(position), context, multiplier);
    }

    @Override
    public int getItemCount() {
        return raidCalculatorListItems != null ? raidCalculatorListItems.size() : 0;
    }

    public void setMultiplier(int multiplier){
        this.multiplier = multiplier;
    }

    public class WeaponsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weaponImage)
        ImageView weaponImage;
        @BindView(R.id.weaponValue)
        TextView weaponValue;
        @BindView(R.id.itemTitle)
        TextView itemTitle;
        @BindView(R.id.itemList)
        GridView itemList;
        @BindView(R.id.itemCompoundTitle)
        TextView itemCompoundTitle;
        @BindView(R.id.itemCompoundList)
        GridView itemCompoundList;

        public WeaponsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(RaidCalculatorListItem raidCalculatorListItem, Context context, int multiplier) {
            Picasso.get().load(raidCalculatorListItem.getWeaponsWithValue().getWeapons().getImageUrl()).into(weaponImage);
            weaponValue.setText(String.valueOf(raidCalculatorListItem.getWeaponsWithValue().getValue() * multiplier));
            if (raidCalculatorListItem.getItemWithValues() != null &&
                    raidCalculatorListItem.getItemWithValues().size() > 0){
                itemList.setAdapter(new ItemsAdapter(raidCalculatorListItem.getItemWithValues(), context, multiplier));
                itemList.setVisibility(View.VISIBLE);
                itemTitle.setVisibility(View.VISIBLE);
            } else {
                itemTitle.setVisibility(View.GONE);
                itemList.setVisibility(View.INVISIBLE);
            }
            if (raidCalculatorListItem.getItemCompoundWithValues() != null &&
                    raidCalculatorListItem.getItemCompoundWithValues().size() > 0){
                itemCompoundList.setAdapter(new ItemsCompoundAdapter(raidCalculatorListItem.getItemCompoundWithValues(), context, multiplier));
                itemCompoundList.setVisibility(View.VISIBLE);
                itemCompoundTitle.setVisibility(View.VISIBLE);
            } else {
                itemCompoundList.setVisibility(View.INVISIBLE);
                itemCompoundTitle.setVisibility(View.GONE);
            }
        }
    }
}
