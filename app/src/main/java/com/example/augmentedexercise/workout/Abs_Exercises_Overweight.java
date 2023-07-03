package com.example.augmentedexercise.workout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.augmentedexercise.R;
import com.example.augmentedexercise.bmi_module.BMI_Normal;
import com.example.augmentedexercise.bmi_module.BMI_Overweight;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Abs_Exercises_Overweight extends AppCompatActivity {

    FirebaseFirestore fStore;
    DocumentReference documentReference;
    public int owAbs_Progress = 0;

    ImageView AeBtnBack2;

    CardView AePushup, AeBurpees, AeHighknee, AeMountainClimbing, AeStandingOpenGate, AeJumpingJack
            ,AeFlutterKickSquat, AeVsitBicycle, AeCobraStretch, AeBackwardLunge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abs_exercises_overweight);

        fStore = FirebaseFirestore.getInstance();

        AeBtnBack2 = (ImageView) findViewById(R.id.aebtnBack2);

        AePushup = (CardView) findViewById(R.id.aePushup2);
        AeBurpees = (CardView) findViewById(R.id.aeBurpees2);
        AeHighknee = (CardView) findViewById(R.id.aeHighKnee2);
        AeMountainClimbing = (CardView) findViewById(R.id.aeMountainClimbing2);
        AeStandingOpenGate = (CardView) findViewById(R.id.aeStandingOpenGate2);
        AeJumpingJack = (CardView) findViewById(R.id.aeJumpingJack2);
        AeFlutterKickSquat = (CardView) findViewById(R.id.aeFlutterKickSquat2);
        AeVsitBicycle = (CardView) findViewById(R.id.aeVSitBicycle2);
        AeCobraStretch = (CardView) findViewById(R.id.aeCobraStretch2);
        AeBackwardLunge = (CardView) findViewById(R.id.aeBackwardLunge2);

        AeBtnBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Abs_Exercises_Overweight.this, BMI_Overweight.class);
                startActivity(intent);
            }
        });

        JumpingJacks();
        PushUp();
        Burpees();
        Highknees();
        MountainClimbing();
        StandingOpenGate();
        FlutterKickSquat();
        VsitBicycle();
        CobraStretch();
        BackwardLunge();
    }
    //calls method
    private void JumpingJacks(){
        AeJumpingJack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1();
            }
        });
    }
    private void PushUp(){
        AePushup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog2();
            }
        });
    }
    private void Burpees(){
        AeBurpees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog3();
            }
        });
    }
    private void Highknees(){
        AeHighknee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4();
            }
        });
    }
    private void MountainClimbing(){
        AeMountainClimbing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog5();
            }
        });
    }
    private void StandingOpenGate(){
        AeStandingOpenGate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog6();
            }
        });
    }
    private void FlutterKickSquat(){
        AeFlutterKickSquat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog7();
            }
        });
    }
    private void VsitBicycle(){
        AeVsitBicycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog8();
            }
        });
    }
    private void CobraStretch(){
        AeCobraStretch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog9();
            }
        });
    }
    private void BackwardLunge(){
        AeBackwardLunge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog10();
            }
        });
    }


    //JumpingJacks
    private void showDialog1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Jumping_Jack_6ft5ny5zmc"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //Push Up
    private void showDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/PushUp_fozm1kg0ng"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //Burpees
    private void showDialog3(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Burpees_2l7apn8eea"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //High Knees
    private void showDialog4(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/High_Knees_tnpe1n5ysr"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //Mountain Climbing
    private void showDialog5(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Mountain_Climber_zr9linccu1"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //Standing Open Gate
    private void showDialog6(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Standing_Open_Gate_q2jq9"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //Flutter Kick Squat
    private void showDialog7(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/FlutterKickSquat_3u7t4k2bdw"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //V Sit Bicycle
    private void showDialog8(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Vsit_Bicycle_Crunches_32gwdnlb9g"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //Cobra Stretch
    private void showDialog9(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/CobraStretch_tlntrjzex5"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
    //Backward Lunge
    private void showDialog10(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.abs_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Backward_Lunge_xffqvmya8e"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        owAbs_Progress = owAbs_Progress + 10;
                        documentReference = fStore.collection("users").document(userID);

                        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                            @Override
                            public void onSuccess(DocumentSnapshot documentSnapshot) {

                                documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        if (documentSnapshot.exists()){
                                            // Get the data from the document
                                            String firstname = documentSnapshot.getString("first_name");
                                            String lastname = documentSnapshot.getString("last_name");
                                            String email = documentSnapshot.getString("email");

                                            Map<String, Object> progress = new HashMap<>();
                                            progress.put("id", userID);
                                            progress.put("email", email);
                                            progress.put("first_name", firstname);
                                            progress.put("last_name", lastname);
                                            progress.put("abs_progress", owAbs_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("abs_overweight").document(userID)
                                                    .set(progress)
                                                    .addOnSuccessListener(aVoid -> {
                                                        // Data added successfully
                                                    })
                                                    .addOnFailureListener(e -> {
                                                        // Error occurred while adding data
                                                        //Log.e(TAG, "Error adding document", e);
                                                    });
                                        }
                                        else{}
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {

                                    }
                                });
                            }
                        });
                        dialogInterface.dismiss();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}