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
import android.widget.ImageView;

import com.example.augmentedexercise.R;
import com.example.augmentedexercise.bmi_module.BMI_Normal;
import com.example.augmentedexercise.bmi_module.BMI_Underweight;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Chest_Exercises_Normal extends AppCompatActivity {

    FirebaseFirestore fStore;
    DocumentReference documentReference;
    public int nChest_Progress = 0;

    ImageView CeBtnBack4;
    CardView CeJumpingJack4, CeKneePushup4, CePushup4, CeStandingSideBend4, CeCobraStretch4, CeWideArmPushup4
            ,CeKneePushup_4, CePushup_4, CeStandingSideBend_4, CeCobrStretch_4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.augmentedexercise.R.layout.activity_chest_exercises_normal);

        fStore = FirebaseFirestore.getInstance();

        CeBtnBack4 = findViewById(R.id.ceBtnBack4);

        CeJumpingJack4 = (CardView) findViewById(R.id.ceJumpingJack4);
        CeKneePushup4 = (CardView) findViewById(R.id.ceKneePushup4);
        CePushup4 = (CardView) findViewById(R.id.cePushup4);
        CeStandingSideBend4 = (CardView) findViewById(R.id.ceStandingSideBend4);
        CeCobraStretch4 = (CardView) findViewById(R.id.ceCobraStretch4);
        CeWideArmPushup4 = (CardView) findViewById(R.id.ceWideArmPushup4);
        CeKneePushup_4 = (CardView) findViewById(R.id.ceKneePushup_4);
        CePushup_4 = (CardView) findViewById(R.id.cePushup_4);
        CeStandingSideBend_4 = (CardView) findViewById(R.id.ceStandingSideBend_4);
        CeCobrStretch_4 = (CardView) findViewById(R.id.ceCobraStretch_4);

        CeBtnBack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chest_Exercises_Normal.this, BMI_Normal.class);
                startActivity(intent);
            }
        });

        JumpingJack();
        KneePushup();
        Pushup();
        StandingSideBend();
        CobraStretch();
        WideArmPushup();
        CobraStretch2();
        KneePushup2();
        Pushup2();
        StandingSideBend2();
    }
    private void JumpingJack(){
        CeJumpingJack4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1();
            }
        });
    }
    private void KneePushup(){
        CeKneePushup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog2();
            }
        });
    }
    private void Pushup(){
        CePushup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog3();
            }
        });
    }
    private void StandingSideBend(){
        CeStandingSideBend4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4();
            }
        });
    }
    private void CobraStretch(){
        CeCobraStretch4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog5();
            }
        });
    }
    private void WideArmPushup(){
        CeWideArmPushup4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog6();
            }
        });
    }
    private void CobraStretch2(){
        CeCobrStretch_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog7();
            }
        });
    }
    private void KneePushup2(){
        CeKneePushup_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog8();
            }
        });
    }
    private void Pushup2(){
        CePushup_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog9();
            }
        });
    }
    private void StandingSideBend2(){
        CeStandingSideBend_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog10();
            }
        });
    }


    //Jumping Jacks
    private void showDialog1(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Jumping_Jack_6ft5ny5zmc"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    //Knee Push up
    private void showDialog2(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/KneePushUp_3phu8k557o"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    private void showDialog3(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/PushUp_fozm1kg0ng"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    //Standing Side Bend
    private void showDialog4(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/StandingSideBend_nqjf1klfk8"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    private void showDialog5(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/CobraStretch_tlntrjzex5"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    //Wide Arm Pushup
    private void showDialog6(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/WideArmPushUp_loeg6knu4t"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    private void showDialog7(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/CobraStretch_tlntrjzex5"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    //Knee Push up
    private void showDialog8(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/KneePushUp_3phu8k557o"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    //Push up
    private void showDialog9(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/PushUp_fozm1kg0ng"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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
    //Standing Side Bend
    private void showDialog10(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.chest_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/StandingSideBend_nqjf1klfk8"));
                        startActivity(intent);

                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        nChest_Progress = nChest_Progress + 10;
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
                                            progress.put("chest_progress", nChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_normal").document(userID)
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