package com.example.chefkart.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chefkart.R;
import com.example.chefkart.model.CouponModel;
import com.example.chefkart.util.ConstantData;

import java.util.ArrayList;

public class CouponAdapter extends RecyclerView.Adapter<CouponAdapter.CPViewHolder> {

    ArrayList<CouponModel> couponmodels;
    Context context;

    public CouponAdapter(ArrayList<CouponModel> couponmodels, Context context) {
        this.couponmodels = couponmodels;
        this.context = context;
    }

    @NonNull
    @Override
    public CPViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.raw_coupon, parent, false);
        return new CPViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CPViewHolder holder, int position) {
        CouponModel coupon = couponmodels.get(position);

        holder.coupon_name.setText(coupon.getC_title());
        holder.coupon_code.setText(coupon.getC_code());
        holder.coupon_disc.setText(coupon.getC_desc());
        holder.coupon_mamt.setText(coupon.getC_maxamt());

        Glide.with(holder.coupon_img.getContext())
                .load(ConstantData.SERVER_ADDRESS_IMG + coupon.getC_pic())
                .into(holder.coupon_img);

        // Copy coupon code when clicking the image
        holder.coupon_img.setOnClickListener(view -> {
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clip = ClipData.newPlainText("Coupon Code", coupon.getC_code());
            clipboard.setPrimaryClip(clip);

            Toast.makeText(context, "Coupon code copied to clipboard!", Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return couponmodels.size();
    }

    public static class CPViewHolder extends RecyclerView.ViewHolder {

        ImageView coupon_img;
        TextView coupon_name;
        TextView coupon_code;
        TextView coupon_disc;
        TextView coupon_mamt;

        public CPViewHolder(@NonNull View itemView) {
            super(itemView);
            coupon_img = itemView.findViewById(R.id.coupon_img);
            coupon_name = itemView.findViewById(R.id.coupon_name);
            coupon_code = itemView.findViewById(R.id.coupon_code);
            coupon_disc = itemView.findViewById(R.id.coupon_disc);
            coupon_mamt = itemView.findViewById(R.id.coupon_mamt);
        }
    }
}
