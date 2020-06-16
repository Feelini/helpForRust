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
import ru.dorofeev.helpforrust.models.ItemWithValue;

public class ItemsAdapter extends BaseAdapter {

    private List<ItemWithValue> itemWithValues;
    private LayoutInflater layoutInflater;
    private int multiplier;

    public ItemsAdapter(List<ItemWithValue> itemWithValues, Context context, int multiplier) {
        this.itemWithValues = itemWithValues;
        this.multiplier = multiplier;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return itemWithValues != null ? itemWithValues.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return itemWithValues.get(position);
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

        ItemWithValue itemWithValue = itemWithValues.get(position);
        Picasso.get().load(itemWithValue.getItem().getImageUrl()).into(viewHolder.itemImage);
        viewHolder.itemValue.setText(String.valueOf(itemWithValue.getValue() * multiplier));

        return convertView;
    }

    private static class ViewHolder {
        ImageView itemImage;
        TextView itemValue;
    }
}
