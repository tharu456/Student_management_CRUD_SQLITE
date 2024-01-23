package com.example.nibmcrudsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validLogin(view);
            }
        });
    }

    public void validLogin(View view) {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();

        if (username.equals("admin") && password.equals("abc@123")) {
            Intent i1 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i1);
        } else {
            Toast.makeText(getApplicationContext(), "invalid username/password or fields are empty", Toast.LENGTH_SHORT).show();
        }
    }
    }

