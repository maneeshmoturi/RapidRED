package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.budgetapp.adapter.RecyclerViewAdapter;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        RecyclerView rvItems = findViewById(R.id.rvItems);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        rvItems.setAdapter(adapter);

        rvItems.setLayoutManager(new LinearLayoutManager(this));
    }
}