package com.example.augmentedexercise.bmi_module;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.augmentedexercise.Navigation_Drawer;
import com.example.augmentedexercise.R;
import com.example.augmentedexercise.exercises.Senior_Exercises;

public class BMI_Senior extends AppCompatActivity {

    ImageView btnSB, btnSN, btnBack;

    ImageButton btnSenior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_senior);

        btnBack = (ImageView) findViewById(R.id.backbtn);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Senior.this, Navigation_Drawer.class);
                startActivity(intent);
            }
        });

        btnSB = (ImageView) findViewById(R.id.btnSBack);
        btnSB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Senior.this, BMI_Obese.class);
                startActivity(intent);
            }
        });

        btnSN = (ImageView) findViewById(R.id.btnSNext);
        btnSN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Senior.this, BMI_Underweight.class);
                startActivity(intent);
            }
        });

        btnSenior = (ImageButton) findViewById(R.id.btnSenior);
        btnSenior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Senior.this, Senior_Exercises.class);
                startActivity(intent);
            }
        });

    }
}