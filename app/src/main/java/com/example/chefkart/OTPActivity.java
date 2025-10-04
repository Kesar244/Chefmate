package com.example.chefkart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
import com.example.chefkart.util.ConstantData;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OTPActivity extends AppCompatActivity {
    EditText etOtp1,etOtp2,etOtp3,etOtp4;
    Button btnVerify;
    TextView tvOTP;
    String username, password, email, mobileno, verificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_otpactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ui_ids();
        etswitcher();
        btnVerify=findViewById(R.id.btnVerify);

        username = getIntent().getStringExtra("username");
        email = getIntent().getStringExtra("email");
        password = getIntent().getStringExtra("password");
        mobileno = getIntent().getStringExtra("mobileno");
        verificationId = getIntent().getStringExtra("verificationId");  // Ensure this is received

        btnVerify.setOnClickListener(view -> {
            if (isOtpValid()) {
                String code = getOtp();
                verifyOTP(code);
            }
            else {
                Toast.makeText(this,"Please Enter Valid OTP",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public  void ui_ids()
    {
        etOtp1=findViewById(R.id.etOtp1);
        etOtp2=findViewById(R.id.etOtp2);
        etOtp3=findViewById(R.id.etOtp3);
        etOtp4=findViewById(R.id.etOtp4);
    }

    public void etswitcher(){
        etOtp1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1){
                        etOtp2.requestFocus();
                    }
                }
            }
        });
        etOtp2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1){
                        etOtp3.requestFocus();
                    } else if (editable.toString().trim().isEmpty()) {
                        etOtp1.requestFocus();
                    }
                }
            }
        });

        etOtp3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){
                    if(editable.length()==1){
                        etOtp4.requestFocus();
                    } else if (editable.toString().trim().isEmpty()) {
                        etOtp2.requestFocus();
                    }
                }
            }
        });

        etOtp4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(editable!=null){

                }
            }
        });
    }
    private boolean isOtpValid() {
        return !(etOtp1.getText().toString().trim().isEmpty() ||
                etOtp2.getText().toString().trim().isEmpty() ||
                etOtp3.getText().toString().trim().isEmpty() ||
                etOtp4.getText().toString().trim().isEmpty());
    }

    private String getOtp() {
        return etOtp1.getText().toString() +
                etOtp2.getText().toString() +
                etOtp3.getText().toString() +
                etOtp4.getText().toString();
    }
    public void verifyOTP(String code) {
        if (verificationId == null || verificationId.isEmpty()) {
            Toast.makeText(this, "Verification ID missing!", Toast.LENGTH_SHORT).show();
            return;
        }

        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            OkHttpClient client = new OkHttpClient.Builder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url("https://cpaas.messagecentral.com/verification/v3/validateOtp?countryCode=91&mobileNumber=" +
                            mobileno + "&verificationId=" + verificationId + "&customerId="+ ConstantData.CUSTOMER_ID +"&code=" + code)
                    .method("GET", null)  // GET requests shouldn't have a body
                    .addHeader("authToken", ConstantData.AUTH_TOKEN )
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    runOnUiThread(() -> {
                        Toast.makeText(OTPActivity.this, "Verification successful", Toast.LENGTH_SHORT).show();
                        //pending register api

                        //Intent intent = new Intent(OTPActivity.this, LoginActivity.class);
                        //startActivity(intent);
                        //finish();
                        new login_register_api().register(this,username,email,password,mobileno);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(OTPActivity.this, "Invalid OTP. Try again!", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException e) {
                Log.e("ERROR", e.getLocalizedMessage());
                runOnUiThread(() -> Toast.makeText(OTPActivity.this, "Network error! Try again.", Toast.LENGTH_SHORT).show());
            }
        });
    }
}