package ru.dorofeev.helpforrust.fragments.info;

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
import ru.dorofeev.helpforrust.models.InfoList;
import ru.dorofeev.helpforrust.models.InfoListItem;
import ru.dorofeev.helpforrust.models.ItemOfInfoListItem;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder>  {
    private InfoList infoListList;
    private Context context;
    private OnInfoListItemClickListener onInfoListItemClickListener;

    public interface OnInfoListItemClickListener{
        void onInfoListItemClick(List<ItemOfInfoListItem> itemOfInfoListItemAndBuffs);
    }

    public InfoAdapter(InfoList infoListList, Context context) {
        this.infoListList = infoListList;
        this.context = context;
        if (context instanceof OnInfoListItemClickListener){
            onInfoListItemClickListener = (OnInfoListItemClickListener) context;
        }
    }

    @NonNull
    @Override
    public InfoAdapter.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_info_list, parent, false);
        return new InfoAdapter.InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.InfoViewHolder holder, int position) {
        holder.bindData(infoListList.getInfoListItems().get(position));
    }

    @Override
    public int getItemCount() {
        return infoListList.getInfoListItems() != null ? infoListList.getInfoListItems().size() : 0;
    }

    public class InfoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.infoItemImage)
        ImageView infoItemImage;
        @BindView(R.id.itemTitle)
        TextView itemTitle;

        public InfoViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(InfoListItem infoListItem) {
            if (infoListItem != null) {
                Picasso.get().load(infoListItem.getIconUrl()).into(infoItemImage);
                switch (infoListItem.getName()) {
                    case "teaAndBuffs":
                        itemTitle.setText(R.string.tea_and_buffs);
                        break;
                }
                itemView.setOnClickListener(v -> {
                    if (onInfoListItemClickListener != null){
                        switch (infoListItem.getName()){
                            case "teaAndBuffs":
                                onInfoListItemClickListener.onInfoListItemClick(infoListList.getItemOfInfoListItemAndBuffs());
                                break;
                        }
                    }
                });
            }
        }
    }
}
