package com.example.chefkart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.chefkart.R;
import com.example.chefkart.model.BookingModel;

import java.util.List;

public class BookingAdapter extends BaseAdapter {

    private Context context;
    private List<BookingModel> bookingList;

    public BookingAdapter(Context context, List<BookingModel> bookingList) {
        this.context = context;
        this.bookingList = bookingList;
    }

    @Override
    public int getCount() {
        return bookingList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.row_booking, parent, false);
        }

        ImageView cimgImageView = convertView.findViewById(R.id.cimg);
        TextView cnameTextView = convertView.findViewById(R.id.cname);
        TextView cpriceTextView = convertView.findViewById(R.id.cprice);
        ImageView cdeleteImageView = convertView.findViewById(R.id.cdelete);

        BookingModel booking = bookingList.get(position);

        cnameTextView.setText(booking.getName());
        cpriceTextView.setText(String.valueOf(booking.getAmount()));
        Glide.with(context).load(booking.getPic()).into(cimgImageView); // Use Glide or any other image loading library

        cdeleteImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle delete click event
                bookingList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
