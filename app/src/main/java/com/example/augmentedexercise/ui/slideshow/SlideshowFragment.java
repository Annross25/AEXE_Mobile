package com.example.augmentedexercise.ui.slideshow;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.augmentedexercise.R;
import com.example.augmentedexercise.bmi_module.BMI_Normal;
import com.example.augmentedexercise.bmi_module.BMI_Obese;
import com.example.augmentedexercise.bmi_module.BMI_Overweight;
import com.example.augmentedexercise.bmi_module.BMI_Senior;
import com.example.augmentedexercise.bmi_module.BMI_Underweight;
import com.example.augmentedexercise.databinding.FragmentSlideshowBinding;
import com.example.augmentedexercise.model.BMI;
import com.example.augmentedexercise.model.Profile;
import com.example.augmentedexercise.ui.gallery.Item_post;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SlideshowFragment extends Fragment {

    FirebaseFirestore fStore;
    CollectionReference collectionReference;
    DocumentReference documentReference;
    TextView tvAge;
    EditText etWeight, etFeet, etInch;
    TextView bmiResult;
    Button btnCalculate, btnCalendar;

    Handler handler = new Handler();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        View view = inflater.inflate(R.layout.fragment_slideshow, container, false);

        SharedPreferences preferences = getContext().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        //Creates an instance of the FirebaseFirestore object, allowing access to Firestore database services.
        fStore = FirebaseFirestore.getInstance();
        collectionReference = fStore.collection("bmi");
        documentReference = fStore.collection("bmi").document(userID);

        tvAge = (TextView) view.findViewById(R.id.tvAge);
        etWeight = (EditText) view.findViewById(R.id.etWeight);
        etFeet = (EditText) view.findViewById(R.id.etFeet);
        etInch = (EditText) view.findViewById(R.id.etInch);
        bmiResult = (TextView) view.findViewById(R.id.bmiResult);
        btnCalculate = (Button) view.findViewById(R.id.btnCalculate);
        btnCalendar = (Button) view.findViewById(R.id.btnCalendar);

        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), Calendar.class);
                startActivity(intent);
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String age = tvAge.getText().toString();
                int age2 = Integer.parseInt(age);
                String kg = etWeight.getText().toString();
                String ft = etFeet.getText().toString();
                String in = etInch.getText().toString();
                String bmi = bmiResult.getText().toString();

                if(kg.length()>0&&ft.length()>0&&in.length()>0) {

                    float weight = Float.parseFloat(kg);
                    float feet = Float.parseFloat(ft);
                    float inch = Float.parseFloat(in);
                    int a = Integer.parseInt(age);

                    float height = (float) (feet * 0.3048 + inch * 0.0254);
                    float bmiIndex = weight/(height*height);

                    if(age2 >= 62){
                        bmiResult.setText("You are Senior Citizen");
                        Intent intent = new Intent(getContext(), BMI_Senior.class);
                        //Delays intent for 2 seconds
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent);
                            }
                        },2000);
                    }
                    else if (bmiIndex < 18.5) {
                        bmiResult.setText("Underweight: " + bmiIndex);
                        Intent intent = new Intent(getContext(), BMI_Underweight.class);
                        Toast.makeText(getContext(), "You are Underweight", Toast.LENGTH_SHORT).show();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent);
                            }
                        },2000);
                    }
                    else if (bmiIndex >= 18.5 && bmiIndex <= 24.9) {
                        bmiResult.setText("Normal: " + bmiIndex);
                        Intent intent = new Intent(getContext(), BMI_Normal.class);
                        Toast.makeText(getContext(), "You are Normal", Toast.LENGTH_SHORT).show();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent);
                            }
                        },2000);
                    }
                    else if(bmiIndex >= 25 && bmiIndex <= 29.9){
                        bmiResult.setText("Overweight: " + bmiIndex);
                        Intent intent = new Intent(getContext(), BMI_Overweight.class);
                        Toast.makeText(getContext(), "You are Overweight", Toast.LENGTH_SHORT).show();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent);
                            }
                        },2000);
                    }
                    else {
                        bmiResult.setText("Obese: " + bmiIndex);
                        Intent intent = new Intent(getContext(), BMI_Obese.class);
                        Toast.makeText(getContext(), "You are Obese", Toast.LENGTH_SHORT).show();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                startActivity(intent);
                            }
                        },2000);
                    }
                }
                else
                {
                    bmiResult.setText("Please input all fields");
                }
                String weight = etWeight.getText().toString();
                String feet = etFeet.getText().toString();
                String inch = etInch.getText().toString();
                String userBMI = bmiResult.getText().toString();

                /*
                Map<String, Object> data = new HashMap<>();
                data.put("weight", weight);
                data.put("feet", feet);
                data.put("inch", inch);
                data.put("bmi", userBMI);
                data.put("id", userID);
                collectionReference.document().set(data);
                 */

                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot document = task.getResult();
                            if(document.exists()){
                                BMI body = document.toObject(BMI.class);
                                etWeight.setText(body.getWeight());
                                etFeet.setText(body.getFeet());
                                etInch.setText(body.getInch());
                                bmiResult.setText(body.getBmiResult());
                            }
                        }
                    }
                });
            }
        });

        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        BMI bmi = document.toObject(BMI.class);
                        tvAge.setText(bmi.getAge());
                    }
                }
            }
        });

        return view;
    }
}