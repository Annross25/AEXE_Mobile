package com.example.augmentedexercise.ui.logout;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.augmentedexercise.R;
import com.example.augmentedexercise.ui.login.Login;
import com.example.augmentedexercise.ui.splash_screen.MainActivity;

public class LogoutFragment extends Fragment {

    Handler handler = new Handler();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_logout, container, false);

        //Display splash screen for a second
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getContext(), Login.class);
                startActivity(intent);
            }
        },1500);
        return view;
    }
}