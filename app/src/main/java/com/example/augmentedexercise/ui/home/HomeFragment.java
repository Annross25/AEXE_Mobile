package com.example.augmentedexercise.ui.home;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.augmentedexercise.R;
import com.example.augmentedexercise.adapter.PostAdapter;
import com.example.augmentedexercise.databinding.FragmentHomeBinding;
import com.example.augmentedexercise.model.Feed;
import com.example.augmentedexercise.model.Profile;
import com.example.augmentedexercise.model.User;
import com.example.augmentedexercise.ui.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.Executor;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeFragment extends Fragment {

    FirebaseFirestore fStore;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    ArrayList<Feed> mFeed; //Declaring an ArrayList called "mFeed" that will contain objects of type "Feed".
    ProgressDialog progressDialog;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Creates an instance of the FirebaseFirestore object, allowing access to Firestore database services.
        fStore = FirebaseFirestore.getInstance();

        //Stores the value of users userID
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        //Initialize TextView and CircleImageView objects through their corresponding IDs in the view hierarchy.
        TextView firstName = (TextView) view.findViewById(R.id.userFname);
        TextView lastName = (TextView) view.findViewById(R.id.userLname);
        TextView userEmail = (TextView) view.findViewById(R.id.userEmail);
        TextView userGender = (TextView) view.findViewById(R.id.userGender);
        TextView userAge = (TextView) view.findViewById(R.id.userAge);
        TextView userBirthday = (TextView) view.findViewById(R.id.userBirthday);
        TextView userAddress = (TextView) view.findViewById(R.id.userAddress);
        TextView userContact = (TextView) view.findViewById(R.id.userContact);
        TextView userId = (TextView) view.findViewById(R.id.userIdentification);
        TextView userRole = (TextView) view.findViewById(R.id.userRole);
        CircleImageView userDP = (CircleImageView) view.findViewById(R.id.profile_image);

        //retrieves a user profile document from a Firestore database for corresponding views
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if(document.exists()){
                        Profile profile = document.toObject(Profile.class);
                        firstName.setText(profile.getFirst_name());
                        lastName.setText(profile.getLast_name());
                        userEmail.setText(profile.getEmail());
                        userGender.setText(profile.getGender());
                        userAge.setText(profile.getAge());
                        userBirthday.setText(profile.getBirthday());
                        userAddress.setText(profile.getAddress());
                        userContact.setText(profile.getMobile_number());
                        userId.setText(profile.getId());
                        userRole.setText(profile.getRole());
                        String imageUrl = profile.getProfile_url();
                        if(imageUrl != null && !imageUrl.isEmpty()){
                            Glide.with(getContext()).load(imageUrl).into(userDP);
                        }
                    }
                }
            }
        });

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        // Initialize RecyclerView and PostAdapter
        recyclerView = view.findViewById(R.id.homeRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeed = new ArrayList<Feed>();
        postAdapter = new PostAdapter(getContext(), mFeed);
        recyclerView.setAdapter(postAdapter); // Set PostAdapter on RecyclerView

        EventChangeListener();

        return view;
    }
    private void EventChangeListener() {
        //Stores the value of users userID
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        fStore.collection("posts").whereEqualTo("id",userID).orderBy("timestamp", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(error != null){
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                            return;
                        }
                        for(DocumentChange dc : value.getDocumentChanges()){
                            if(dc.getType() == DocumentChange.Type.ADDED){
                                mFeed.add(dc.getDocument().toObject(Feed.class));
                            }
                            postAdapter.notifyDataSetChanged();
                            if(progressDialog.isShowing())
                                progressDialog.dismiss();
                        }
                    }
                });
    }
}