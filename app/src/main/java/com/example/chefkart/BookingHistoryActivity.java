package com.example.chefkart;

import static com.example.chefkart.R.id.bookinghistoryrcyl;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chefkart.adapter.MyBookingAdapter;
import com.example.chefkart.api.BookingApi;
import com.example.chefkart.model.BookingModel;
import com.example.chefkart.model.BookingOutputModel;
import com.example.chefkart.util.ConstantData;

import java.util.ArrayList;

public class BookingHistoryActivity extends AppCompatActivity {

    RecyclerView bookinghistoryrcyl;
    ArrayList<BookingModel> bookings;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        bookinghistoryrcyl=findViewById(R.id.bookinghistoryrcyl);


        SharedPreferences sp =getSharedPreferences(ConstantData.SP_NAME,MODE_PRIVATE);
        String uid =sp.getString(ConstantData.KEY_USER_ID,"0");
        new BookingApi().order_history(this,uid,"0");
    }

    public void set(BookingOutputModel p) {

        bookings=p.getOrder();

        bookinghistoryrcyl.setLayoutManager(new LinearLayoutManager(this));
        bookinghistoryrcyl.setAdapter(new MyBookingAdapter(bookings,this));
    }
}