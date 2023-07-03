package com.example.augmentedexercise.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.augmentedexercise.model.FaqsModel;
import com.example.augmentedexercise.R;

import java.util.List;

public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.FaqsVH> {

    List<FaqsModel> faqsList;

    public FaqsAdapter(List<FaqsModel> faqsList) {
        this.faqsList = faqsList;
    }

    @NonNull
    @Override
    public FaqsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_faqs_item, parent, false);
        return new FaqsVH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FaqsVH holder, int position) {

        FaqsModel faqsmodel = faqsList.get(position);
        holder.questionTxt.setText(faqsmodel.getQuestion());
        holder.answerTxt.setText(faqsmodel.getAnswer());

        boolean isExpandable = faqsList.get(position).isExpandable();
        holder.expandLayout.setVisibility(isExpandable ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return faqsList.size();
    }

    public class FaqsVH extends RecyclerView.ViewHolder {

        TextView questionTxt, answerTxt;
        LinearLayout linearLayout;
        RelativeLayout expandLayout;

        public FaqsVH(@NonNull View itemView) {
            super(itemView);

            questionTxt = itemView.findViewById(R.id.question);
            answerTxt = itemView.findViewById(R.id.answer);

            linearLayout = itemView.findViewById(R.id.linear_layout);
            expandLayout = itemView.findViewById(R.id.expand_layout);

            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FaqsModel faqsModel = faqsList.get(getAdapterPosition());
                    faqsModel.setExpandable(!faqsModel.isExpandable());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
