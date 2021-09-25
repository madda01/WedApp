package com.example.dreamwedmadd;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dreamwedmadd.database.DBConnection;

public class ForgetPasswordActivity extends AppCompatActivity {
    //variable declaration
    Button btnreset;
    EditText username;
    DBConnection db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        //map the button
        btnreset=findViewById(R.id.btnreset);
        username=findViewById(R.id.checkemailtext);

        //initialize database
        db=new DBConnection(this);

        //get intents
        Intent receiveintent= getIntent();
        String message3=receiveintent.getStringExtra("Message3");
        Toast.makeText(getApplicationContext(),message3,Toast.LENGTH_LONG).show();

        //event handling for going to login
        btnreset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user= username.getText().toString();

                Boolean checkuser= db.checkUser(user);

                if(checkuser==true){
                    //intent creation: Explicit
                    Intent i = new Intent(ForgetPasswordActivity.this,PasswordConfirmationActivity.class);
                    i.putExtra("username",user);
                    Toast.makeText(ForgetPasswordActivity.this,"User exists",Toast.LENGTH_LONG).show();
                    startActivity(i);
                }else{
                    Toast.makeText(ForgetPasswordActivity.this,"User does not exists",Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}