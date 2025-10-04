package com.example.chefkart;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chefkart.adapter.ChefAdapter;
import com.example.chefkart.model.ChefModel;

import java.util.ArrayList;

public class ChefActivity extends AppCompatActivity {

    RecyclerView rcylChef;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chef);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgBack=findViewById(R.id.imgBack);
        imgBack.setOnClickListener(v->{
            finish();
        });
        rcylChef=findViewById(R.id.rcylChef);

        ArrayList<ChefModel> chefModels= (ArrayList<ChefModel>) getIntent().getSerializableExtra("chef");
        ChefAdapter adapter=new ChefAdapter(chefModels);
        rcylChef.setLayoutManager(new GridLayoutManager(this,2));
        rcylChef.setAdapter(adapter);
    }
}