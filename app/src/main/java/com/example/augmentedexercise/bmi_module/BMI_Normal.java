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
import com.example.augmentedexercise.workout.Abs_Exercises_Normal;
import com.example.augmentedexercise.workout.Chest_Exercises_Normal;
import com.example.augmentedexercise.workout.Fullbody_Exercises_Normal;
import com.example.augmentedexercise.workout.Fullbody_Exercises_Underweight;
import com.example.augmentedexercise.workout.Leg_and_Butt_Exercises_Normal;
import com.example.augmentedexercise.workout.Shoulder_Exercises_Normal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BMI_Normal extends AppCompatActivity {

    FirebaseFirestore fStore;
    ImageView btnNN, btnNB, btnBack;
    ImageButton btnfullBody, btnAbs, btnChest, btnLowerBody, btnShoulder;
    TextView fbProgress, absProgress, chestProgress, lowerbodyProgress, shoulderProgress;
    ProgressBar pbFBNormal, pbAbsNormal, pbChestNormal, pbLegsNormal, pbShoulderNormal;
    DocumentReference documentReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_normal);

        fStore = FirebaseFirestore.getInstance();

        pbFBNormal = (ProgressBar) findViewById(R.id.pbFullbodyNormal);
        pbAbsNormal = (ProgressBar) findViewById(R.id.pbAbsNormal);
        pbChestNormal = (ProgressBar) findViewById(R.id.pbChestNormal);
        pbLegsNormal = (ProgressBar) findViewById(R.id.pbLegsNormal);
        pbShoulderNormal = (ProgressBar) findViewById(R.id.pbShoulderNormal);

        btnBack = (ImageView) findViewById(R.id.backbtn);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Normal.this, Navigation_Drawer.class);
                startActivity(intent);
            }
        });

        Normal_Fullbody();
        Normal_Abs();
        Normal_Chest();
        Normal_Leg();
        Normal_Shoulder();

        bmiNormal();
    }
    private void bmiNormal(){
        btnfullBody = (ImageButton) findViewById(R.id.fullbody);
        btnAbs = (ImageButton) findViewById(R.id.abs);
        btnChest = (ImageButton) findViewById(R.id.chest);
        btnLowerBody = (ImageButton) findViewById(R.id.leg);
        btnShoulder = (ImageButton) findViewById(R.id.shoulder);

        btnNN = (ImageView) findViewById(R.id.btnNNext);
        btnNN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Normal.this, BMI_Overweight.class);
                startActivity(intent);
            }
        });

        btnNB = (ImageView) findViewById(R.id.btnNBack);
        btnNB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Normal.this, BMI_Underweight.class);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Normal.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Normal.this, Fullbody_Exercises_Normal.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Normal.this, Fullbody_Exercises_Normal.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to Abs Exercises
        btnAbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = absProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Normal.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Normal.this, Abs_Exercises_Normal.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Normal.this, Abs_Exercises_Normal.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to Chest Exercises
        btnChest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = chestProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Normal.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Normal.this, Chest_Exercises_Normal.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Normal.this, Chest_Exercises_Normal.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to Lowerbody Exercises
        btnLowerBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = lowerbodyProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Normal.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Normal.this, Leg_and_Butt_Exercises_Normal.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Normal.this, Leg_and_Butt_Exercises_Normal.class);
                    startActivity(intent);
                }
            }
        });

        //Intent to Shoulder Exercises
        btnShoulder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = shoulderProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Normal.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Normal.this, Shoulder_Exercises_Normal.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Normal.this, Shoulder_Exercises_Normal.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void Normal_Fullbody(){
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbFBNormal = (ProgressBar) findViewById(R.id.pbFullbodyNormal);
        fbProgress = (TextView) findViewById(R.id.fb_percentage);

        documentReference = fStore.collection("fullbody_normal").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Progress progress = document.toObject(Progress.class);
                        Long fbNormal = document.getLong("fullbody_progress");
                        if(fbNormal <= 99){
                            fbProgress.setText(String.valueOf(fbNormal + "%"));
                        }
                        else{
                            fbProgress.setText("COMPLETED");
                        }
                        pbFBNormal.setProgress(progress.getFullbody_progress());
                        Log.d("MY_APP", "User ID: " + userID);
                    }
                }
            }
        });
    }
    private void Normal_Abs() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbAbsNormal = (ProgressBar) findViewById(R.id.pbAbsNormal);
        absProgress = (TextView) findViewById(R.id.abs_percentage);

        documentReference = fStore.collection("abs_normal").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbAbsNormal.setProgress(progress.getAbs_progress());
                        //Getting the value of progress (number)
                        Long absNormal = document.getLong("abs_progress");
                        if(absNormal <= 99){
                            absProgress.setText(String.valueOf(absNormal) + "%");
                        }
                        else{
                            absProgress.setText("COMPLETED");
                        }
                    }
                }
            }
        });
    }
    private void Normal_Chest() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbChestNormal = (ProgressBar) findViewById(R.id.pbChestNormal);
        chestProgress = (TextView) findViewById(R.id.chest_percentage);

        documentReference = fStore.collection("chest_normal").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbChestNormal.setProgress(progress.getChest_progress());
                        //Getting the value of progress (number)
                        Long chestNormal = document.getLong("chest_progress");
                        if(chestNormal <= 99){
                            chestProgress.setText(String.valueOf(chestNormal) + "%");
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
    private void Normal_Leg() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbLegsNormal = (ProgressBar) findViewById(R.id.pbLegsNormal);
        lowerbodyProgress = (TextView) findViewById(R.id.leg_percentage);

        documentReference = fStore.collection("leg_normal").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbLegsNormal.setProgress(progress.getLeg_progress());
                        //Getting the value of progress (number)
                        Long legNormal= document.getLong("leg_progress");
                        if(legNormal <= 99){
                            lowerbodyProgress.setText(String.valueOf(legNormal) + "%");
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
    private void Normal_Shoulder() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbShoulderNormal = (ProgressBar) findViewById(R.id.pbShoulderNormal);
        shoulderProgress = (TextView) findViewById(R.id.shoulder_percentage);

        documentReference = fStore.collection("shoulder_normal").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbShoulderNormal.setProgress(progress.getShoulder_progress());
                        //Getting the value of progress (number)
                        Long shoulderNormal = document.getLong("shoulder_progress");
                        if(shoulderNormal <= 99){
                            shoulderProgress.setText(String.valueOf(shoulderNormal + "%"));
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