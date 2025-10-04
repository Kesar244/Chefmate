package com.example.chefkart.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chefkart.R;
import com.example.chefkart.chef_detail_Activity;
import com.example.chefkart.model.ChefModel;
import com.example.chefkart.util.ConstantData;

import java.util.ArrayList;

public class ChefAdapter extends RecyclerView.Adapter<ChefAdapter.CHViewHolder> {

    ArrayList<ChefModel> chefmodels;

    public ChefAdapter(ArrayList<ChefModel> chefmodels) {
        this.chefmodels = chefmodels;
    }

    @NonNull
    @Override
    public CHViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_chef, parent, false);
        return new CHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CHViewHolder holder, int position) {
        holder.tvCname.setText(chefmodels.get(position).getChef_name());
        holder.tvPrice.setText(chefmodels.get(position).getAmount());
        holder.tvRatings.setText(chefmodels.get(position).getRatings());
        Glide.with(holder.Cimg.getContext()).load(ConstantData.SERVER_ADDRESS_IMG+chefmodels
                .get(position).getChef_pic()).into(holder.Cimg);

        holder.card.setOnClickListener(v -> {
            Intent intent = new Intent(holder.card.getContext(),chef_detail_Activity.class);
            intent.putExtra("chef",chefmodels.get(position));
            holder.card.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return chefmodels.size();
    }

    public class CHViewHolder extends RecyclerView.ViewHolder{

        ImageView Cimg;
        TextView tvCname,tvPrice,tvRatings;
        CardView card;
        public CHViewHolder(@NonNull View itemView) {
            super(itemView);

            Cimg=itemView.findViewById(R.id.Cimg);
            tvCname=itemView.findViewById(R.id.tvCname);
            tvPrice=itemView.findViewById(R.id.tvPrice);
            tvRatings=itemView.findViewById(R.id.tvRatings);
            card=itemView.findViewById(R.id.card);

        }
    }
}
