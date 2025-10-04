package com.example.chefkart;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.chefkart.api.login_register_api;

public class LoginActivity extends AppCompatActivity {

    EditText etEmail;

    EditText etPwd;

    Button btnLogin;

    TextView tvReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etEmail=findViewById(R.id.etEmail);
        etPwd=findViewById(R.id.etPwd);
        btnLogin=findViewById(R.id.btnLogin);
        tvReg=findViewById(R.id.tvReg);

        btnLogin.setOnClickListener(v -> {
            String email=etEmail.getText().toString();
            String pass=etPwd.getText().toString();



            if(TextUtils.isEmpty(email))
            {
                etEmail.setError("REQUIRED *");
                Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            } else if (TextUtils.isEmpty(pass))
            {
                etPwd.setError("REQUIRED *");
                Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            }
            else
            {
                //Intent i1 = new Intent(this, HomeActivity.class);
                //startActivity(i1);
                new login_register_api().login(this,email.trim(),pass.trim());
            }
        });

        tvReg.setOnClickListener(v -> {
            Intent i1=new Intent(this, RegistrationActivity.class);
            startActivity(i1);
        });
    }
}