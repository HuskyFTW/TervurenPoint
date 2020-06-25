package com.example.tervurenrecycles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    Button login;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        login = findViewById(R.id.mLogin);
        register = findViewById(R.id.registerBtn);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newlogin();
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newregister();
            }
        });
    }

    private void newlogin() {
        Intent settings = new Intent(this, Login.class);
        startActivity(settings);
    }

    private void newregister() {
        Intent settings = new Intent(this, Registreren.class);
        startActivity(settings);
    }
}
