package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    FirebaseFirestore myDB;
    FirebaseApp myApp;

    EditText edtData;
    ListView listView;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // InitViews
        edtData = findViewById(R.id.edt_hint);
        listView = findViewById(R.id.lv);

        // Init FirebaseApp
        myApp = FirebaseApp.getInstance();

        // Init FireStore
        myDB = FirebaseFirestore.getInstance();
        readData();
    }

    void readData() {
        myDB.collection("tasks").addSnapshotListener((documentSnapshots, e) -> {
            if (e != null)
                toastResult(e.getMessage());
            list.clear();
            if (documentSnapshots != null) {
                for (DocumentSnapshot doc : documentSnapshots) {
                    list.add(doc.getString("task_name"));
                }
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getApplicationContext(),
                    R.layout.simple_list_item,
                    R.id.text1,
                    list);
            listView.setAdapter(arrayAdapter);
        });
    }

    public void onAddClicked(View view) {
        hideKeyboard(this);
        if (edtData.getText().toString().length() > 0) {
            Map<String, Object> data = new HashMap<>();
            data.put("task_name", edtData.getText().toString());
            myDB.collection("tasks").document("1")
                    .set(data)
                    .addOnSuccessListener(documentReference -> toastResult("Data added successfully"))
                    .addOnFailureListener(e -> toastResult("Error while adding the data : " + e.getMessage()));

        } else {
            edtData.setError("Value Required");
        }
    }

    public void onUpdateClicked(View view) {
        hideKeyboard(this);
        if (edtData.getText().toString().length() > 0) {
            Map<String, Object> data = new HashMap<>();
            data.put("task_name", edtData.getText().toString());
            myDB.collection("tasks").document("1").update(data)
                    .addOnSuccessListener(aVoid -> toastResult("Data updated successfully"))
                    .addOnCompleteListener(task -> toastResult("Data update Completed"))
                    .addOnFailureListener(e -> toastResult("Error while updating the data : " + e.getMessage()));
        } else {
            edtData.setError("Value Required");
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

    public static void hideKeyboard(HomeActivity activity) {
        View view = activity.findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}