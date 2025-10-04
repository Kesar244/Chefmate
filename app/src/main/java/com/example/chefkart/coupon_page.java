package com.example.chefkart;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.chefkart.adapter.CategoryAdapter;
import com.example.chefkart.adapter.CouponAdapter;
import com.example.chefkart.model.ChefModel;
import com.example.chefkart.model.CouponModel;

import java.util.ArrayList;


public class coupon_page extends Fragment {

    View view;
    RecyclerView rcylCoupon;
    public static ArrayList<CouponModel> coupon_data;

    public coupon_page(ArrayList<CouponModel>coupon_data){
        this.coupon_data=coupon_data;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_coupon_page, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rcylCoupon=view.findViewById(R.id.rcylCoupon);
        rcylCoupon.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        setCoupon_data();
    }

    public void setCoupon_data(){
        CouponAdapter adapter=new CouponAdapter(coupon_data,getContext());
        rcylCoupon.setAdapter(adapter);
    }
}