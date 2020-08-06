package ru.dorofeev.helpforrust.fragments.info;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.models.ItemOfInfoListItem;

public class InfoItemAdapter extends RecyclerView.Adapter<InfoItemAdapter.InfoItemViewHolder> {
    private List<ItemOfInfoListItem> itemOfInfoListItem;
    private Context context;

    public InfoItemAdapter(List<ItemOfInfoListItem> itemOfInfoListItem, Context context) {
        this.itemOfInfoListItem = itemOfInfoListItem;
        this.context = context;
    }

    @NonNull
    @Override
    public InfoItemAdapter.InfoItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_of_info_list_item, parent, false);
        return new InfoItemAdapter.InfoItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoItemAdapter.InfoItemViewHolder holder, int position) {
        holder.bindData(itemOfInfoListItem.get(position));
    }

    @Override
    public int getItemCount() {
        return itemOfInfoListItem != null ? itemOfInfoListItem.size() : 0;
    }

    public class InfoItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.infoItemListImage)
        ImageView infoItemListImage;

        public InfoItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(ItemOfInfoListItem itemOfInfoListItem) {
            if (itemOfInfoListItem != null) {
                Picasso.get().load(itemOfInfoListItem.getImageUrl()).into(infoItemListImage);
            }
        }
    }
}
