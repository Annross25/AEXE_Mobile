package com.example.augmentedexercise.ui.chat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.augmentedexercise.Navigation_Drawer;
import com.example.augmentedexercise.R;
import com.example.augmentedexercise.adapter.MessageAdapter;
import com.example.augmentedexercise.databinding.ActivityChatDetailUserBinding;
import com.example.augmentedexercise.model.Message;
import com.example.augmentedexercise.ui.gallery.GalleryFragment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivityUser extends AppCompatActivity {

    FirebaseFirestore fStore;

    ImageView btnBack;
    ActivityChatDetailUserBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.augmentedexercise.R.layout.activity_chat_detail_user);
        binding = ActivityChatDetailUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fStore = FirebaseFirestore.getInstance();

        btnBack = (ImageView) findViewById(R.id.backArrow);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChatDetailActivityUser.this, Navigation_Drawer.class);
                startActivity(intent);
            }
        });

        SharedPreferences preferences = getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        final String senderId = userID;
        String receivedId = getIntent().getStringExtra("id");
        String first_name = getIntent().getStringExtra("first_name");
        String last_name = getIntent().getStringExtra("last_name");
        String userDP = getIntent().getStringExtra("profile_url");

        binding.userFname.setText(first_name);
        binding.userLname.setText(last_name);
        Glide.with(this)
                .load(userDP)
                .placeholder(R.drawable.img_dp_default)
                .centerInside()
                .into(binding.profileImage);

        final ArrayList<Message> messageModels = new ArrayList<>();
        final MessageAdapter messageAdapter = new MessageAdapter(messageModels, this, receivedId);

        binding.chatRecyclerView.setAdapter(messageAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatRecyclerView.setLayoutManager(layoutManager);

        final String senderRoom = senderId + receivedId;
        final String receiverRoom = receivedId + senderId;

        FirebaseFirestore.getInstance().collection("message")
                .document(senderRoom)
                .collection("message")
                .orderBy("timestamp", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot snapshot, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            return;
                        }

                        if (snapshot != null && !snapshot.isEmpty()) {
                            messageModels.clear();
                            for (DocumentSnapshot document : snapshot.getDocuments()) {
                                Message model = document.toObject(Message.class);
                                model.setMessageId(document.getId());
                                messageModels.add(model);
                            }
                            messageAdapter.notifyDataSetChanged();
                        }
                    }
                });

        binding.send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = binding.enterMessage.getText().toString();
                final Message model = new Message(senderId, message);
                model.setTimestamp(new Date().getTime());
                binding.enterMessage.setText("");

                FirebaseFirestore.getInstance().collection("message")
                        .document(senderRoom)
                        .collection("message")
                        .add(model)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                String messageId = documentReference.getId();

                                // Add the message to the receiver's chat room
                                FirebaseFirestore.getInstance().collection("message")
                                        .document(receiverRoom)
                                        .collection("message")
                                        .document(messageId)
                                        .set(model)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {
                                                //Log.d(TAG, "Message sent successfully");
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                //.e(TAG, "Error sending message", e);
                                            }
                                        });
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                //Log.e(TAG, "Error sending message", e);
                            }
                        });
            }
        });
    }
}