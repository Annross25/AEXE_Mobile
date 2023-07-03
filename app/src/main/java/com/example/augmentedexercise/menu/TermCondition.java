package com.example.augmentedexercise.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.augmentedexercise.Navigation_Drawer;
import com.example.augmentedexercise.R;

public class TermCondition extends AppCompatActivity {

    ImageView btnTermClose;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_condition);

        btnTermClose = (ImageView) findViewById(R.id.btnTermClose);
        btnTermClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TermCondition.this, Navigation_Drawer.class);
                startActivity(intent);
            }
        });
    }
}