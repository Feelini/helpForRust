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
import ru.dorofeev.helpforrust.models.InfoListItem;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoViewHolder>  {
    private List<InfoListItem> infoListItems;
    private Context context;

    public InfoAdapter(List<InfoListItem> infoListItems, Context context) {
        this.infoListItems = infoListItems;
        this.context = context;
    }

    @NonNull
    @Override
    public InfoAdapter.InfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_info_list, parent, false);
        return new InfoAdapter.InfoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoAdapter.InfoViewHolder holder, int position) {
        holder.bindData(infoListItems.get(position));
    }

    @Override
    public int getItemCount() {
        return infoListItems != null ? infoListItems.size() : 0;
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
            Picasso.get().load(infoListItem.getIconUrl()).into(infoItemImage);
            itemTitle.setText(infoListItem.getName());
        }
    }
}
