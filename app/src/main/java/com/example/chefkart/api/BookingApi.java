package com.example.chefkart.api;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.autofill.AutofillId;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.chefkart.BookingActivity;
import com.example.chefkart.BookingHistoryActivity;
import com.example.chefkart.LoginActivity;
import com.example.chefkart.ThankuActivity;
import com.example.chefkart.model.BookingOutputModel;
import com.example.chefkart.model.CouponOutputModel;
import com.example.chefkart.model.PersonOutputModel;
import com.example.chefkart.util.ConstantData;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class BookingApi {
    public void cofirm(Context context,String uid,String pid,String date,String time,String tot_amount,String address,String c_o,String c_discount,String c_code){


        ProgressDialog dialog=new ProgressDialog(context);
        dialog.setTitle("Please Wait...");
        dialog.setCancelable(false);
        dialog.show();

        String URL= ConstantData.BOOKING_METHOD;
        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                dialog.dismiss();
                try{

                    Gson gson=new Gson();
                    BookingOutputModel p=gson.fromJson(response,BookingOutputModel.class);
                    if(p.isStatus()){
                        Toast.makeText(context, "Chef booked Succefully", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(context, ThankuActivity.class);
                        context.startActivity(intent);
                    }

                }catch (Exception e){
                    dialog.dismiss();

                    Toast.makeText(context, "ERROR" + e.toString(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dialog.dismiss();

                Toast.makeText(context, "SERVER ERROR", Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("uid", uid);
                map.put("pid",pid);
                map.put("tot_amount",tot_amount);
                map.put("address",address);
                map.put("date",date);
                map.put("time",time);
                map.put("cooking_date",date);
                map.put("cooking_date",time);
                map.put("c_o",c_o);
                map.put("c_discount",c_discount);
                map.put("c_code",c_code);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }

    public void applyCoupon(Context context,String code){
        RequestQueue queue=Volley.newRequestQueue(context);
        StringRequest request=new StringRequest(
                Request.Method.POST,
                ConstantData.APPLY_COUPON_METHOD,
                response -> {
                    CouponOutputModel c= new Gson().fromJson(response,CouponOutputModel.class);
                    if(c.isStatus()){
                        ((BookingActivity)context).setCoupon(c);

                    }else{
                        Toast.makeText(context, "Invalid Coupon code", Toast.LENGTH_SHORT).show();
                    }
                },
                error -> {
                    Toast.makeText(context, "SERVER ERROR:" + error, Toast.LENGTH_SHORT).show();
                    Log.e("APPLY COUPON ERROR",error.toString());
                }
        ){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> map=new HashMap<>();
                map.put("code",code);
                return map;
            }
        };
        queue.add(request);
    }

    public void order_history(Context context, String uid,String status){

        //CustomProgressDialog customProgressDialog=new CustomProgressDialog(context);
        //customProgressDialog.show();


        RequestQueue requestQueue= Volley.newRequestQueue(context);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, ConstantData.ORDER_HISTORY_METHOD, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //customProgressDialog.dismiss();
                BookingOutputModel p= new Gson().fromJson(response,BookingOutputModel.class);
                if(p.isStatus()){
                        Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                        ((BookingHistoryActivity)context).set(p);


                }else{
                    Toast.makeText(context, p.getMessage(), Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //customProgressDialog.dismiss();
                Toast.makeText(context, "error:"+volleyError.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map=new HashMap<>();
                map.put("uid",uid);
                map.put("status",status);
                return map;
            }
        };
        requestQueue.add(stringRequest);
    }
}
