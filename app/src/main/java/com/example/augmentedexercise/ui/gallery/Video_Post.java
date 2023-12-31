package com.example.augmentedexercise.ui.gallery;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.augmentedexercise.Navigation_Drawer;
import com.example.augmentedexercise.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Video_Post extends AppCompatActivity {

    FirebaseFirestore fStore;
    FirebaseStorage fStorage;
    StorageReference storageRef;
    private static final int PICK_IMAGES_VIDEOS_CODE = 1234;
    private List<Uri> selectedUris = new ArrayList<>();

    LinearLayout btn_add;
    ImageView btnDiscard, imageView;
    VideoView videoView;
    Button btnPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_post);

        //Storage for posting images or videos
        fStore = FirebaseFirestore.getInstance();
        fStorage = FirebaseStorage.getInstance();
        storageRef = fStorage.getReference().child("video");

        videoView = (VideoView) findViewById(R.id.video_added);
        btn_add = (LinearLayout) findViewById(R.id.btn_addFile);
        btnPost = (Button) findViewById(R.id.btnPost);
        btnDiscard = (ImageView) findViewById(R.id.btnback);
        btnDiscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Video_Post.this, Navigation_Drawer.class);
                startActivity(intent);
                //Fragment fragment = new GalleryFragment();
                //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //transaction.replace(R.id.gallery_fragment, fragment).commit();
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("video/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, PICK_IMAGES_VIDEOS_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGES_VIDEOS_CODE && resultCode == RESULT_OK) {
            if (data.getData() != null) {
                // If the user picked only one item
                Uri selectedMediaUri = data.getData();
                StorageReference fileRef = storageRef.child(selectedMediaUri.getLastPathSegment());
                UploadTask uploadTask = fileRef.putFile(selectedMediaUri);
                String selectedMediaType = getContentResolver().getType(selectedMediaUri);
                if (selectedMediaType.startsWith("video/")) {
                    // Handle selected video
                    videoView.setVideoURI(selectedMediaUri);
                    videoView.start();
                }

                btnPost.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        SharedPreferences preferences = Video_Post.this.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
                                        String userID = preferences.getString("id", "");
                                        DocumentReference docRef = FirebaseFirestore.getInstance().collection("users").document(userID);
                                        // Retrieve the document
                                        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                            @Override
                                            public void onSuccess(DocumentSnapshot documentSnapshot) {
                                                if (documentSnapshot.exists()) {
                                                    // Get the data from the document
                                                    String firstname = documentSnapshot.getString("first_name");
                                                    String lastname = documentSnapshot.getString("last_name");
                                                    String imageUrl = documentSnapshot.getString("profile_url");
                                                    String email = documentSnapshot.getString("email");
                                                    String role = documentSnapshot.getString("role");

                                                    // Retrieves user input from an EditText and stores it in a String variable named userCaption.
                                                    EditText caption = findViewById(R.id.userCaption);
                                                    String userCaption = caption.getText().toString();

                                                    // HashMap
                                                    Map<String, Object> post = new HashMap<>();
                                                    post.put("videoUrl", uri.toString());
                                                    post.put("timestamp", FieldValue.serverTimestamp());
                                                    post.put("id", userID);
                                                    post.put("first_name", firstname);
                                                    post.put("last_name", lastname);
                                                    post.put("role", role);
                                                    post.put("profile_url", imageUrl);
                                                    post.put("caption", userCaption);
                                                    post.put("email", email);
                                                    fStore.collection("posts").add(post);
                                                    Toast.makeText(Video_Post.this, "Successfully Uploaded", Toast.LENGTH_SHORT).show();

                                                    // Do something with the data

                                                } else {
                                                    // Handle document does not exist case
                                                }
                                            }
                                        }).addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                // Handle failure case
                                            }
                                        });
                                    }
                                });
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle failure case
                            }
                        });
                    }
                });
            } else if (data.getClipData() != null) {
                // If the user picked multiple items
                ClipData clipData = data.getClipData();
                for (int i = 0; i < clipData.getItemCount(); i++) {
                    Uri selectedMediaUri = clipData.getItemAt(i).getUri();
                    String selectedMediaType = getContentResolver().getType(selectedMediaUri);
                    if (selectedMediaType.startsWith("video/")) {
                        // Handle selected video
                        videoView.setVideoURI(selectedMediaUri);
                        videoView.start();
                    }
                }
            }
        }

    }
}