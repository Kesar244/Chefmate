package com.example.chefkart.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chefkart.ChefActivity;
import com.example.chefkart.R;
import com.example.chefkart.model.CategoryModel;
import com.example.chefkart.model.ChefModel;
import com.example.chefkart.util.ConstantData;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CViewHolder> {

    ArrayList<CategoryModel> categoryModels;
    ArrayList<ChefModel> chefModels;

    public CategoryAdapter(ArrayList<CategoryModel> categoryModels, ArrayList<ChefModel> chefModels) {
        this.categoryModels = categoryModels;
        this.chefModels=chefModels;
    }

    @NonNull
    @Override
    public CViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_category, parent, false);
        return new CViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CViewHolder holder, int position) {
        Context context=holder.imgCat.getContext();
        CategoryModel model=categoryModels.get(position);
        holder.tvCatName.setText(categoryModels.get(position).getCat_name());
        Glide.with(holder.imgCat.getContext()).load(ConstantData.SERVER_ADDRESS_IMG+categoryModels
                .get(position).getCat_pic()).into(holder.imgCat);
        holder.imgCat.setOnClickListener(v -> {
            ArrayList<ChefModel> c=new ArrayList<>();
            for (int i=0;i<chefModels.size();i++){
                if(model.getCat_name().equals(chefModels.get(i).getcat_name())){
                    c.add(chefModels.get(i));
                }
            }
            Intent intent=new Intent(context, ChefActivity.class);
            intent.putExtra("chef",c);
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return categoryModels.size();
    }

    public class CViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imgCat;
        TextView tvCatName;
        public CViewHolder(@NonNull View itemView) {
            super(itemView);

            imgCat=itemView.findViewById(R.id.imgCat);
            tvCatName=itemView.findViewById(R.id.tvCatName);
        }
    }
}
