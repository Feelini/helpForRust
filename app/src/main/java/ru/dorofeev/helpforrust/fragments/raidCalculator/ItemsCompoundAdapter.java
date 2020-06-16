package ru.dorofeev.helpforrust.fragments.raidCalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ru.dorofeev.helpforrust.R;
import ru.dorofeev.helpforrust.models.ItemCompoundWithValue;

public class ItemsCompoundAdapter extends BaseAdapter {
    private List<ItemCompoundWithValue> itemCompoundWithValues;
    private int multiplier;
    private LayoutInflater layoutInflater;

    public ItemsCompoundAdapter(List<ItemCompoundWithValue> itemCompoundWithValues, Context context, int multiplier) {
        this.itemCompoundWithValues = itemCompoundWithValues;
        this.multiplier = multiplier;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemCompoundWithValues != null ? itemCompoundWithValues.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return itemCompoundWithValues.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_grid_list, parent, false);
            viewHolder.itemImage = convertView.findViewById(R.id.itemImage);
            viewHolder.itemValue = convertView.findViewById(R.id.itemValue);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        ItemCompoundWithValue itemCompoundWithValue = itemCompoundWithValues.get(position);
        Picasso.get().load(itemCompoundWithValue.getItemCompound().getImageUrl()).into(viewHolder.itemImage);
        viewHolder.itemValue.setText(String.valueOf(itemCompoundWithValue.getValue() * multiplier));

        return convertView;
    }

    private static class ViewHolder {
        ImageView itemImage;
        TextView itemValue;
    }
}
