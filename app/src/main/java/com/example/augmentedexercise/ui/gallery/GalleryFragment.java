package com.example.augmentedexercise.ui.gallery;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.augmentedexercise.Navigation_Drawer;
import com.example.augmentedexercise.R;
import com.example.augmentedexercise.adapter.PostAdapter;
import com.example.augmentedexercise.databinding.FragmentGalleryBinding;
import com.example.augmentedexercise.model.Feed;
import com.example.augmentedexercise.ui.login.Login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class GalleryFragment extends Fragment {

    FirebaseFirestore fStore;
    RecyclerView recyclerView;
    PostAdapter postAdapter;
    ArrayList<Feed> mFeed; //Declaring an ArrayList called "mFeed" that will contain objects of type "Feed".
    CircleImageView feedDP;
    Button postButton;
    ImageView btnPost;
    ProgressDialog progressDialog;

    public FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        fStore = FirebaseFirestore.getInstance();
        CollectionReference collectionReference = fStore.collection("posts");

        //Retrieves data of current logged in user
        SharedPreferences preferences = getActivity().getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");

        //Displays the users Display Picture
        feedDP = (CircleImageView) view.findViewById(R.id.userFeedDP);
        DocumentReference documentReference = fStore.collection("users").document(userID);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                DocumentSnapshot document = task.getResult();
                Feed feed = document.toObject(Feed.class);
                String imageUrl = feed.getProfile_url();
                if(imageUrl != null && !imageUrl.isEmpty()){
                    Glide.with(getContext()).load(imageUrl).into(feedDP);
                }
            }
        });

        feedDP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Navigation_Drawer.class);
                startActivity(intent);
            }
        });

        //Intents the Post Button into Item_Post Activity
        postButton = (Button) view.findViewById(R.id.postButton);
        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Item_post.class);
                startActivity(intent);
            }
        });

        //Intents the Post Button into Item_Post Activity
        btnPost = (ImageView) view.findViewById(R.id.postImage);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Video_Post.class);
                startActivity(intent);
            }
        });

        //Puts Progress Dialog
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Fetching Data...");
        progressDialog.show();

        // Initialize RecyclerView and PostAdapter
        recyclerView = view.findViewById(R.id.postRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mFeed = new ArrayList<Feed>();
        postAdapter = new PostAdapter(getContext(), mFeed);
        recyclerView.setAdapter(postAdapter); // Set PostAdapter on RecyclerView

        EventChangeListener();

        return view;
    }
    private void EventChangeListener() {
        fStore.collection("posts").orderBy("timestamp", Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (error != null) {
                            if (progressDialog.isShowing())
                                progressDialog.dismiss();
                            return;
                        }
                        for (DocumentChange dc : value.getDocumentChanges()) {
                            if (dc.getType() == DocumentChange.Type.ADDED) {
                                Feed feed = dc.getDocument().toObject(Feed.class);
                                feed.setVideoUrl(dc.getDocument().getString("videoUrl"));
                                feed.setMediaUrl(dc.getDocument().getString("mediaUrl"));
                                mFeed.add(feed);
                            }
                        }
                        postAdapter.notifyDataSetChanged();
                        if (progressDialog.isShowing())
                            progressDialog.dismiss();
                    }
                });
    }

}