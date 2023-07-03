package com.example.augmentedexercise.ui.login;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.augmentedexercise.Navigation_Drawer;
import com.example.augmentedexercise.R;
import com.example.augmentedexercise.menu.TermCondition;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Map;

public class Login extends AppCompatActivity {

    FirebaseFirestore fStore; //Firebase Databases

    EditText etEmail, etPassword;
    TextView tvTerms;
    //String userID;
    Button btnSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        fStore = FirebaseFirestore.getInstance();

        //ProgressDialog progressDialog = new ProgressDialog(this);
        //progressDialog.setCancelable(false);
        //progressDialog.setMessage("Fetching Data....");
        //progressDialog.show();

        etEmail = (EditText) findViewById(R.id.editTextEmail);
        etPassword = (EditText) findViewById(R.id.editTextPasswword);
        btnSignin = (Button) findViewById(R.id.btnSignin);
        tvTerms = (TextView) findViewById(R.id.tvTerms);

        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser(); //Firestore Database
            }
        });
        tvTerms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Conditions_Login.class);
                startActivity(intent);
            }
        });
    }
    //Firestore Database
    private void loginUser(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            if(!password.isEmpty()){
                ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

                if (isConnected) {
                    CollectionReference usersRef = fStore.collection("users");
                    Query query = usersRef.whereEqualTo("email", email).whereEqualTo("password", password);

                    query.get().addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {

                            QuerySnapshot querySnapshot = task.getResult();
                            if (!querySnapshot.isEmpty()) {
                                // User with given email and password exists in Firestore
                                Intent intent = new Intent(Login.this, Navigation_Drawer.class);
                                startActivity(intent);
                                Toast.makeText(Login.this, "Logging in...", Toast.LENGTH_SHORT).show();

                                DocumentSnapshot userDoc = querySnapshot.getDocuments().get(0);
                                Map<String, Object> userData = userDoc.getData();
                                Log.d("Data", userData.toString());

                                // stores the user ID in SharedPreferences
                                String userID = (String) userData.get("id");
                                SharedPreferences preferences = getSharedPreferences("MY_APP", MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.putString("id", userID);
                                editor.apply();
                                // Do something with user data, e.g. redirect to dashboard
                            } else {
                                // User with given email and password does not exist in Firestore
                                // Handle error
                                Toast.makeText(Login.this, "Incorrect Email or Password!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(Login.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                //etPassword.setError("Empty Fields are not Allowed");
                Toast.makeText(Login.this, "Please enter your password", Toast.LENGTH_SHORT).show();
            }
        }
        else if (email.isEmpty())
        {
            etEmail.setError("Empty Fields are not Allowed");
            Toast.makeText(Login.this, "Please enter your email", Toast.LENGTH_SHORT).show();
        }
    }
}

