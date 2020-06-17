package ru.dorofeev.helpforrust.fragments.furnace;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.models.Furnace;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FurnaceAdapter extends RecyclerView.Adapter<FurnaceAdapter.FurnaceViewHolder> {

    private List<Furnace> furnaceList;
    private Context context;

    public FurnaceAdapter(List<Furnace> furnaces, Context context) {
        this.furnaceList = furnaces;
        this.context = context;
    }

    @NonNull
    @Override
    public FurnaceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_furnace_list, parent, false);
        return new FurnaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnaceViewHolder holder, int position) {
        holder.bindData(furnaceList.get(position));
    }

    @Override
    public int getItemCount() {
        return furnaceList != null ? furnaceList.size() : 0;
    }

    public class FurnaceViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.furnaceImage)
        ImageView furnaceImage;

        public FurnaceViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bindData(Furnace furnace) {
            furnaceImage.setMaxWidth(furnaceImage.getHeight());
            Picasso.get().load(furnace.getImageUrl()).into(furnaceImage);
        }
    }
}
