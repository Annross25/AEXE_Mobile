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
import com.example.augmentedexercise.bmi_module.BMI_Obese;
import com.example.augmentedexercise.bmi_module.BMI_Underweight;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Chest_Exercises_Obese extends AppCompatActivity {

    FirebaseFirestore fStore;
    DocumentReference documentReference;
    public int oChest_Progress = 0;

    ImageView CeBtnBack3;
    CardView CeJumpingJack3, CeKneePushup3, CePushup3, CeStandingSideBend3, CeCobraStretch3, CeWideArmPushup3
            , CeKneePushup_3, CePushup_3, CeStandingSideBend_3, CeCobrStretch_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.augmentedexercise.R.layout.activity_chest_exercises_obese);

        fStore = FirebaseFirestore.getInstance();

        CeBtnBack3 = (ImageView) findViewById(R.id.ceBtnBack3);

        CeJumpingJack3 = (CardView) findViewById(R.id.ceJumpingJack3);
        CeKneePushup3 = (CardView) findViewById(R.id.ceKneePushup3);
        CePushup3 = (CardView) findViewById(R.id.cePushup3);
        CeStandingSideBend3 = (CardView) findViewById(R.id.ceStandingSideBend3);
        CeCobraStretch3 = (CardView) findViewById(R.id.ceCobraStretch3);
        CeWideArmPushup3 = (CardView) findViewById(R.id.ceWideArmPushup3);
        CeKneePushup_3 = (CardView) findViewById(R.id.ceKneePushup_3);
        CePushup_3 = (CardView) findViewById(R.id.cePushup_3);
        CeStandingSideBend_3 = (CardView) findViewById(R.id.ceStandingSideBend_3);
        CeCobrStretch_3 = (CardView) findViewById(R.id.ceCobraStretch_3);

        CeBtnBack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Chest_Exercises_Obese.this, BMI_Obese.class);
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
        CeJumpingJack3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1();
            }
        });
    }
    private void KneePushup(){
        CeKneePushup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog2();
            }
        });
    }
    private void Pushup(){
        CePushup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog3();
            }
        });
    }
    private void StandingSideBend(){
        CeStandingSideBend3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4();
            }
        });
    }
    private void CobraStretch(){
        CeCobraStretch3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog5();
            }
        });
    }
    private void WideArmPushup(){
        CeWideArmPushup3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog6();
            }
        });
    }
    private void CobraStretch2(){
        CeCobrStretch_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog7();
            }
        });
    }
    private void KneePushup2(){
        CeKneePushup_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog8();
            }
        });
    }
    private void Pushup2(){
        CePushup_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog9();
            }
        });
    }
    private void StandingSideBend2(){
        CeStandingSideBend_3.setOnClickListener(new View.OnClickListener() {
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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
    //Knee Pushup
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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

                        oChest_Progress = oChest_Progress + 10;
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
                                            progress.put("chest_progress", oChest_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("chest_obese").document(userID)
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