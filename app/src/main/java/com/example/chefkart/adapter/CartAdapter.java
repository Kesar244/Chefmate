package com.example.chefkart.adapter;



import android.content.Context;
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
import com.example.chefkart.model.BookingModel;
import com.example.chefkart.util.ConstantData;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {

    private Context context;
    private List<BookingModel> orderList;

    public onClickListener onClickListener;

    public CartAdapter(Context context, List<BookingModel> orderList,onClickListener onClickListener) {
        this.context = context;
        this.orderList = orderList;
        this.onClickListener=onClickListener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_booking, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {

        // Bind data to views
        BookingModel BookingModel=orderList.get(position);
        holder.tvfname.setText(BookingModel.getName());
        holder.price.setText(String.valueOf(BookingModel.getTot_amount()));
        holder.tvcatname.setText(String.valueOf(BookingModel.getAmount()));
        Glide.with(context).load(ConstantData.SERVER_ADDRESS_IMG+BookingModel.getPic()).into(holder.cartImage);


        // Handle delete button
        holder.btnDeleteCart.setOnClickListener(v -> {
            orderList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, orderList.size());
            onClickListener.onDelete(BookingModel);
        });
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    // Helper method to update total price


    // ViewHolder class
    public static class CartViewHolder extends RecyclerView.ViewHolder {
        TextView tvfname, tvcatname, price, tot;
        ImageView btnDeleteCart,cartImage;

        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvfname = itemView.findViewById(R.id.cartShoesName);
            cartImage = itemView.findViewById(R.id.cartImage);
            tvcatname = itemView.findViewById(R.id.cartShoesColor);
            price = itemView.findViewById(R.id.cartShoesPrice);
            tot = itemView.findViewById(R.id.cartShoesPrice);
            btnDeleteCart = itemView.findViewById(R.id.btnDeleteCart);
        }
    }

    public  interface onClickListener{

        public void onDelete(BookingModel BookingModel);
    }
}