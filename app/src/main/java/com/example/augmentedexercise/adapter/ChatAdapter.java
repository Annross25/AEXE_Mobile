package com.example.augmentedexercise.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.augmentedexercise.R;
import com.example.augmentedexercise.model.Feed;
import com.example.augmentedexercise.model.Profile;
import com.example.augmentedexercise.model.User;
import com.example.augmentedexercise.ui.chat.ChatDetailActivityUser;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    Context context;
    ArrayList<Profile> mProfile;

    public ChatAdapter(Context context, ArrayList<Profile> mProfile) {
        this.context = context;
        this.mProfile = mProfile;
    }

    @NonNull
    @Override
    public ChatAdapter.ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.rv_chat_item, parent,false);
        return new ChatViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatAdapter.ChatViewHolder holder, int position) {
        Profile profile = mProfile.get(position);
        Profile user = mProfile.get(position);
        holder.firstname.setText(profile.getFirst_name());
        holder.lastname.setText(profile.getLast_name());
        Glide.with(context)
                .load(profile.getProfile_url())
                .placeholder(R.drawable.img_dp_default)
                .centerInside()
                .into(holder.userDP);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ChatDetailActivityUser.class);
                intent.putExtra("id", profile.getId());
                intent.putExtra("profile_url", profile.getProfile_url());
                intent.putExtra("first_name", profile.getFirst_name());
                intent.putExtra("last_name", profile.getLast_name());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mProfile.size();
    }
    public static class ChatViewHolder extends RecyclerView.ViewHolder{
        TextView firstname;
        TextView lastname;
        TextView lastMessage;
        ImageView userDP;
        public ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            firstname = itemView.findViewById(R.id.userFirstname);
            lastname = itemView.findViewById(R.id.userLastname);
            lastMessage = itemView.findViewById(R.id.lastMessage);
            userDP = itemView.findViewById(R.id.userDP);

        }
    }
}
