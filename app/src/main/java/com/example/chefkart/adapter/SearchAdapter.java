package com.example.chefkart.adapter;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.chefkart.R;
import com.example.chefkart.chef_detail_Activity;
import com.example.chefkart.model.ChefModel;
import com.example.chefkart.util.ConstantData;

import java.util.List;


import android.widget.ArrayAdapter;
import android.widget.Filter;

import java.util.ArrayList;

public class SearchAdapter extends ArrayAdapter<ChefModel> {

    private List<ChefModel> originalData; // Original data list
    private List<ChefModel> filteredData; // Filtered data list
    private ItemFilter filter;

    public SearchAdapter(Context context, List<ChefModel> data) {
        super(context, 0, data);
        this.originalData = data;
        this.filteredData = new ArrayList<>(data);
        filter = new ItemFilter();
    }

    @Override
    public int getCount() {
        return filteredData.size();
    }

    @Override
    public ChefModel getItem(int position) {
        return filteredData.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Inflate a custom layout for each item in the AutoCompleteTextView
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.raw_search, parent, false);
        }

        ChefModel product = getItem(position);

        // Set product name
        TextView productName = convertView.findViewById(R.id.txtProName);
        productName.setText(product.getChef_name());

        // Set product image using Glide (optional)
        ImageView productImage = convertView.findViewById(R.id.img);
        Glide.with(getContext())
                .load(ConstantData.SERVER_ADDRESS_IMG+product.getChef_pic()) // Use the product image URL
                .into(productImage);

//

        CardView searchcard= convertView.findViewById(R.id.searchcard);

        searchcard.setOnClickListener(v -> {
            Intent intent=new Intent(getContext(), chef_detail_Activity.class);
            intent.putExtra("chef" + "",product);
            getContext().startActivity(intent);
        });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            FilterResults results = new FilterResults();

            // If there is no query, return the original data
            if (constraint == null || constraint.length() == 0) {
                results.values = originalData;
                results.count = originalData.size();
            } else {
                // Filter based on the product name
                List<ChefModel> filteredList = new ArrayList<>();
                for (ChefModel product : originalData) {
                    if (product.getChef_pic().toLowerCase().contains(constraint.toString().toLowerCase())) {
                        filteredList.add(product);
                    }
                }
                results.values = filteredList;
                results.count = filteredList.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                filteredData = (List<ChefModel>) results.values;
                notifyDataSetChanged(); // Notify adapter about data changes
            }
        }
    }
}