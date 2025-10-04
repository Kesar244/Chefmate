 package com.example.chefkart;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.chefkart.adapter.CategoryAdapter;
import com.example.chefkart.adapter.ChefAdapter;
import com.example.chefkart.adapter.SearchAdapter;
import com.example.chefkart.model.BannerModel;
import com.example.chefkart.model.CategoryModel;
import com.example.chefkart.model.ChefModel;
import com.example.chefkart.util.ConstantData;

import java.util.ArrayList;

public class Home_page1 extends Fragment {

    View view;
    ImageSlider img_slider;
    RecyclerView rcylCat;
    RecyclerView rcylChef;
    TextView tvUser,seeOne,seeTwo;
    AutoCompleteTextView edSearch;
    public static ArrayList<BannerModel> banner_data;
    public static ArrayList<CategoryModel> category_data;
    public static ArrayList<ChefModel> chef_data;

    public Home_page1(ArrayList<BannerModel> banner_data, ArrayList<CategoryModel> category_data, ArrayList<ChefModel> chef_data) {
        this.banner_data=banner_data;
        this.category_data=category_data;
        this.chef_data=chef_data;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home_page1, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvUser=view.findViewById(R.id.tvUser);
        SharedPreferences sp= getActivity().getSharedPreferences("SP_USER",MODE_PRIVATE);
        String username=sp.getString("KEY_USER","");
        tvUser.setText(username);


        edSearch=view.findViewById(R.id.edSearch);
        img_slider=view.findViewById(R.id.img_slider);
        rcylCat=view.findViewById(R.id.rcylCat);
        rcylChef=view.findViewById(R.id.rcylChef);
        rcylCat.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
        //rcylChef.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        GridLayoutManager gridLayoutManager= new GridLayoutManager(getContext(),2);
        rcylChef.setLayoutManager(gridLayoutManager);
        setBanner_data();

        SearchAdapter searchAdapter=new SearchAdapter(getContext(),chef_data);
        edSearch.setThreshold(1);
        edSearch.setAdapter(searchAdapter);

    }
    public  void setBanner_data()
    {
        ArrayList<SlideModel> imgList= new ArrayList<>();
        for(int i=0;i< banner_data.size();i++){
            imgList.add(
              new SlideModel(
                      ConstantData.SERVER_ADDRESS_IMG+banner_data.get(i).getBanner_pic(),
                      null,
                      ScaleTypes.FIT
              )
            );
        }
        img_slider.setImageList(imgList);

        CategoryAdapter adapter=new CategoryAdapter(category_data,chef_data);
        rcylCat.setAdapter(adapter);
        ChefAdapter ad=new ChefAdapter(chef_data);
        rcylChef.setAdapter(ad);
    }
}