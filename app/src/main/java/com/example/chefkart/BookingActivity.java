package com.example.chefkart;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.chefkart.api.BookingApi;
import com.example.chefkart.model.BookingModel;
import com.example.chefkart.model.ChefModel;
import com.example.chefkart.model.CouponOutputModel;
import com.example.chefkart.util.ConstantData;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

public class BookingActivity extends AppCompatActivity implements PaymentResultListener {

    ImageView Chef_img,imgBack;
    String c_o = "CASH";
    TextView tvCname,tvCprice,tvCdate,tvCtime;
    TextView tvView,tvSadd,tvAdd;
    String address;
    Button btnApply,btnConfirm,btnCheckOut;

    RadioButton rbtnCOD,rbtnOnline;
    EditText etCode;
    TextInputEditText t1,t2,t3,t4,t5,t6;

    RadioGroup rdbPayment;
    Button btnSaveAddress;
    String uid;
    SharedPreferences sp;
    String pid;
    double tot=0,gst=0,amt=0,c_discount=0;
    /*String getAddress="",getC_o="cash",c_code=0;*/
    TextView tvAmnt,Namnt,tvGst,Ngst,tvCouponDisc,NCouponDisc,tvTotalAmnt,NTotalAmnt;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_booking);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Chef_img=findViewById(R.id.Chef_img);
        tvCname=findViewById(R.id.tvCname);
        tvCprice=findViewById(R.id.tvCprice);
        tvCdate=findViewById(R.id.tvCdate);
        tvCtime=findViewById(R.id.tvCtime);
        tvView=findViewById(R.id.tvView);
        tvSadd=findViewById(R.id.tvSadd);
        btnApply=findViewById(R.id.btnApply);
        Namnt=findViewById(R.id.Namnt);
        tvCouponDisc=findViewById(R.id.NCouponDisc);
        Ngst=findViewById(R.id.Ngst);
        NCouponDisc=findViewById(R.id.NCouponDisc);
        NTotalAmnt=findViewById(R.id.NTotalAmnt);
        btnConfirm=findViewById(R.id.btnConfirm);
        etCode=findViewById(R.id.etCode);
        imgBack=findViewById(R.id.imgBack);
        rdbPayment = findViewById(R.id.rdbPayment);

        imgBack.setOnClickListener(v->{
            finish();
        });

        Intent intent = getIntent();
        ChefModel model = (ChefModel) intent.getSerializableExtra("chef");
        Glide.with(this)
                .load(ConstantData.SERVER_ADDRESS_IMG + model.getChef_pic())
                .into(Chef_img);
        tvCname.setText(model.getChef_name());
        tvCprice.setText(model.getAmount());
        Namnt.setText(model.getAmount());
        NCouponDisc.setText("0");
         gst= (Double.parseDouble(model.getAmount()) * 0.05);
         tot=Double.parseDouble(model.getAmount()) + (Double.parseDouble(model.getAmount()) * 0.05);
        Ngst.setText(gst+"");
        NTotalAmnt.setText(tot+"");
        tvCdate.setOnClickListener(v -> {
            // Get the current date
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            // Create a DatePickerDialog
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    v.getContext(),
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        // Format the selected date and set it to tvCdate
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        tvCdate.setText(selectedDate);
                    },
                    year, month, day
            );

            // Show the DatePickerDialog
            datePickerDialog.show();
        });



        tvCtime.setOnClickListener(v -> {
            // Get the current time
            final Calendar calendar = Calendar.getInstance();
            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);

            // Create a TimePickerDialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    v.getContext(),
                    (view, selectedHour, selectedMinute) -> {
                        // Format the selected time and set it to tvCtime
                        String selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute);
                        tvCtime.setText(selectedTime);
                    },
                    hour, minute, true // true for 24-hour format, false for 12-hour format
            );

            // Show the TimePickerDialog
            timePickerDialog.show();
        });

        sp=getSharedPreferences(ConstantData.SP_NAME,MODE_PRIVATE);
        uid=sp.getString(ConstantData.KEY_USER_ID,"0");
        if(uid.equals("0")){
            Intent intet=new Intent(BookingActivity.this,LoginActivity.class);
            startActivity(intet);
        }else{
//            new OrderApi().get_order(this,uid,"0");
        }


        final BottomSheetDialog bottomSheetTeachersDialog = new BottomSheetDialog(BookingActivity.this);

        View layout = LayoutInflater.from(BookingActivity.this).inflate(R.layout.activity_address,null);

        // passing our layout file to our bottom sheet dialog.
        bottomSheetTeachersDialog.setContentView(layout);
        t1=layout.findViewById(R.id.t1);
        t2=layout.findViewById(R.id.t2);
        t3=layout.findViewById(R.id.t3);
        t4=layout.findViewById(R.id.t4);
        t5=layout.findViewById(R.id.t5);
        t6=layout.findViewById(R.id.t6);
        btnSaveAddress=layout.findViewById(R.id.btnSaveAddress);

        bottomSheetTeachersDialog.setCancelable(false);

        bottomSheetTeachersDialog.setCanceledOnTouchOutside(true);
        btnSaveAddress.setOnClickListener(v -> {
            if (t1.getText().toString().trim().length()==0){
                Toast.makeText(this, "Please Enter House NO", Toast.LENGTH_SHORT).show();
            } else if (t2.getText().toString().trim().length()==0) {
                Toast.makeText(this, "Please Enter Address Line 1", Toast.LENGTH_SHORT).show();
            }else if (t3.getText().toString().trim().length()==0) {
                Toast.makeText(this, "Please Enter Address Line 2", Toast.LENGTH_SHORT).show();
            }else if (t4.getText().toString().trim().length()==0) {
                Toast.makeText(this, "Please Enter City", Toast.LENGTH_SHORT).show();
            }else if (t5.getText().toString().trim().length()==0) {
                Toast.makeText(this, "Please Enter State", Toast.LENGTH_SHORT).show();
            }else if (t6.getText().toString().trim().length()==0) {
                Toast.makeText(this, "Please Enter Pincode", Toast.LENGTH_SHORT).show();
            }else if (t6.getText().toString().trim().length()!=6) {
                Toast.makeText(this, "Please Enter Valid Pincode", Toast.LENGTH_SHORT).show();
            }else {
                address = t1.getText().toString() + t2.getText().toString() + t3.getText().toString()
                        + t4.getText().toString() + t5.getText().toString() + t6.getText().toString();
                tvSadd.setText(address);
                bottomSheetTeachersDialog.dismiss();
            }

        });

        tvSadd.setOnClickListener(v->{

            bottomSheetTeachersDialog.show();

        });

        btnApply.setOnClickListener(v -> {
            String code=etCode.getText().toString();
            if(code==null && code.isEmpty()){
                Toast.makeText(this, "Enter Coupon Code", Toast.LENGTH_SHORT).show();
            }else{
                new BookingApi().applyCoupon(this ,code);
            }
        });

        btnConfirm.setOnClickListener(view -> {

            if(address.equals("")){
                Toast.makeText(this, "Please add Delivery Address", Toast.LENGTH_SHORT).show();
            }else{
                if(rdbPayment.getCheckedRadioButtonId()==R.id.rbtnCOD) {
                    c_o="cash";
                    Toast.makeText(this, c_o, Toast.LENGTH_SHORT).show();
                }else{
                    c_o="online";
                    Toast.makeText(this, c_o, Toast.LENGTH_SHORT).show();

                }

                if(c_o.equals("cash")){
                    new BookingApi().cofirm(BookingActivity.this,uid,model.getId(),tvCdate.getText().toString(),
                            tvCtime.getText().toString(),tot+"",address,c_o,"","");

                }else{
                    pid = model.getId();
                    openRazorPay();
                }

            }


        });
//        btnConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if(uid.equals("0")){
//                   /* Intent i1=new Intent(BookingActivity.this, LoginActivity.class);
//                    startActivity(i1);
//
//*/
//                    Toast.makeText(BookingActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
//                }else{
//                    new BookingApi().cofirm(BookingActivity.this,uid,model.getId(),tvCdate.getText().toString(),
//                            tvCtime.getText().toString(),tot+"",address,c_o,"","");
//                }
//            }
//        });
    }

    @Override
    public void onPaymentSuccess(String s) {

        new BookingApi().cofirm(BookingActivity.this,uid,pid,tvCdate.getText().toString(),
                tvCtime.getText().toString(),tot+"",address,c_o,"","");

    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Payment Error", Toast.LENGTH_SHORT).show();
    }

    public void openRazorPay(){
        Checkout checkout = new Checkout();

        // set your id as below
        checkout.setKeyID("rzp_test_0TPmxWbU09u5JU");

        // set image
        checkout.setImage(R.mipmap.ic_launcher);

        // initialize json object
        JSONObject object = new JSONObject();
        try {
            // to put name
            object.put("name", "CHEFMATE");

            // put description
            object.put("description", "Test payment");

            // to set theme color
            object.put("theme.color", "#7954CA");

            // put the currency
            object.put("currency", "INR");

            // put amount
            object.put("amount", tot*100);

            // put mobile number
            object.put("prefill.contact", "7778872635");

            // put email
            object.put("prefill.email", "riddhinanavati08@gmail.com");

            // open razorpay to checkout activity
            checkout.open(BookingActivity.this, object);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setCoupon(CouponOutputModel c){
        c_discount = Double.parseDouble(c.getCoupon_data().getC_discount());
        tvCouponDisc.setText(c_discount + "");
        tot = tot - c_discount;
        NTotalAmnt.setText(tot + "");
    }
}