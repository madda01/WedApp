package com.example.dreamwedmadd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ForgetPasswordActivity extends AppCompatActivity {

    Button btnreset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //map the button
        btnreset=findViewById(R.id.btnreset);

        //get intents
        Intent receiveintent= getIntent();
        String message3=receiveintent.getStringExtra("Message3");
        Toast.makeText(getApplicationContext(),message3,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //event handling for going to login
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i = new Intent(ForgetPasswordActivity.this,PasswordConfirmationActivity.class);
                startActivity(i);
            }
        });
    }
}