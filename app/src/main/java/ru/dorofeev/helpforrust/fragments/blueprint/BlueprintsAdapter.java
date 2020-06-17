package ru.dorofeev.helpforrust.fragments.blueprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.models.Blueprint;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.dorofeev.helpforrust.repo.database.CheckedItemEntity;

public class BlueprintsAdapter extends RecyclerView.Adapter<BlueprintsAdapter.BlueprintsViewHolder>{

    private List<Blueprint> blueprints;
    private List<CheckedItemEntity> checkedBlueprintsPosition = new ArrayList<>();
    private int currentPosition = -1;
    private Context context;
    private OnBlueprintClickListener onBlueprintClick;
    private String type;

    public interface OnBlueprintClickListener{
        void onBlueprintClick(Blueprint blueprint, int position, String type);
    }

//    public void addCheckedBlueprint(int position){
//        checkedBlueprintsPosition.add(position);
//    }

//    public void removeCheckedBlueprint(Integer position){
//        checkedBlueprintsPosition.remove(position);
//    }

//    public void removeAllSelect(){
//        checkedBlueprintsPosition = new ArrayList<>();
//    }

    public CheckedItemEntity getCurrentItem(int position){
        List<CheckedItemEntity> checkedItemEntities = new ArrayList<>();
        for (CheckedItemEntity checkedItemEntity: checkedBlueprintsPosition){
            if (checkedItemEntity.getPosition() == position){
                checkedItemEntities.add(checkedItemEntity);
                 break;
            }
        }
        return checkedItemEntities.size() > 0 ? checkedItemEntities.get(0) : null;
    }

    public int getBlueprintsCount(){
        return checkedBlueprintsPosition != null ? checkedBlueprintsPosition.size() : 0;
    }

    public void setSelected(int position){
        currentPosition = position;
    }

    public void setCheckedItems(List<CheckedItemEntity> checkedItems){
        checkedBlueprintsPosition = checkedItems;
    }

    public Boolean isAdded(int position){
        boolean isAdded = false;
        for (CheckedItemEntity checkedItemEntity: checkedBlueprintsPosition){
            if (checkedItemEntity.getPosition() == position) {
                isAdded = true;
                break;
            }
        }
        return isAdded;
    }

    public BlueprintsAdapter(List<Blueprint> blueprints, Context context, String type){
        this.blueprints = blueprints;
        this.context = context;
        this.type = type;
        if (context instanceof OnBlueprintClickListener){
            onBlueprintClick = (OnBlueprintClickListener) context;
        }
    }

    @NonNull
    @Override
    public BlueprintsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_blueprints_list, parent, false);
        return new BlueprintsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlueprintsViewHolder holder, int position) {
        Boolean isSelected = currentPosition == position;
        boolean isAdded = false;
        for (CheckedItemEntity checkedItemEntity: checkedBlueprintsPosition){
            if (checkedItemEntity.getPosition() == position) {
                isAdded = true;
                break;
            }
        }
        holder.bindData(blueprints.get(position), isSelected, isAdded);
    }

    @Override
    public int getItemCount() {
        return blueprints != null ? blueprints.size() : 0;
    }

    public class BlueprintsViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.blueprintImage)
        ImageView blueprintImage;

        public BlueprintsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Blueprint blueprint, Boolean isSelected, Boolean isAdded){
            Picasso.get().load(blueprint.getImageUrl()).into(blueprintImage);
            blueprintImage.setOnClickListener(v -> {
                if (onBlueprintClick != null) {
                    onBlueprintClick.onBlueprintClick(blueprint, getLayoutPosition(), type);
                }
            });
            if (isAdded){
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.toolbarText));
            } else if (isSelected){
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.bgBottomNavBar));
            } else {
                itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.bgPrimary));
            }
        }
    }
}
