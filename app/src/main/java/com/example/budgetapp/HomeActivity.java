package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.budgetapp.adapter.RecyclerViewAdapter;
import com.example.budgetapp.controller.SwipeController;
import com.example.budgetapp.models.Budget;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rvItems = findViewById(R.id.rvItems);
        List<Budget> rvData = fillWithData();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(rvData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        ItemTouchHelper.Callback swipeController = new SwipeController();
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);

        rvItems.setAdapter(adapter);
        rvItems.setLayoutManager(layoutManager);
        itemTouchHelper.attachToRecyclerView(rvItems);
    }

    private List<Budget> fillWithData() {
        List<Budget> toR = new ArrayList<>();

        toR.add(new Budget("MARCH 12, 2022", "Zomato", "12,000"));
        toR.add(new Budget("MARCH 12, 2022", "Swig", "12,000"));
        toR.add(new Budget("MARCH 12, 2022", "Uber", "12,000"));

        return toR;
    }
}