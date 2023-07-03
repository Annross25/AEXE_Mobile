package com.example.augmentedexercise.ui.gallery;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.augmentedexercise.R;

public class LargerImageActivity extends AppCompatActivity {

    private ImageView largerImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_larger_image);

        largerImageView = findViewById(R.id.largerImageView);

        // Retrieve the image URL from the intent extras
        String imageUrl = getIntent().getStringExtra("mediaUrl");

        // Load the image into the ImageView using an image loading library like Glide or Picasso
        Glide.with(this)
                .load(imageUrl)
                .into(largerImageView);
    }
}