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

public class Shoulder_Exercises_Underweight extends AppCompatActivity {

    FirebaseFirestore fStore;
    DocumentReference documentReference;
    public int uwShoulder_Progress = 0;

    ImageView SeBtnBack1;
    CardView SeJumpingJack, SeArmRaises, SeSideArmRaise, SeKneePushup, SeDoubleHipTouch
            ,SeJumpingJack2, SeArmRaises2, SeSideArmRaise2, SeKneePushup2, SeDoubleHipTouch2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.augmentedexercise.R.layout.activity_shoulder_exercises_underweight);

        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        fStore = FirebaseFirestore.getInstance();
        documentReference = fStore.collection("user_progress").document(userID);

        fStore = FirebaseFirestore.getInstance();

        SeBtnBack1 = (ImageView) findViewById(R.id.seBtnBack1);

        SeJumpingJack = (CardView) findViewById(R.id.seJumpingJack1);
        SeSideArmRaise = (CardView) findViewById(R.id.seSideArmRaise1);
        SeArmRaises = (CardView) findViewById(R.id.seArmRaises1);
        SeKneePushup = (CardView) findViewById(R.id.seKneePushup1);
        SeDoubleHipTouch = (CardView) findViewById(R.id.seDoubleHipTouch1);
        SeJumpingJack2 = (CardView) findViewById(R.id.seJumpingJack_1);
        SeSideArmRaise2 = (CardView) findViewById(R.id.seSideArmRaises_1);
        SeArmRaises2 = (CardView) findViewById(R.id.seArmRaises_1);
        SeKneePushup2 = (CardView) findViewById(R.id.seKneePushup_1);
        SeDoubleHipTouch2 = (CardView) findViewById(R.id.seDoubleHipTouch_1);

        SeBtnBack1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Shoulder_Exercises_Underweight.this, BMI_Underweight.class);
                startActivity(intent);
            }
        });

        JumpingJack();
        SideArmRaises();
        ArmRaises();
        KneePushup();
        DoubleHipTouch();
        JumpingJack2();
        SideArmRaises2();
        ArmRaises2();
        KneePushup2();
        DoubleHipTouch2();

    }
    private void JumpingJack(){
        SeJumpingJack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog1();
            }
        });
    }
    private void SideArmRaises(){
        SeSideArmRaise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog2();
            }
        });
    }
    private void ArmRaises(){
        SeArmRaises.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog3();
            }
        });
    }
    private void KneePushup(){
        SeKneePushup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog4();
            }
        });
    }
    private void DoubleHipTouch(){
        SeDoubleHipTouch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog5();
            }
        });
    }
    private void JumpingJack2(){
        SeJumpingJack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog6();
            }
        });
    }
    private void SideArmRaises2(){
        SeSideArmRaise2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog7();
            }
        });
    }
    private void ArmRaises2(){
        SeArmRaises2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog8();
            }
        });
    }
    private void KneePushup2(){
        SeKneePushup2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               showDialog9();
            }
        });
    }
    private void DoubleHipTouch2(){
        SeDoubleHipTouch2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog10();
            }
        });
    }



    //Jumping Jacks
    private void showDialog1() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        String message = getResources().getString(R.string.shoulder_description);
        builder.setMessage(message)
                .setPositiveButton("OKAY", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Jumping_Jack_6ft5ny5zmc"));
                        startActivity(intent);
                        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                        String userID = preferences.getString("id", "");

                        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                                            progress.put("shoulder_progress", uwShoulder_Progress);

                                            // Set the document ID to be the same as the "id" field
                                            fStore.collection("shoulder_underweight").document(userID)
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
    //Side Arm Raises
    private void showDialog2(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/SideArmRaises_jzd67kd910"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }
    //Arm Raises
    private void showDialog3(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/ArmRaises_vsa7cjufo9"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }
    //Knee Pushup
    private void showDialog4(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/KneePushUp_3phu8k557o"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }
    //Double Hip Touch
    private void showDialog5(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Double_Hip_Touch_fwd6eyh9af"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }
    //Jumping Jacks2
    private void showDialog6(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Jumping_Jack_6ft5ny5zmc"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }
    //Side Arm Raises2
    private void showDialog7(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/SideArmRaises_jzd67kd910"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }
    //Arm Raises2
    private void showDialog8(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/ArmRaises_vsa7cjufo9"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }
    //Knee Pushup2
    private void showDialog9(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/KneePushUp_3phu8k557o"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }
    //Double Hip Touch2
    private void showDialog10(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://mywebar.com/p/Double_Hip_Touch_fwd6eyh9af"));
        startActivity(intent);
        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        uwShoulder_Progress = uwShoulder_Progress + 10;
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
                            progress.put("shoulder_progress", uwShoulder_Progress);

                            // Set the document ID to be the same as the "id" field
                            fStore.collection("shoulder_underweight").document(userID)
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
    }

}