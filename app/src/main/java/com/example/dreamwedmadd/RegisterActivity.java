package com.example.dreamwedmadd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    Button btnreg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //mapping the button
        btnreg=findViewById(R.id.btnregform);

        //get intents
        Intent receiveintent= getIntent();
        String message1=receiveintent.getStringExtra("Message1");
        Toast.makeText(getApplicationContext(),message1,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //event handling for login
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //intent creation: Explicit
                Intent i = new Intent(RegisterActivity.this,MainActivity2.class);
                i.putExtra("MessageReg","Successfully created an account");
                startActivity(i);
            }
        });
    }
}