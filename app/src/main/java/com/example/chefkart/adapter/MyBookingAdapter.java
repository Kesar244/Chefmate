package com.example.chefkart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chefkart.model.BookingModel;
import com.example.chefkart.R;

import java.util.ArrayList;
import java.util.List;


import android.widget.TextView;

import com.example.chefkart.util.ConstantData;
import com.transferwise.sequencelayout.SequenceLayout;
import com.transferwise.sequencelayout.SequenceStep;


public class MyBookingAdapter extends RecyclerView.Adapter<MyBookingAdapter.CartViewHolder> {
    List<BookingModel> bookingModels;
    ArrayList<String> steps;
    Context context;

    public MyBookingAdapter(List<BookingModel> bookingModels , Context context) {
        this.bookingModels = bookingModels ;
        this.context = context;
        steps=new ArrayList<>();
        steps.add("Order Placed");
        steps.add("Dispatched");
        steps.add("Shipping");
        steps.add("Delivered");

    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.rawmybooking, parent, false);
        CartViewHolder cartViewHolder = new CartViewHolder(view);
        return cartViewHolder;
    }

    @NonNull


    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        BookingModel b = bookingModels.get(position);
        Glide.with(context).load(ConstantData.SERVER_ADDRESS_IMG+b.getPic()).into(holder.orderShoesImage);
        holder.orderShoesName.setText(b.getName());
        holder.orderShoesPrice.setText(b.getAmount()+"");
        //holder.orderShoesSize.setText("Quantity:"+b.getQty()+"");


        holder.orderCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tracker.setVisibility(View.VISIBLE);
            }
        });

        holder.tracker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.tracker.setVisibility(View.GONE);

            }
        });
        holder.step1.setSubtitle("Your Order has Received,Order Will be Dispatch within few Hours");
        holder.step2.setSubtitle("Order dispatched from SOLEGLIDE's Warehouse");
        holder.step3.setSubtitle("Order is being Shipped");
        holder.step4.setSubtitle("Order is Out for delivery");
        holder.step5.setSubtitle("Delieverd order");


        if(b.getStatus()==1){
            holder.step1.setActive(true);
        }else if(b.getStatus()==2){
            holder.step2.setActive(true);

        }else if(b.getStatus()==3){
            holder.step3.setActive(true);

        }else if(b.getStatus()==4){
            holder.step4.setActive(true);

        }else{
            holder.step5.setActive(true);

        }
        holder.orderShoesColor.setText(b.getDate());
        holder.orderShoesSize.setText(b.getTime());

    }

    @Override
    public int getItemCount() {
        return bookingModels.size();
    }

    public class CartViewHolder extends RecyclerView.ViewHolder {
        ImageView orderShoesImage;
        SequenceLayout tracker;
        CardView orderCard;
        SequenceStep step1,step2,step3,step4,step5;
        TextView orderShoesName, orderShoesPrice, orderShoesColor, orderShoesSize;


        public CartViewHolder(@NonNull View itemView) {
            super(itemView);
            orderShoesImage = itemView.findViewById(R.id.orderShoesImage);

            orderShoesName = itemView.findViewById(R.id.orderShoesName);
            orderShoesPrice = itemView.findViewById(R.id.orderShoesPrice);
            orderShoesColor = itemView.findViewById(R.id.orderShoesColor);
            orderShoesSize = itemView.findViewById(R.id.orderShoesSize);

            step1=itemView.findViewById(R.id.step1);
            step2=itemView.findViewById(R.id.step2);
            step3=itemView.findViewById(R.id.step3);
            step4=itemView.findViewById(R.id.step4);
            step5=itemView.findViewById(R.id.step5);
            tracker=itemView.findViewById(R.id.tracker);
            orderCard=itemView.findViewById(R.id.orderCard);
        }
    }

    public interface OnClickListener {
        public void onClickPlus(BookingModel bookingModel);

        public void onClickMinus(BookingModel bookingModel);

        public void removeOrder(BookingModel bookingModel);

    }

}