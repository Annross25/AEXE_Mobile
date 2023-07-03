package com.example.augmentedexercise.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.augmentedexercise.R;
import com.example.augmentedexercise.model.Message;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MessageAdapter extends RecyclerView.Adapter{

    ArrayList<Message> messageModels;
    Context context;
    String recId;
    int SENDER_VIEW_TYPE = 1;
    int RECEIVER_VIEW_TYPE = 2;

    public MessageAdapter(ArrayList<Message> messageModels, Context context) {
        this.messageModels = messageModels;
        this.context = context;
    }

    public MessageAdapter(ArrayList<Message> messageModels, Context context, String recId) {
        this.messageModels = messageModels;
        this.context = context;
        this.recId = recId;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.message_sender,parent,false);
            return new SenderViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.message_receiver, parent, false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        SharedPreferences preferences = context.getSharedPreferences("MY_APP", Context.MODE_PRIVATE);
        String userID = preferences.getString("id", "");
        if(messageModels.get(position).getId().equals(userID)){
            return SENDER_VIEW_TYPE;
        }
        else{
            return RECEIVER_VIEW_TYPE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message messageModel = messageModels.get(position);

        if(holder.getClass() == SenderViewHolder.class)
        {
            ((SenderViewHolder)holder).senderMsg.setText(messageModel.getMessage());

            Date date = new Date(messageModel.getTimestamp());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
            String strDate = simpleDateFormat.format(date);
            ((SenderViewHolder)holder).senderTime.setText(strDate);
        }
        else{
            ((ReceiverViewHolder)holder).receiverMsg.setText(messageModel.getMessage());

            Date date = new Date(messageModel.getTimestamp());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("h:mm a");
            String strDate = simpleDateFormat.format(date);
            ((ReceiverViewHolder)holder).receiverTime.setText(strDate);
        }
    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder{

        TextView receiverMsg , receiverTime;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);

            receiverMsg = itemView.findViewById(R.id.receiverText);
            receiverTime = itemView.findViewById(R.id.receiverTime);
        }
    }
    public class SenderViewHolder extends RecyclerView.ViewHolder{

        TextView senderMsg, senderTime;

        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);

            senderMsg = itemView.findViewById(R.id.senderText);
            senderTime = itemView.findViewById(R.id.senderTime);
        }
    }
}
