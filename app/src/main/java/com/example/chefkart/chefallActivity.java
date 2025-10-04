package com.example.chefkart;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chefkart.adapter.ChefAdapter;
import com.example.chefkart.model.ChefModel;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class chefallActivity extends AppCompatActivity {

    RecyclerView rcylChefAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chefall);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rcylChefAll=findViewById(R.id.rcylChefAll);
        ArrayList<ChefModel> chefModels=(ArrayList<ChefModel>)getIntent().getSerializableExtra("chef");
        ChefAdapter adapter=new ChefAdapter(chefModels);
        rcylChefAll.setLayoutManager(new GridLayoutManager(this,2));
        rcylChefAll.setAdapter(adapter);

        Intent intent = getIntent();
        ChefModel model = (ChefModel) intent.getSerializableExtra("chef");



    }
}