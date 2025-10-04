package com.example.chefkart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegistrationActivity extends AppCompatActivity {

    EditText etUname;

    EditText etEmail;

    EditText etCno;

    EditText etPwd;

    TextView tvLogin;

    Button btnReg;

    String username,email,pass,mobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        etEmail=findViewById(R.id.etEmail);
        etUname=findViewById(R.id.etUname);
        etCno=findViewById(R.id.etCno);
        etPwd=findViewById(R.id.etPwd);
        btnReg=findViewById(R.id.btnReg);
        tvLogin=findViewById(R.id.tvLogin);

        btnReg.setOnClickListener(v -> {
            username=etUname.getText().toString();
            email=etEmail.getText().toString();
            mobileno=etCno.getText().toString();
            pass=etPwd.getText().toString();
            if(TextUtils.isEmpty(username))
            {
                etEmail.setError("REQUIRED *");
                Toast.makeText(this,"Please enter username",Toast.LENGTH_SHORT).show();
            }
            else if(TextUtils.isEmpty(email))
            {
                etEmail.setError("REQUIRED *");
                Toast.makeText(this,"Please enter email",Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(mobileno))
            {
                etCno.setError("REQUIRED *");
                Toast.makeText(this,"Please enter number",Toast.LENGTH_SHORT).show();
            } else if (mobileno.length()!=10)
            {
                Toast.makeText(this,"Please enter valid mobile number",Toast.LENGTH_SHORT).show();
            }
            else if (TextUtils.isEmpty(pass))
            {
                etPwd.setError("REQUIRED *");
                Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            }
            else
            {
                generateOTP(mobileno);
                SharedPreferences sp=getSharedPreferences("SP_USER",MODE_PRIVATE);
                SharedPreferences.Editor ed=sp.edit();
                ed.putString("KEY_USER",username);
                ed.putString("KEY_EMAIL",email);
                ed.commit();
                new login_register_api().register(this,username,email,pass,mobileno);
            }
        });

        tvLogin.setOnClickListener(v -> {
            Intent i1=new Intent(this, LoginActivity.class);
            startActivity(i1);
        });

    }
    public void generateOTP(String mobileno) {
        ExecutorService executor = Executors.newSingleThreadExecutor(); // Run in background thread
        executor.execute(() -> {
            OkHttpClient client = new OkHttpClient.Builder().build();
            MediaType mediaType = MediaType.parse("text/plain");
            RequestBody body = RequestBody.create(mediaType, "");

            Request request = new Request.Builder()
                    .url("https://cpaas.messagecentral.com/verification/v3/send?countryCode=91&customerId="+ ConstantData.CUSTOMER_ID+"&flowType=SMS&mobileNumber=" + mobileno)
                    .method("POST", body)
                    .addHeader("authToken", ConstantData.AUTH_TOKEN)
                    .build();

            try {
                Response response = client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String responseBody = response.body().string();

                    JSONObject jsonResponse = new JSONObject(responseBody);

                    // Extract verificationId from the response
                    JSONObject data = jsonResponse.getJSONObject("data");
                    String verificationId = data.getString("verificationId");

                    // Send data to OTPActivity
                    runOnUiThread(() -> {
                        Intent intent = new Intent(RegistrationActivity.this, OTPActivity.class);
                        intent.putExtra("username", username);
                        intent.putExtra("email", email);
                        intent.putExtra("password", pass);
                        intent.putExtra("mobileno", mobileno);
                        intent.putExtra("verificationId", verificationId); // Pass verificationId
                        startActivity(intent);
                    });
                } else {
                    runOnUiThread(() -> Toast.makeText(RegistrationActivity.this, "OTP request failed", Toast.LENGTH_SHORT).show());
                }
            } catch (IOException | JSONException e) {
                Log.e("ERROR", e.getLocalizedMessage());
                runOnUiThread(() -> Toast.makeText(RegistrationActivity.this, "Network error! Try again.", Toast.LENGTH_SHORT).show());
            }
        });
    }
}