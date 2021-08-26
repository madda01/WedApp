package com.example.dreamwedmadd;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PasswordConfirmationActivity extends AppCompatActivity {

    Button btnresetpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_confirmation);

        //mapping the button
        btnresetpass=findViewById(R.id.btnresetlogin);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //event handling for login
        btnresetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i = new Intent();
                startActivity(i);
            }
        });
    }
}