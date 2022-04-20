package com.example.budgetapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.budgetapp.adapter.BudgetAdapter;
import com.example.budgetapp.models.Budget;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HomeActivity extends AppCompatActivity {

    FirebaseFirestore myDB;
    FirebaseApp myApp;

    EditText edtDate, edtTitle, edtAmount;
    RecyclerView listView;

    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
    BudgetAdapter adapter;

    List<Budget> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // InitViews
        edtDate = findViewById(R.id.edtDate);
        edtTitle = findViewById(R.id.edtTitle);
        edtAmount = findViewById(R.id.edtAmount);
        listView = findViewById(R.id.rvBudget);

        listView.setAdapter(adapter);
        listView.setLayoutManager(layoutManager);

        // Init FirebaseApp
        myApp = FirebaseApp.getInstance();

        // Init FireStore
        myDB = FirebaseFirestore.getInstance();
        readData();
    }

    void readData() {
        myDB.collection("budgets").addSnapshotListener((documentSnapshots, e) -> {
            if (e != null)
                toastResult(e.getMessage());
            list.clear();

            if (documentSnapshots != null) {
                for (DocumentSnapshot doc : documentSnapshots) {
                    String _id = doc.getId();
                    String _title = doc.getString("title");
                    String _amount = doc.getString("amount");
                    String _date = doc.getString("date");

                    list.add(new Budget(_id, _date, _title, _amount));
                }
            }

            adapter = new BudgetAdapter(list);
            listView.setAdapter(adapter);
        });
    }

    public void onAddClicked(View view) {
        hideKeyboard(this);

        String _date = edtDate.getText().toString().trim();
        String _title = edtTitle.getText().toString().trim();
        String _amount = edtAmount.getText().toString().trim();

        Budget newData = new Budget(_date, _title, _amount);

        myDB.collection("budgets")
                .add(newData)
                .addOnCompleteListener(documentReference -> toastResult("Data added successfully"))
                .addOnFailureListener(e -> toastResult("Error while adding the data : " + e.getMessage()));

        edtDate.setText("");
        edtTitle.setText("");
        edtAmount.setText("");
    }

    public void onUpdateClicked(View view) {
        hideKeyboard(this);
        if (edtDate.getText().toString().length() > 0) {
            Map<String, Object> data = new HashMap<>();
            data.put("task_name", edtDate.getText().toString());
            myDB.collection("tasks").document("1").update(data)
                    .addOnSuccessListener(aVoid -> toastResult("Data updated successfully"))
                    .addOnCompleteListener(task -> toastResult("Data update Completed"))
                    .addOnFailureListener(e -> toastResult("Error while updating the data : " + e.getMessage()));
        } else {
            edtDate.setError("Value Required");
        }
    }

    public void onDeleteClicked(View view) {
        hideKeyboard(this);
        myDB.collection("tasks").document("1").delete()
                .addOnSuccessListener(aVoid -> toastResult("Data deleted successfully"))
                .addOnFailureListener(e -> toastResult("Error while deleting the data : " + e.getMessage()));
    }

    public void toastResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public String getSize(String collection) {
        AtomicInteger count = new AtomicInteger();
        myDB.collection(collection).get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                count.set(task.getResult().size());;
            } else {
                edtDate.setError("ERROR getSize()");
            }
        });
        return count.toString();
    }

    public static void hideKeyboard(HomeActivity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            Toast.makeText(this, "See you Soon", Toast.LENGTH_SHORT).show();

            // Sign Out active Firebase User
            FirebaseAuth.getInstance().signOut();

            Intent loginIntent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}