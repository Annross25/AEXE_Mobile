package com.example.augmentedexercise.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.augmentedexercise.R;

public class Conditions_Login extends AppCompatActivity {

    ImageView btnTermClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.augmentedexercise.R.layout.activity_conditions_login);

        btnTermClose = (ImageView) findViewById(R.id.btnTermClose);
        btnTermClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Conditions_Login.this, Login.class);
                startActivity(intent);
            }
        });
    }
}