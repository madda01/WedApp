package com.example.dreamwedmadd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnreg, btnlog;
    EditText etusername,etpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mapping the button
        btnreg=findViewById(R.id.btnreg);
        btnlog=findViewById(R.id.btnlog);
        etusername=findViewById(R.id.etenteremail);
        etpassword=findViewById(R.id.etenterpassword);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //event handling for register
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i= new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        //event handling for going to home
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i= new Intent();
                startActivity(i);
            }
        });

        //event handling for forget password
        /*etforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i= new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                startActivity(i);
            }
        });*/
    }
}