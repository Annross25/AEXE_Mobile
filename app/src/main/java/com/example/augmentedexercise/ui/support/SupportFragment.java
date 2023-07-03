package com.example.augmentedexercise.ui.support;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.augmentedexercise.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SupportFragment extends Fragment {

    ImageView ivGym, ivMap;
    TextView tvFB, tvEmail, tvFAQ;
    FloatingActionButton fab;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_support, container, false);

        ivGym = (ImageView) view.findViewById(R.id.iv_place);
        ivMap = (ImageView) view.findViewById(R.id.map);
        tvFB = (TextView) view.findViewById(R.id.fb);
        tvEmail = (TextView) view.findViewById(R.id.email);
        tvFAQ = (TextView) view.findViewById(R.id.tvFAQ);
        fab = (FloatingActionButton) view.findViewById(R.id.fab);

        ivGym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Gym_Larger_Image.class);
                startActivity(intent);
            }
        });
        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Map_Larger_Image.class);
                startActivity(intent);
            }
        });
        tvFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/profile.php?id=100063900667022"));
                startActivity(intent);
            }
        });
        tvEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com/mail/u/0/#inbox"));
                startActivity(intent);
            }
        });
        tvFAQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://aexe-web.vercel.app/user_inquiries"));
                startActivity(intent);
            }
        });
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.messenger.com/t/103850659406697"));
                startActivity(intent);
            }
        });

        return view;
    }
}