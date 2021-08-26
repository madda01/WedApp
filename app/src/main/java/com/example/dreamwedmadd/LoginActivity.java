package com.example.dreamwedmadd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button btnreg, btnlog;
    EditText etusername,etpassword;
    TextView etforget;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //mapping the button
        btnreg=findViewById(R.id.btnreg);
        btnlog=findViewById(R.id.btnlog);
        etusername=findViewById(R.id.etenteremail);
        etpassword=findViewById(R.id.etenterpassword);
        etforget=findViewById(R.id.forgetpasswordlink);
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
                i.putExtra("Message1","New User");
                startActivity(i);
            }
        });

        //event handling for going to home
        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i= new Intent();
                i.putExtra("Message2","Directing To Home");
                startActivity(i);
            }
        });

        //event handling for forget password
        etforget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i= new Intent(LoginActivity.this,ForgetPasswordActivity.class);
                i.putExtra("Message3","Reset Password");
                startActivity(i);
            }
        });
    }
}