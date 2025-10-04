package com.example.chefkart;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.dhaval2404.imagepicker.ImagePicker;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_page extends Fragment {

    View view;
    CircleImageView Uimg;
    ImageView imgBack;
    TextView tvUname,tvUmail,tvAdd,tvAboutUs,tvContactUs,tvFaq,tvTermCon,tvPrivacyPolicy;
    Button btnEdit,btnLogout;
    ImageView iv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_profile_page, container, false);
        return view;
    }
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Uimg=view.findViewById(R.id.Uimg);
        tvUname=view.findViewById(R.id.tvUname);
        tvUmail=view.findViewById(R.id.tvUmail);
        tvAdd=view.findViewById(R.id.tvAdd);
        tvAboutUs=view.findViewById(R.id.tvAboutUs);
        tvContactUs=view.findViewById(R.id.tvContactUs);
        //tvFaq=view.findViewById(R.id.tvFaq);
        tvTermCon=view.findViewById(R.id.tvTermCon);
        tvPrivacyPolicy=view.findViewById(R.id.tvPrivacyPolicy);
        btnLogout=view.findViewById(R.id.btnLogout);
        //imgBack=view.findViewById(R.id.imgBack);

        //from reg
        SharedPreferences sp = getActivity().getSharedPreferences("SP_USER", MODE_PRIVATE);
        String username = sp.getString("KEY_USER", "");
        String email = sp.getString("KEY_EMAIL", "");
        tvUname.setText(username);
        tvUmail.setText(email);


        tvContactUs.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ContactUsActivity.class);
            startActivity(intent);
        });

        tvAboutUs.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AboutUsActivity.class);
            startActivity(intent);
        });

        tvPrivacyPolicy.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), PrivacyPolicyActivity.class);
            startActivity(intent);
        });

        tvTermCon.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TermActivity.class);
            startActivity(intent);
        });

        iv=view.findViewById(R.id.iv);
        iv.setOnClickListener(v -> {
            ImagePicker.with(this)
                    .crop()	    			//Crop image(Optional), Check Customization for more option
                    .compress(1024)			//Final image size will be less than 1 MB(Optional)
                    .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                    .start(1001);

        });

        btnLogout.setOnClickListener(view1 -> {
            //clear sharedPreference
            SharedPreferences.Editor editor=sp.edit();
            editor.clear();
            editor.commit();

            AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
            builder.setTitle("LOGOUT !");
            builder.setMessage("Are you sure ?");
            builder.setPositiveButton("YES", ((dialogInterface, i) -> {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }));
            builder.setNegativeButton("NO",((dialogInterface, i) -> {
                        Toast.makeText(getActivity(), "Logged in currently", Toast.LENGTH_SHORT).show();
                    }))
                    .show();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {
            if (resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
                Uri uri = data.getData();
                iv.setImageURI(uri);
            } else if (resultCode == ImagePicker.RESULT_ERROR) {
                String error = ImagePicker.Companion.getError(data);
                Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Task Cancelled", Toast.LENGTH_SHORT).show();
            }
        }
    }
}