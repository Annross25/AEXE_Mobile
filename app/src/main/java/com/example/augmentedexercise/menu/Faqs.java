package com.example.augmentedexercise.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.augmentedexercise.adapter.FaqsAdapter;
import com.example.augmentedexercise.model.FaqsModel;
import com.example.augmentedexercise.R;

import java.util.ArrayList;
import java.util.List;

public class Faqs extends AppCompatActivity {

    RecyclerView recyclerView = findViewById(R.id.faqsRecyclerView);
    List<FaqsModel> faqsModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_faqs);

        initData();
        setRecycleView();
    }

    private void setRecycleView() {
        FaqsAdapter faqsAdapter = new FaqsAdapter(faqsModelList);
        recyclerView.setAdapter(faqsAdapter);
        recyclerView.setHasFixedSize(true);
    }

    private void initData() {
        faqsModelList = new ArrayList<>();
        //faqsModelList.add(new FaqsModel("Where is the gym located?","@string/ans_faqs1"));
    }
}