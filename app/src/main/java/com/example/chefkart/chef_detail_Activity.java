package com.example.chefkart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.chefkart.model.ChefModel;
import com.example.chefkart.util.ConstantData;

import java.util.ArrayList;

public class chef_detail_Activity extends AppCompatActivity {

    ImageView cimg,imgBack;
    TextView tvCname,tvCno,tvCmail,tvSubCat,tvDesc,tvAdd,tvCuisin,tvRatings,tvFees;

    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chef_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v->{
            finish();
        });

        cimg=findViewById(R.id.cimg);
        tvCname=findViewById(R.id.tvCname);
        tvCno=findViewById(R.id.tvCno);
        tvCmail=findViewById(R.id.tvCmail);
        tvSubCat=findViewById(R.id.tvSubCat);
        //tvDesc=findViewById(R.id.tvDesc);
        tvAdd=findViewById(R.id.tvAdd);
        tvCuisin=findViewById(R.id.tvCuisin);
        tvRatings=findViewById(R.id.tvRatings);
        tvFees=findViewById(R.id.tvFees);
        btnBooking=findViewById(R.id.btnBooking);

        Intent intent = getIntent();
        ChefModel model = (ChefModel) intent.getSerializableExtra("chef");

        tvCname.setText(model.getChef_name());
        tvCno.setText(model.getMob_no());
        tvCmail.setText(model.getEmail_id());
        tvSubCat.setText(model.getVeg_nonveg_both());
        //tvDesc.setText(model.ge);
        tvAdd.setText(model.getAddress());
        tvCuisin.setText(model.getcat_name());
        tvRatings.setText(model.getRatings());
        tvFees.setText(model.getAmount());

        Glide.with(this)
                .load(ConstantData.SERVER_ADDRESS_IMG + model.getChef_pic())
                .into(cimg);


        SharedPreferences sharedPreferences = getSharedPreferences(ConstantData.SP_NAME, MODE_PRIVATE);
        String uid = sharedPreferences.getString(ConstantData.KEY_USER_ID, "0");
        btnBooking.setOnClickListener(v -> {
            if(uid.equals("0")){
                Intent intent1=new Intent(this, LoginActivity.class);
                startActivity(intent1);
            }
            else {
                Intent intent1=new Intent(this, BookingActivity.class);
                intent1.putExtra("chef",model);
                startActivity(intent1);
            }

        });
    }
}