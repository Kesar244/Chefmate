package com.example.chefkart;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FirstActivity extends AppCompatActivity {

    TextView tvStart,tvSkip;
    VideoView videoView;
    Button btnLogin,btnSignUp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.acitivity_first);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin=findViewById(R.id.btnLogin);
        btnSignUp=findViewById(R.id.btnSignUp);
        //tvStart=findViewById(R.id.tvStart);
        tvSkip=findViewById(R.id.tvSkip);
        tvSkip.setOnClickListener(v->{
            Intent i1=new Intent(FirstActivity.this, HomeActivity.class);
            startActivity(i1);
        });
        btnLogin.setOnClickListener(v->{
            Intent i1=new Intent(FirstActivity.this, LoginActivity.class);
            startActivity(i1);
        });
        btnSignUp.setOnClickListener(v->{
            Intent i1=new Intent(FirstActivity.this, RegistrationActivity.class);
            startActivity(i1);
        });


        //videoView=findViewById(R.id.videoView);

        //String videoPath = "android.resource://" + getPackageName() + "/" + R.raw.vid;
        //Uri uri = Uri.parse(videoPath);
        //videoView.setVideoURI(uri);
        //videoView.start();

    }
}