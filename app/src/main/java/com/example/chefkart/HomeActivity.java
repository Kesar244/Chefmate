package com.example.chefkart;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.chefkart.api.Data_Api;
import com.example.chefkart.model.BannerModel;
import com.example.chefkart.model.CategoryModel;
import com.example.chefkart.model.ChefModel;
import com.example.chefkart.model.CouponModel;
import com.example.chefkart.model.DataOutputModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

import me.ibrahimsn.lib.OnItemSelectedListener;
import me.ibrahimsn.lib.SmoothBottomBar;

public class HomeActivity extends AppCompatActivity {

    SmoothBottomBar bnvHome;
    public ArrayList<BannerModel> banner_data;
    public ArrayList<CategoryModel> category_data;
    public ArrayList<CouponModel> coupon_data;
    public ArrayList<ChefModel> chef_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });

        //Start code;
        bnvHome=findViewById(R.id.bnvHome);
        new Data_Api().getData(this);

        //openFragment(new Home_page1(banner_data, category_data, chef_data));
        bnvHome.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public boolean onItemSelect(int i) {
                if(i==0){
                    openFragment(new Home_page1(banner_data, category_data, chef_data));
                } else if (i==1) {
                    openFragment(new coupon_page(coupon_data));
                } else if (i==2) {
                    Intent intent=new Intent(HomeActivity.this,BookingHistoryActivity.class);
                    startActivity(intent);
                }else {
                    openFragment(new Profile_page());
                }
                return false;
            }
        });
    }
    public  void openFragment(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frame,fragment,null);
        ft.commit();
    }

    public  void replaceFragment(Fragment fragment)
    {
        FragmentManager fm=getSupportFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.frame,fragment,null);
        ft.commit();
    }
    public void getData(DataOutputModel data){
        banner_data=data.getBanner_data();
        category_data=data.getCategory_data();
        coupon_data=data.getCoupon_data();
        chef_data=data.getChef_data();
        replaceFragment(new Home_page1(banner_data, category_data, chef_data));
    }
}