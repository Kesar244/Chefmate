package com.example.chefkart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.example.chefkart.util.ConstantData;

public class AnimeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_anime);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp=getSharedPreferences(ConstantData.SP_NAME, Context.MODE_PRIVATE);
                boolean isLogin=sp.getBoolean(ConstantData.KEY_SP_ISLOGIN,false);
                if(isLogin)
                {
                   Intent intent=new Intent(AnimeActivity.this,HomeActivity.class);
                   startActivity(intent);
                   finish();
                }
                Intent intent=new Intent(AnimeActivity.this,FirstActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}