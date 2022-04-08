package com.example.budgetapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    final private String TAG = "<DEBUG>";

    private EditText registerEmail, registerPassword;
    private Button registerButton;
    private TextView loginText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerEmail = findViewById(R.id.registerEmail);
        registerPassword = findViewById(R.id.registerPassword);
        registerButton = findViewById(R.id.registerButton);
        loginText = findViewById(R.id.loginText);

        registerButton.setOnClickListener(view -> {
            // TODO: register using Firebase
            Intent homeIntent = new Intent(RegisterActivity.this, HomeActivity.class);
            startActivity(homeIntent);
            finish();
        });

        loginText.setOnClickListener(view -> {
            Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(loginIntent);
        });

    }
}