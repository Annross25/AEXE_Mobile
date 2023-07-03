package com.example.augmentedexercise.bmi_module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.augmentedexercise.Navigation_Drawer;
import com.example.augmentedexercise.R;
import com.example.augmentedexercise.model.Progress;
import com.example.augmentedexercise.workout.Abs_Exercises_Overweight;
import com.example.augmentedexercise.workout.Chest_Exercises_Overweight;
import com.example.augmentedexercise.workout.Fullbody_Exercises_Normal;
import com.example.augmentedexercise.workout.Fullbody_Exercises_Overweight;
import com.example.augmentedexercise.workout.Leg_and_Butt_Exercises_Overweight;
import com.example.augmentedexercise.workout.Shoulder_Exercises_Overweight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BMI_Overweight extends AppCompatActivity {

    FirebaseFirestore fStore;
    DocumentReference documentReference;
    ImageView btnOWB, btnOWN, btnBack;
    ImageButton btnfullBody, btnAbs, btnChest, btnLowerBody, btnShoulder;
    TextView fbProgress, absProgress, chestProgress, lowerbodyProgress, shoulderProgress;
    ProgressBar pbFBOverweight, pbAbsOverweight, pbChestOverweight, pbLegsOverweight, pbShoulderOverweight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_overweight);

        fStore = FirebaseFirestore.getInstance();

        btnfullBody = (ImageButton) findViewById(R.id.fullbody);
        btnAbs = (ImageButton) findViewById(R.id.abs);
        btnChest = (ImageButton) findViewById(R.id.chest);
        btnLowerBody = (ImageButton) findViewById(R.id.leg);
        btnShoulder = (ImageButton) findViewById(R.id.shoulder);

        Overweight_Fullbody();
        Overweight_Abs();
        Overweight_Chest();
        Overweight_Leg();
        Overweight_Shoulder();

        btnBack = (ImageView) findViewById(R.id.backbtn);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Overweight.this, Navigation_Drawer.class);
                startActivity(intent);
            }
        });

        btnOWB = (ImageView) findViewById(R.id.btnOWBack);
        btnOWB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Overweight.this, BMI_Normal.class);
                startActivity(intent);
            }
        });

        btnOWN = (ImageView) findViewById(R.id.btnOWNext);
        btnOWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Overweight.this, BMI_Obese.class);
                startActivity(intent);
            }
        });

        //Intent to Fullbody Exercises
        btnfullBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = fbProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Overweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Overweight.this, Fullbody_Exercises_Overweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Overweight.this, Fullbody_Exercises_Overweight.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to Abs Exercises
        btnAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = fbProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Overweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Overweight.this, Abs_Exercises_Overweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Overweight.this, Abs_Exercises_Overweight.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to Chest Exercises
        btnChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = fbProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Overweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Overweight.this, Chest_Exercises_Overweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Overweight.this, Chest_Exercises_Overweight.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to Lowerbody Exercises
        btnLowerBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = fbProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Overweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Overweight.this, Leg_and_Butt_Exercises_Overweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Overweight.this, Leg_and_Butt_Exercises_Overweight.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to Shoulder Exercises
        btnShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = fbProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Overweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Overweight.this, Shoulder_Exercises_Overweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Overweight.this, Shoulder_Exercises_Overweight.class);
                    startActivity(intent);
                }
            }
        });
    }

    //Methods
    private void Overweight_Fullbody(){
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbFBOverweight = (ProgressBar) findViewById(R.id.pbFullbodyOverweight);
        fbProgress = (TextView) findViewById(R.id.fb_percentage);

        documentReference = fStore.collection("fullbody_overweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbFBOverweight.setProgress(progress.getFullbody_progress());
                        //Getting the value of progress (number)
                        Long fbOverweight = document.getLong("fullbody_progress");
                        if(fbOverweight <= 99){
                            fbProgress.setText(String.valueOf(fbOverweight + "%"));
                        }
                        else{
                            fbProgress.setText("COMPLETED");
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
                }
            }
        });
    }
    private void Overweight_Abs() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbAbsOverweight = (ProgressBar) findViewById(R.id.pbAbsOverweight);
        absProgress = (TextView) findViewById(R.id.abs_percentage);

        documentReference = fStore.collection("abs_overweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbAbsOverweight.setProgress(progress.getAbs_progress());
                        //Getting the value of progress (number)
                        Long absOverweight = document.getLong("abs_progress");
                        if(absOverweight <= 99){
                            absProgress.setText(String.valueOf(absOverweight) + "%");
                        }
                        else{
                            absProgress.setText("COMPLETED");
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
                }
            }
        });
    }
    private void Overweight_Chest() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbChestOverweight = (ProgressBar) findViewById(R.id.pbChestOverweight);
        chestProgress = (TextView) findViewById(R.id.chest_percentage);

        documentReference = fStore.collection("chest_overweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbChestOverweight.setProgress(progress.getChest_progress());
                        //Getting the value of progress (number)
                        Long chestOverweight = document.getLong("chest_progress");
                        if(chestOverweight <= 99){
                            chestProgress.setText(String.valueOf(chestOverweight) + "%");
                        }
                        else{
                            chestProgress.setText("COMPLETED");
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
                }
            }
        });
    }
    private void Overweight_Leg() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbLegsOverweight = (ProgressBar) findViewById(R.id.pbLegsOverweight);
        lowerbodyProgress = (TextView) findViewById(R.id.leg_percentage);

        documentReference = fStore.collection("leg_overweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress (number)
                        pbLegsOverweight.setProgress(progress.getLeg_progress());
                        Long legOverweight = document.getLong("leg_progress");
                        if(legOverweight <= 99){
                            lowerbodyProgress.setText(String.valueOf(legOverweight) + "%");
                        }
                        else{
                            lowerbodyProgress.setText("COMPLETED");
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
                }
            }
        });
    }
    private void Overweight_Shoulder() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbShoulderOverweight = (ProgressBar) findViewById(R.id.pbShoulderOverweight);
        shoulderProgress = (TextView) findViewById(R.id.shoulder_percentage);

        documentReference = fStore.collection("shoulder_overweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress (number)
                        pbShoulderOverweight.setProgress(progress.getShoulder_progress());
                        Long shoulderOverweight = document.getLong("shoulder_progress");
                        if(shoulderOverweight <= 99){
                            shoulderProgress.setText(String.valueOf(shoulderOverweight+ "%"));
                        }
                        else{
                            shoulderProgress.setText("COMPLETED");//Displays COMPLETED when progress is 100
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
                }
            }
        });
    }
}