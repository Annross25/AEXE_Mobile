package com.example.augmentedexercise.bmi_module;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
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
import com.example.augmentedexercise.workout.Abs_Exercises_Underweight;
import com.example.augmentedexercise.workout.Chest_Exercises_Underweight;
import com.example.augmentedexercise.workout.Fullbody_Exercises_Underweight;
import com.example.augmentedexercise.workout.LegAndButt_Exercises_Underweight;
import com.example.augmentedexercise.workout.Shoulder_Exercises_Underweight;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class BMI_Underweight extends AppCompatActivity {

    FirebaseFirestore fStore;
    DocumentReference documentReference;
    AlertDialog.Builder dialogBox;
    ProgressBar pbFullbody, pbAbs, pbChest, pbLeg, pbShoulder;
    ImageView btnUWB, btnUWN, btnBack;
    ImageButton btnfullBody, btnAbs, btnChest, btnLowerBody, btnShoulder;
    TextView fbProgress, absProgress, chestProgress, lowerbodyProgress, shoulderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_underweight);

        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        fStore = FirebaseFirestore.getInstance();

        btnUWB = (ImageView) findViewById(R.id.btnUWBack);
        btnUWN = (ImageView) findViewById(R.id.btnUWNext);
        btnBack = (ImageView) findViewById(R.id.backbtn);

        pbFullbody = (ProgressBar) findViewById(R.id.pbFullbodyUnderweight);
        pbAbs = (ProgressBar) findViewById(R.id.pbAbsUnderweight);
        pbChest = (ProgressBar) findViewById(R.id.pbChestUnderweight);
        pbLeg = (ProgressBar) findViewById(R.id.pbLegsUnderweight);
        pbShoulder = (ProgressBar) findViewById(R.id.pbShoulderUnderweight);

        btnfullBody = (ImageButton) findViewById(R.id.fullbody);
        btnAbs = (ImageButton) findViewById(R.id.abs);
        btnChest = (ImageButton) findViewById(R.id.chest);
        btnLowerBody = (ImageButton) findViewById(R.id.leg);
        btnShoulder = (ImageButton) findViewById(R.id.shoulder);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Underweight.this, Navigation_Drawer.class);
                startActivity(intent);
            }
        });

        Underweight_Fullbody();
        Underweight_Abs();
        Underweight_Chest();
        Underweight_Leg();
        Underweight_Shoulder();

        //Navigate Back
        btnUWB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Underweight.this, BMI_Senior.class);
                startActivity(intent);
            }
        });
        //Next Button
        btnUWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(BMI_Underweight.this, BMI_Normal.class);
                startActivity(intent);
            }
        });
    }
    //Methods of BMI
    private void Underweight_Fullbody(){
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbFullbody = (ProgressBar) findViewById(R.id.pbFullbodyUnderweight);
        fbProgress = (TextView) findViewById(R.id.fb_percentage);

        documentReference = fStore.collection("fullbody_underweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbFullbody.setProgress(progress.getFullbody_progress());
                        //Getting the value of progress (number)
                        Long fbUnderweight = document.getLong("fullbody_progress");
                        if(fbUnderweight <= 99){
                            fbProgress.setText(String.valueOf(fbUnderweight + "%"));
                        }
                        else{
                            fbProgress.setText("COMPLETED");
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
                }
            }
        });
        //Intent to Fullbody Exercises
        btnfullBody.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String progressText = fbProgress.getText().toString();
                if(progressText.equals("COMPLETED")){
                    String message = getResources().getString(R.string.congratulatory_message);
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Underweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Underweight.this, Fullbody_Exercises_Underweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Underweight.this, Fullbody_Exercises_Underweight.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void Underweight_Abs() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbAbs = (ProgressBar) findViewById(R.id.pbAbsUnderweight);
        absProgress = (TextView) findViewById(R.id.abs_percentage);

        documentReference = fStore.collection("abs_underweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbAbs.setProgress(progress.getAbs_progress());
                        //Getting the value of progress (number)
                        Long absUnderweight = document.getLong("abs_progress");
                        if(absUnderweight <= 99){
                            absProgress.setText(String.valueOf(absUnderweight) + "%");
                        }
                        else{
                            absProgress.setText("COMPLETED");
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Underweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Underweight.this, Abs_Exercises_Underweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Underweight.this, Abs_Exercises_Underweight.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void Underweight_Chest() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbChest = (ProgressBar) findViewById(R.id.pbChestUnderweight);
        chestProgress = (TextView) findViewById(R.id.chest_percentage);

        documentReference = fStore.collection("chest_underweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbChest.setProgress(progress.getChest_progress());
                        //Getting the value of progress (number)
                        Long chestUnderweight = document.getLong("chest_progress");
                        if(chestUnderweight <= 99){
                            chestProgress.setText(String.valueOf(chestUnderweight) + "%");
                        }
                        else{
                            chestProgress.setText("COMPLETED");
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Underweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Underweight.this, Chest_Exercises_Underweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Underweight.this, Chest_Exercises_Underweight.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void Underweight_Leg() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbLeg = (ProgressBar) findViewById(R.id.pbLegsUnderweight);
        lowerbodyProgress = (TextView) findViewById(R.id.leg_percentage);

        documentReference = fStore.collection("leg_underweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbLeg.setProgress(progress.getLeg_progress());
                        //Getting the value of progress (number)
                        Long legUnderweight = document.getLong("leg_progress");
                        if(legUnderweight <= 99){
                            lowerbodyProgress.setText(String.valueOf(legUnderweight) + "%");
                        }
                        else{
                            lowerbodyProgress.setText("COMPLETED");
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Underweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Underweight.this, LegAndButt_Exercises_Underweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Underweight.this, LegAndButt_Exercises_Underweight.class);
                    startActivity(intent);
                }
            }
        });
    }
    private void Underweight_Shoulder() {
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        Log.d("MY_APP", "User ID: " + userID);

        pbShoulder = (ProgressBar) findViewById(R.id.pbShoulderUnderweight);
        shoulderProgress = (TextView) findViewById(R.id.shoulder_percentage);

        documentReference = fStore.collection("shoulder_underweight").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Progress progress = document.toObject(Progress.class);
                        //Getting the value of progress to display on Progress Bar
                        pbShoulder.setProgress(progress.getShoulder_progress());
                        //Getting the value of progress (number)
                        Long shoulderUnderweight = document.getLong("shoulder_progress");
                        if(shoulderUnderweight <= 99){
                            shoulderProgress.setText(String.valueOf(shoulderUnderweight + "%"));
                        }
                        else{
                            shoulderProgress.setText("COMPLETED");//Displays COMPLETED when progress is 100
                        }
                        Log.d("MY_APP", "User ID: " + userID);
                    }
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(BMI_Underweight.this);
                    builder.setMessage(message)
                            .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                    Intent intent = new Intent(BMI_Underweight.this, Shoulder_Exercises_Underweight.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent intent = new Intent(BMI_Underweight.this, Shoulder_Exercises_Underweight.class);
                    startActivity(intent);
                }

            }
        });
    }
}