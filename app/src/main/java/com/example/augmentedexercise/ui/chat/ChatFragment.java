package com.example.augmentedexercise.ui.chat;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.augmentedexercise.R;
import com.example.augmentedexercise.adapter.ChatAdapter;
import com.example.augmentedexercise.adapter.PostAdapter;
import com.example.augmentedexercise.model.Feed;
import com.example.augmentedexercise.model.Profile;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ChatFragment extends Fragment {

    FirebaseFirestore fStore;
    RecyclerView recyclerView;
    ChatAdapter chatAdapter;
    ArrayList<Profile> mProfile;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        fStore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = fStore.collection("users");


        //Retrieves data of current logged in user
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        // Initialize RecyclerView and PostAdapter
        recyclerView = view.findViewById(R.id.chatRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mProfile = new ArrayList<Profile>();
        chatAdapter = new ChatAdapter(getContext(), mProfile);
        recyclerView.setAdapter(chatAdapter); // Set PostAdapter on RecyclerView

        EventChangeListener();

        return view;
    }
    private void EventChangeListener(){
        fStore.collection("users").orderBy("first_name", Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            return;
                        }for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                mProfile.add(dc.getDocument().toObject(Profile.class));
                            }
                            chatAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}