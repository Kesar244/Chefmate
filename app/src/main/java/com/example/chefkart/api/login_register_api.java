package com.example.chefkart.api;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chefkart.HomeActivity;
import com.example.chefkart.LoginActivity;
import com.example.chefkart.model.PersonOutputModel;
import com.example.chefkart.util.ConstantData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class login_register_api {

    public void register(Context context,String username,String email ,String password,String mobileno){

        String URL= ConstantData.REGISTER_METHOD;
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try{
                    Gson gson=new Gson();
                    PersonOutputModel p=gson.fromJson(response,PersonOutputModel.class);
                    if(p.isStatus()){
                        Toast.makeText(context, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }

                }catch (Exception e){
                    Toast.makeText(context, "ERROR" + e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "SERVER ERROR", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("username",username);
                map.put("email",email);
                map.put("password",password);
                map.put("phone",mobileno);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void login(Context context,String email,String password) {

        String URL = ConstantData.LOGIN_METHOD;
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    Gson gson = new Gson();
                    PersonOutputModel p = gson.fromJson(response, PersonOutputModel.class);
                    if (p.isStatus()) {
                        Toast.makeText(context, "User Login Successfully", Toast.LENGTH_SHORT).show();
                        SharedPreferences sp = context.getSharedPreferences(ConstantData.SP_NAME, Context.MODE_PRIVATE);
                        SharedPreferences.Editor ed = sp.edit();

                        ed.putString(ConstantData.KEY_USERNAME, p.getPerson().getUsername());
                        ed.putString(ConstantData.KEY_EMAIL, p.getPerson().getEmail());
                        ed.putString(ConstantData.KEY_CONTACT, p.getPerson().getPhone());
                        ed.putString(ConstantData.KEY_USER_ID, p.getPerson().getId());
                        ed.putBoolean(ConstantData.KEY_SP_ISLOGIN, true);
                        ed.apply();

                        Intent intent = new Intent(context, HomeActivity.class);
                        context.startActivity(intent);
                    }
                    else {

                        Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    Toast.makeText(context, "ERROR" + e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "SERVER ERROR", Toast.LENGTH_SHORT).show();

            }
        }) {
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("email", email);
                map.put("password", password);

                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
